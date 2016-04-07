package ics314.dezesseis.calendar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Scanner;
import ics314.dezesseis.calendar.constants.Component;
import ics314.dezesseis.calendar.constants.Classification;

public class Menu {
	private static Scanner userInput =new Scanner(System.in);
	
	public Menu(){
		CreateICSFile create = new CreateICSFile();
		int input;
		do{
			System.out.println("Select your choice: ");
		    System.out.println("1 -> create calendar events    2 -> read the .ics files" );
		    System.out.println("3 -> list all events and time");
		    System.out.println("0 -> exit");
		    System.out.print("enter your number -> ");
		    try {
		    	input = Integer.parseInt(userInput.nextLine());
		    	switch(input){
		    	case 1:  create.start();
		    		break;
		    	case 2:  
		    		break;
				case 3: 
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
}
