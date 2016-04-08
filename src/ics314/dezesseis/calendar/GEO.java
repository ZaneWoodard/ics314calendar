package ics314.dezesseis.calendar;

import ics314.dezesseis.calendar.constants.CalendarProperty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class GEO {
    private String location;
    private String output;

    // structure
    public GEO(String location) {
        this.location = location;
        this.output = "";
    }

    /*********************
     * connect to google map, send the address to the map to find the position
     * 
     * @param address
     * @return a String of "latitude(90);longitude(180)" or "ERROR" google map
     *         cannot location the position;
     * @throws Exception
     **********************/
    private static String getLatLongPositions(String address) throws Exception {
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
                + "&sensor=true";
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setConnectTimeout(5 * 1000);
        httpConnection.connect();
        // HTTP Status Codes value= 200 -> OK;
        responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {
            // httpConnection.getinputstream return a XML file
            BufferedReader stdout = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String line;

            while ((line = stdout.readLine()) != null) {
                // System.out.println(line);
                if (line.indexOf("location") != -1) {
                    String latitude = stdout.readLine();
                    latitude = latitude.replace("<lat>", "");
                    latitude = latitude.replace("</lat>", "");
                    String longitude = stdout.readLine();
                    longitude = longitude.replace("<lng>", "");
                    longitude = longitude.replace("</lng>", "");
                    return latitude + ";" + longitude.trim();
                }

            }
        }
        return "ERROR";
    }

    public void setPosition(String position) {
        output = position;

    }

    /************************
     * return the position
     * 
     * @return .ics format position of location or ERROR for not found
     */
    public String getPosition() {
        try {
            output = getLatLongPositions(location);
            return output.trim();
        } catch (SocketTimeoutException e) {
            // Exception occurs when couldn't connect
            System.out.println("ERROR: cannot connect to Google.");
        } catch (Exception e) {
            // Unexpected error, print it to make sure we know about it
            e.printStackTrace();
        }
        return "ERROR";
    }

    /************************************
     * 
     * @param position1
     * @param position2
     * @return array of String4 [0] distance in miles [1] distance in km
     */
    public static Double[] GreatCircleDistance(String position1, String position2) {
        Double distance[] = new Double[2];
        double[] latLong1 = Utilities.positionToLatLong(position1);
        double[] latLong2 = Utilities.positionToLatLong(position2);
        try {
            // latitude of position 1
            double x1 = Math.toRadians(latLong1[0]);
            // longitude of position 1
            double y1 = Math.toRadians(latLong1[1]);
            // latitude of position 2
            double x2 = Math.toRadians(latLong2[0]);
            // longitude of position 2
            double y2 = Math.toRadians(latLong2[1]);
            // great circle distance in radians
            double angle1 = Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
            // convert back to degrees
            angle1 = Math.toDegrees(angle1);
            // each degree on a great circle of Earth is 60 nautical miles
            // 1 nautical miles= 1.15078 miles
            double r = 60 * 1.15078;
            // compute the distance in miles
            double distance1 = r * angle1;
            distance[0] = distance1;
            r = 60 * 1.852;
            distance1 = r * angle1;
            distance[1] = distance1;
        } catch (NumberFormatException e) {
            distance = null;
        }
        return distance;
    }

    public static Double[] cumulativeGCD(List<VObject> events) {
        Utilities.sortVObjectByStartDate(events);
        double totalMiles = 0;
        double totalKilometers = 0;
        for(int i = 0; i < events.size()-1; i++) {
            VObject event1 = events.get(i);
            VObject event2 = events.get(i+1);

            Double[] gcds = GreatCircleDistance(event1.getProperty(CalendarProperty.GEO), event2.getProperty(CalendarProperty.GEO));
            event1.addComment(String.format("GCD DISTANCE %d(miles), %d(kilometers)", gcds[0], gcds[1]));
            totalMiles += gcds[0];
            totalKilometers += gcds[1];
        }

        events.get(events.size()).addComment(String.format("TOTAL GCD DISTANCE %d(miles), %d(kilometers)", totalMiles, totalKilometers));

        Double[] distances = {totalMiles, totalKilometers};
        return distances;
    }

}
