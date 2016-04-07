package ics314.dezesseis.calendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;


public class GEO {
    private String location;
    private String output;

    //structure
    public GEO(String location){
        this.location = location;
        this.output="";
    }


    /*********************
     * connect to google map, send the address to the map to find the position
     * @param address
     * @return a String of "latitude(90);longitude(180)" or "ERROR" google map cannot location the position;
     * @throws Exception
     **********************/
    private static String getLatLongPositions(String address) throws Exception
    {
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();        
        httpConnection.setConnectTimeout(5*1000);
        httpConnection.connect();
        //HTTP Status Codes value= 200 -> OK;
        responseCode = httpConnection.getResponseCode();
        if(responseCode == 200)
        {
            //httpConnection.getinputstream return a XML file 
            BufferedReader stdout = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String line;

            while((line = stdout.readLine())!= null){
                // System.out.println(line);
                if(line.indexOf("location") != -1){
                    String latitude = stdout.readLine();
                    latitude = latitude.replace("<lat>", "");
                    latitude = latitude.replace("</lat>", "");
                    String longitude = stdout.readLine();
                    longitude = longitude.replace("<lng>", "");
                    longitude = longitude.replace("</lng>", "");
                    return latitude +";"+ longitude.trim();
                }

            }
        }
        return "ERROR";
    }

    public void setPosition(String position){
        output =position;

    }

    /************************
     * return the position
     * @return .ics format position of location or ERROR for not found
     */
    public String getPosition() {
        try {
            output = getLatLongPositions(location);
            return output.trim();
        } catch (SocketTimeoutException e) {
            //Exception occurs when couldn't connect
            System.out.println("ERROR: cannot connect to Google.");
        } catch(Exception e) {
            //Unexpected error, print it to make sure we know about it
            e.printStackTrace();
        }
        return "ERROR";
    }
    /************************************
     * 
     * @param position1
     * @param position2
     * @return array of String4
     * 		   [0] distance in miles
     *         [1] distance in km
     */
    public String[] GreatCircleDistance (String position1,String position2){
    	String distance[] = new String[2];
    	int m1 = position1.indexOf(";");
    	int m2 = position2.indexOf(";");
    	try{
    		//latitude of position 1
    		double x1 = Math.toRadians(Double.parseDouble(position1.substring(0, m1)));
    		//longitude of position 1
            double y1 = Math.toRadians(Double.parseDouble(position1.substring(m1+1)));
            //latitude of position 2
            double x2 = Math.toRadians(Double.parseDouble(position2.substring(0, m2)));
            //longitude of position 2
            double y2 = Math.toRadians(Double.parseDouble(position2.substring(m2+1)));
            // great circle distance in radians
            double angle1 = Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
            // convert back to degrees
            angle1 = Math.toDegrees(angle1);
            // each degree on a great circle of Earth is 60 nautical miles
            // 1 nautical miles= 1.15078 miles
            double r = 60 * 1.15078;
            //compute the distance in miles
            double distance1 = r * angle1;
            distance[0] = ""+ distance1;
            r = 60 * 1.852;
            distance1 = r * angle1;
            distance[1] = "" + distance1;
    	}
    	catch(NumberFormatException e){
    		distance = null; 
    	}
    	return distance;
    }
    

}
