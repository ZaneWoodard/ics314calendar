package ics314.dezesseis.calendar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import ics314.dezesseis.calendar.constants.Component;
import ics314.dezesseis.calendar.constants.Classification;

public class Menu {
	private static Scanner userInput =new Scanner(System.in);
	//private ArrayList<VObject> events;
	private VObject event1 = new VObject(Component.CALENDAR);
	
	public Menu(){
		CreateICSFile create = new CreateICSFile();
		int input;
		do{
			System.out.println("Select your choice: ");
		    System.out.println("1 -> create calendar events    2 -> read the .ics files" );
		    System.out.println("3 -> list all events and time  4 -> edit the events");
		    System.out.println("0 -> exit");
		    System.out.print("enter your number -> ");
		    try {
		    	input = Integer.parseInt(userInput.nextLine());
		    	switch(input){
		    	case 1:  event1.addChild(create.start());
		    		break;
		    	case 2:  
		    		break;
				case 3: listEvent();
					break;
				case 4:
					break;
				}
		     }
			catch(NumberFormatException e){
				System.out.println("ERROR: please enter a integer");
				input = -1;
			}
		}while(input != 0 );
		System.out.println("Thank yous, see you next time.");
		userInput.close();
	}
	
	private void listEvent(){
		int size = event1.getListSize();
		VObject event = null;
		for(int i = 0 ;i < size ;i++){
			event = event1.getEvent(i);
			Map<String, Object> content = event.getContent();
			System.out.println(content.get("Summary"));
			System.out.println(content.get("DTSTART"));
		}
		
	}
}
