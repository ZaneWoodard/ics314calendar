package ics314.dezesseis.calendar.interfacecomponents;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ics314.dezesseis.calendar.GEO;
import ics314.dezesseis.calendar.Utilities;
import ics314.dezesseis.calendar.VObject;
import ics314.dezesseis.calendar.constants.CalendarProperty;
import ics314.dezesseis.calendar.constants.Component;

public class Menu {
    private static Scanner userInput = new Scanner(System.in);
    // private ArrayList<VObject> events;
    private VObject calendar = new VObject(Component.CALENDAR);

    public Menu() {
        CreateICSFile create = new CreateICSFile();
        int input;
        boolean exit = false;
        do {
            System.out.println("Select your choice: ");
            System.out.println("1 -> create calendar events    2 -> read the .ics files");
            System.out.println("3 -> list all events and time");
            System.out.println("4 -> find great circle distance");
            System.out.println("5 -> save in-memory calendar to file");
            System.out.println("0 -> exit");
            System.out.print("enter your number -> ");
            try {
                input = Integer.parseInt(userInput.nextLine());
                switch (input) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                    	calendar.addChild(create.start(userInput));
                        //calendar.add(create.start(userInput));
                        break;
                    case 2:
                        List<VObject> events = ReadICSFiles.start();
                        events.stream().forEach((VObject event) -> calendar.addChild(event));
                        break;
                    case 3:
                    	events = calendar.getChildren();
                    	int size = events.size();
                    	for(int i= 0; i <size; i++){
                    		 VObject event1 = events.get(i);
                    		 System.out.println("event name :" + event1.getProperty(CalendarProperty.SUMMARY));
                    	}
                    	System.out.println();
                        break;
                    case 4:
                        events = calendar.getChildren();
                        Utilities.sortVObjectByStartDate(events);
                        Double[] distances = GEO.cumulativeGCD(events);
                        System.out.println("GDC computed for " + events.size() + " events:");
                        System.out.println(distances[0] + " miles");
                        System.out.println(distances[1] + " kilometers");
                        break;
                    case 5:
                        new SaveICSFile(calendar).start();
                        break;

                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: please enter a integer");
                input = -1;
            }
        } while (!exit);
        System.out.println("Thank you, see you next time.");
        userInput.close();
    }

    private void listEvent() {
        int size = calendar.getListSize();
        VObject event = null;
        for (int i = 0; i < size; i++) {
            event = calendar.getEvent(i);
            Map<String, Object> content = event.getContent();
            System.out.println(content.get("Summary"));
            System.out.println(content.get("DTSTART"));
        }

    }
}