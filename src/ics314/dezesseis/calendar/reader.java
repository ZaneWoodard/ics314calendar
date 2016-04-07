package ics314.dezesseis.calendar;

import java.io.*;
import ics314.dezesseis.calendar.constants.Component;

public class testReader {

  public static void main(String args[]) throws IOException {

    reader thing = new reader();

    thing.readFile("evanhata@hawaii.edu.ics");
    System.out.println("aye lmao");
  }

}
