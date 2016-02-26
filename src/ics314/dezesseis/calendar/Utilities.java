package ics314.dezesseis.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
