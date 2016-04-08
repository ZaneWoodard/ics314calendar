package ics314.dezesseis.calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ics314.dezesseis.calendar.constants.Component;

public class Reader {

  private List<VObject> events;

  public Reader() {
    events = new ArrayList<VObject>();

  }

  public void readFile(String fileName) throws IOException {
    FileReader fr = new FileReader(fileName);
    BufferedReader br = new BufferedReader(fr);
    String line = "";

    line = br.readLine();

    // Skips over everything until 1st event
    while (!line.equals("BEGIN:VEVENT") && !line.equals("END:VCALENDAR")) {
      line = br.readLine();
    }

    // Traverses events
    while (line != null && !line.equals("END:VCALENDAR")) {
      VObject currEvent = new VObject(Component.EVENT);
      line = br.readLine();
      //traverse fields of event
      while (line != null && !line.equals("END:VEVENT")) {
        String temp = ""; // Holds next lines read
        String attribute = "";
        //StringBuilder is a much more efficient way to append strings together many times
        StringBuilder data = new StringBuilder();
        
        String[] splitLine = line.split(":", 2);
        attribute = splitLine[0];
        if(splitLine.length==2) {
            data.append(splitLine[1]);
        }

        temp = br.readLine();
        if (temp.charAt(0) == ' ') {
          data.append("\n").append(temp);
          //System.out.println(data);
          temp = br.readLine();
          while (temp.charAt(0) == ' ') {
            data.append("\n").append(temp);
            temp = br.readLine();
          } // end while
        } // end if
        line = temp;
        //System.out.println("Data logged: " + attribute +  data);
        set(currEvent, attribute, data.toString());
      } // end inner while
      line = br.readLine();
      events.add(currEvent);
      //System.out.println(currEvent.getTextRepresentation());
    } // end while
    br.close();

  } // end read file

  private void set(VObject currEvent, String attribute, String data) {
    currEvent.addContentLine(attribute, data);
  }

  public List<VObject> getList() {
    return events;
  }




}
