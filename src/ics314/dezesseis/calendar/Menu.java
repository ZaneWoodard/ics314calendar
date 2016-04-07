package ics314.dezesseis.calendar;

import java.util.Map;
import java.util.Scanner;
import ics314.dezesseis.calendar.constants.Component;

public class Menu {
    private static Scanner userInput = new Scanner(System.in);
    // private ArrayList<VObject> events;
    private VObject event1 = new VObject(Component.CALENDAR);

    public Menu() {
        CreateICSFile create = new CreateICSFile();
        int input;
        do {
            System.out.println("Select your choice: ");
            System.out.println("1 -> create calendar events    2 -> read the .ics files");
            System.out.println("3 -> list all events and time");
            System.out.println("0 -> exit");
            System.out.print("enter your number -> ");
            try {
                input = Integer.parseInt(userInput.nextLine());
                switch (input) {
                case 1:
                    create.start();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: please enter a integer");
                input = -1;
            }
        } while (input != 0);
        System.out.println("Thank yous, see you next time.");
        userInput.close();
    }

    private void listEvent() {
        int size = event1.getListSize();
        VObject event = null;
        for (int i = 0; i < size; i++) {
            event = event1.getEvent(i);
            Map<String, Object> content = event.getContent();
            System.out.println(content.get("Summary"));
            System.out.println(content.get("DTSTART"));
        }

    }
}
