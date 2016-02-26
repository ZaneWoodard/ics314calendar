package ics314.dezesseis.calendar;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.io.FileOutputStream;


public class CalendarExample {
    public static void main(String[] args) {
        VObject calendar = new VObject(VObjectType.CALENDAR);
        
        VObject event = new VObject(VObjectType.EVENT);
        
        Calendar start = Calendar.getInstance();
        start.set(2016, 2, 26, 10, 0, 0);
        Calendar end = Calendar.getInstance();
        end.set(2016, 2, 26, 10, 15, 0);     

        event.addSummary("TA Meeting");
        event.addDescription("Team meeting with the ICS314 TA");
        event.addDtStart(Utilities.dateToDtString(start.getTime()));
        event.addDtEnd(Utilities.dateToDtString(end.getTime()));
        event.addDtStamp(Utilities.dateToDtString(Calendar.getInstance().getTime()));
        event.addLocation("ICS Lounge in POST");
        
        calendar.addChild(event);
        
        File out = new File("test.ics");
        try(FileOutputStream outStream = new FileOutputStream(out)) {
            outStream.write(calendar.getTextRepresentation().getBytes());
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
