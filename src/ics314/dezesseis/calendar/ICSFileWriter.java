package ics314.dezesseis.calendar;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ICSFileWriter {
    public static void write(File fOut, VObject calendar) {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fOut))) {
            bw.write(calendar.getTextRepresentation());
        } catch (IOException e) {
            System.out.println("An unknown error occured when attempting to write to an ICS file");
            e.printStackTrace();
        }
    }
}
