package ics314.dezesseis.calendar;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import ics314.dezesseis.calendar.constants.Classification;
import ics314.dezesseis.calendar.constants.Component;

import java.io.FileOutputStream;


public class CalendarExample {
    public static void main(String[] args) {
        VObject calendar = new VObject(Component.CALENDAR);
        VObject event = new VObject(Component.EVENT);
        calendar.addChild(event);
        
        Calendar start = Calendar.getInstance();
        start.set(2016, 2, 26, 10, 0, 0);
        Calendar end = Calendar.getInstance();
        end.set(2016, 2, 26, 10, 15, 0);     

        event.addSummary("TA Meeting");
        event.addDescription("Team meeting with the ICS314 TA");
        event.addDtStart(Utilities.dateToDtString(start.getTime()));
        event.addDtEnd(Utilities.dateToDtString(end.getTime()));
        event.addDtStamp(Utilities.dateToDtString(Calendar.getInstance().getTime()));
        event.addLocation("2563 Dole St");
        event.addClassification(Classification.PUBLIC);
        event.addGeo(new GEO("2563 Dole St").getPosition()); //Asume no error
        
        //File out = new File("test.ics");
        File out = new File(System.getProperty("user.home")+"/Desktop/test.ics");
        try(FileOutputStream outStream = new FileOutputStream(out)) {
            outStream.write(calendar.getTextRepresentation().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
