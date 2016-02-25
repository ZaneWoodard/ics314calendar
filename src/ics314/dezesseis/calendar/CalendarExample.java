package ics314.dezesseis.calendar;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;


public class CalendarExample {
    public static void main(String[] args) {
        VObject calendar = new VObject(VObjectType.CALENDAR);
        
        VObject event = new VObject(VObjectType.EVENT);
        event.addSummary("TA Meeting");
        event.addDescription("Team meeting with the ICS314 TA");
        event.addDtStart("20160226T103000");
        event.addDtEnd("20160226T104500");
        event.addDtStamp("20160224T104500");
        event.addLocation("ICS Lounge in POST");
        
        calendar.addChild(event);
        
        File out = new File("/Users/Zane/Desktop/test.ics");
        try(FileOutputStream outStream = new FileOutputStream(out)) {
            outStream.write(calendar.getTextRepresentation().getBytes());
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
