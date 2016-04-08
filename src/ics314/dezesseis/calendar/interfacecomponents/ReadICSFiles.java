package ics314.dezesseis.calendar.interfacecomponents;

import ics314.dezesseis.calendar.Reader;
import ics314.dezesseis.calendar.VObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReadICSFiles {

    public static List<VObject> start() {
        Scanner cliReader = new Scanner(System.in);

        boolean validFile = false;
        File icsFileIn = null;
        while(!validFile) {
            System.out.print("Please enter name of file to read: ");
            String fileName = cliReader.nextLine();

            if(!fileName.endsWith(".ics")) {
                System.out.println("Error! File not a .ics file!");
            } else if(!(icsFileIn=new File(fileName)).exists()) {
                System.out.println("Error! File does not exist!");
            } else if(icsFileIn.isDirectory()) {
                System.out.println("Error! Path is a directory not a file!");
            } else if(!icsFileIn.canRead() || !icsFileIn.canWrite()) {
                System.out.println("Error! Cannot read and/or write to the file!");
            } else {
                validFile = true;
            }
        }

        Reader icsFileReader = new Reader();
        try {
            icsFileReader.readFile(icsFileIn.getAbsolutePath());
        } catch(IOException error) {
            System.out.println("An unknown error occured when reading the calendar file!");
            System.out.println("Please report the following to the developers:");
            error.printStackTrace();
        }

        return icsFileReader.getList();

    }

}
