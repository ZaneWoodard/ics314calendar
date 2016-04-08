package ics314.dezesseis.calendar.interfacecomponents;

import ics314.dezesseis.calendar.ICSFileWriter;
import ics314.dezesseis.calendar.VObject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SaveICSFile {
    private final VObject calendar;
    public SaveICSFile(VObject calendar) {
        this.calendar = calendar;
    }

    public void start() {
        Scanner cliReader = new Scanner(System.in);

        boolean validFile = false;
        File icsFileIn = null;
        while(!validFile) {
            System.out.print("Please enter name of file to write to: ");
            String fileName = cliReader.nextLine();

            if(!fileName.endsWith(".ics")) {
                System.out.println("Error! File not a .ics file!");
            } else if((icsFileIn=new File(fileName)).isDirectory()) {
                System.out.println("Error! Path is a directory not a file!");
            } else {
                validFile = true;
            }
        }

        if(!icsFileIn.exists()) try {
            icsFileIn.createNewFile();
        } catch (IOException e) {
            System.out.println("An unknown error occured when trying to create the file!");
            System.out.println("Please report the errors below to the developers:");
            e.printStackTrace();
        }

        ICSFileWriter.write(icsFileIn, this.calendar);
        System.out.println("Calendar saved!");
    }
}
