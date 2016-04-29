package ics314.dezesseis.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import ics314.dezesseis.calendar.constants.CalendarProperty;

public class Utilities {
    /**
     * Takes a Java date object, returns an iCal DT formated string
     * @return
     */
    public static String dateToDtString(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        return formatter.format(date);
    }
    
    /**
     * Takes an iCal DT formatter string without a timezone, returns the Java Date object
     * @return
     */
    public static Date dtStringtoDate(String dtstring) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        return formatter.parse(dtstring);
    }
    
    /**
     * Sorts a list of events in place according to the start times
     * @param events - the list to sort
     * @return events - the input list sorted in place
     */
    public static List<VObject> sortVObjectByStartDate(List<VObject> events) {
        
        events.sort(new Comparator<VObject>() {
            @Override
            public int compare(VObject o1, VObject o2) {
                int comp = 0;
                try {
                    Date d1 = dtStringtoDate(o1.getProperty(CalendarProperty.DTSTART));
                    Date d2 = dtStringtoDate(o2.getProperty(CalendarProperty.DTSTART));
                    comp = d1.compareTo(d2);
                } catch(ParseException e) {
                    //Should never occur according to the design spec, only used on already valid files
                }
                return comp;
            }
            
        });
        return events;
    }
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @return
     */
    public static String latLongToPosition(double latitude, double longitude) {
        return latitude + ";" + longitude;
    }
    
    /**
     * 
     * @param position
     * @return
     */
    public static double[] positionToLatLong(String position) {
        double[] latLong = new double[2];
        latLong[0] = Double.parseDouble(position.split(";")[0]);
        latLong[1] = Double.parseDouble(position.split(";")[1]);
        return latLong;
    }
}
