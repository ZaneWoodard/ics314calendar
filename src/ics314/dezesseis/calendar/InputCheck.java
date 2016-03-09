package ics314.dezesseis.calendar;
import java.util.Scanner;

public class InputCheck {
	Scanner userInput =new Scanner(System.in);

	public String Checktime(String BETime){
		String input = "";
		boolean again = false;
		//loop until user input a correct time
		do{
			System.out.print("Please, enter the "+ BETime +" time. e.g.(14:20): ");
			input = userInput.nextLine();
			input = input.trim();
			if(input.substring(2, 3).equals(":") && input.length() == 5){
				try {
					//check the time is or not integer
					int num1 = Integer.parseInt(input.substring(0, 2));
					int num2 = Integer.parseInt(input.substring(3, 5));
					//if 
					if(num1<0  || num1 >24 || num2<0 || num2>60){
						again = true;
						System.out.println("Error:only has 24 hours and 60 minutes");
					}
					else{
						again = false;
					}
				}catch (NumberFormatException e) {
					again = true;
					System.out.println("Error: Please,enter number.");
				}
			}
			else{
				again = true;
				System.out.println("Please,enter a correct time.");
			}
		}while(again);
		input = input.replace(":","");
		input = input.trim();
		return input;
	}
	
	public String CheckDate(String BEDay){
		String input = "";
		boolean again = false;
		do{
			System.out.print("please, enter the " + BEDay +" day. e.g.(2000/03/01): ");
			input = userInput.nextLine();
			input = input.trim();
			if(input.length() == 10 && input.substring(4,5).equals("/") && input.substring(7,8).equals("/")){
				try {
					//check the time is or not integer
					int year = Integer.parseInt(input.substring(0, 4));
					int month = Integer.parseInt(input.substring(5, 7));
					int day = Integer.parseInt(input.substring(8, 10));
					switch(month){
					//the months have 31 days 
					case 1: case 3: case 5:
		            case 7: case 8: case 10:
		            case 12:
		            	if(day<1 || day>31){
		            		again = true;
		            		System.out.println("Error: " + month + " has only 31 days");
		            	}
		            	else{
		            		again = false;
		            	}
						break;
					case 2:
						//check the year is common or leap year
						if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){
							//common year Feb has 29 days
							if(day<1 ||day>29){
								again = true;
								System.out.println("Error: " + month+" has only 29 days.");
							}
							else{
								again = false;
							}
						}
						else{
							//leap year Feb has 28 days
							if(day<1 || day>28){
								again = true;
								System.out.println("Error: "+month+" has only 28 days.");
							}
							else{
								again = false;
							}
						}
						break;
					//the months have 30 days
					case 4: case 6:
		            case 9: case 11:
		            	if(day<1 || day>30){
		            		again = true;
		            		System.out.println("Error: " + month+" has only 30 days.");

		            	}
		            	else{        		
		            		again = false;
		            	}
						break;
					default:
						again = true;
						System.out.println("outor: Invalid month.");
						break;
					}
				}catch (NumberFormatException e) {
					again = true;
					System.out.println("Error: Enter number for year, month an day.");

				}
			}
			else{
				again = true;
				System.out.println("Errro: Enter a correct day.");
			}
			
		}while(again);
		input = input.replace("/","");
		input = input.trim();
		return input;
	}

	public String CheckPosition(){
		boolean done = false;
		String output="";
		while(!done){
			try{
				System.out.print("Please, enter Latitude: ");
				float latF = Float.parseFloat(userInput.nextLine());
				System.out.print("Please, enter Longitude: ");
				float lonF = Float.parseFloat(userInput.nextLine());
				NumberFormat formatter = new DecimalFormat("#0.000000"); 
				output = formatter.format(latF) + ";" + formatter.format(lonF);
				done = true;
			}catch (NumberFormatException e) {
				System.out.println("Error: Not a number.");
			}
		}
		return output;
	}
}
