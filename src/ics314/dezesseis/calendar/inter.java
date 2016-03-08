package ics314.dezesseis.calendar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Scanner;

import ics314.dezesseis.calendar.constants.Component;

public class inter {
    public static void main(String[] args) {

    	String input = "";
    	Scanner userInput =new Scanner(System.in);
    	InputCheck inputCheck = new InputCheck();
        VObject calendar = new VObject(Component.CALENDAR);
        VObject event = new VObject(Component.EVENT);
        do{
        System.out.print("Hello, do you want to create any Goolge calendar event,yes or no? y/n : ");
        input = userInput.nextLine(); 
        }while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));    	
        //
        if(input.equalsIgnoreCase("n"))
        	System.exit(0);

        System.out.print("Please, Enter a title of the event: ");
       	String summary = userInput.nextLine();
       	summary = summary.trim();
       	String beginDay = inputCheck.CheckDate("beginning")+"T";
       	String beginTime = inputCheck.Checktime("beginning")+"00";
       	String endDay = inputCheck.CheckDate("ending")+"T";
        String endTime= inputCheck.Checktime("ending")+"00";
       	System.out.print("Please, enter the location of the event:");
       	place = userInput.nextLine();
       	place = place.trim();
       	System.out.println("Please, enter a description of the event:");
       	System.out.print("->");
       	String description = userInput.nextLine();
       	description = description.trim();
       	
       	//date stamp;
       	Date myDate = new Date();
       	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
       	String stamp = sdf.format(myDate);        	
       	stamp = stamp.replace("-", "T");
       
       	event.addSummary(summary);
       	event.addDescription(description);
       	event.addDtStart(beginDay + beginTime);
       	event.addDtEnd(endDay + endTime);
       	event.addDtStamp(stamp);
       	event.addLocation(place);
       	calendar.addChild(event);
       	userInput.close();
       //System.getProperty("user.home") return User home directory
       	File out = new File(System.getProperty("user.home")+"/Desktop/test.ics");
       	try(FileOutputStream outStream = new FileOutputStream(out)) {
       		outStream.write(calendar.getTextRepresentation().getBytes());
       		outStream.close();
       	} catch (IOException e) {
       		e.printStackTrace();
       	}
      }
}
