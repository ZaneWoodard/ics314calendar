//package ics314.dezesseis.calendar;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
//import ics314.dezesseis.calendar.constants.Component;

public class reader {

  private List<VObject> events;

  public reader() {
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
      while (!line.equals("END:VEVENT") && line != null) {
        String temp = ""; // Holds next lines read
        String attribute = "";
        String data = "";
        int i = 0;

        while (line.charAt(i) != ':' && line.charAt(i) != ';') {
          i++;
        } // end while
        attribute = line.substring(0, i + 1);
        data = line.substring(i + 1, line.length());

        temp = br.readLine();
        if (temp.charAt(0) == ' ') {
          data = data + "\n " + temp;
          //System.out.println(data);
          temp = br.readLine();
          while (true) {
            if (temp.charAt(0) == ' ') {
              data = data + "\n " + temp;
              temp = br.readLine();
            }
            else {
              break;
            }
          } // end while
        } // end if
        line = temp;
        //System.out.println("Data logged: " + attribute +  data);
        set(currEvent, attribute, data);
      } // end inner while
      line = br.readLine();
      events.add(currEvent);
      //System.out.println(currEvent.getTextRepresentation());
    } // end while


  } // end read file

  private void set(VObject currEvent, String attribute, String data) {
    currEvent.addContentLine(attribute.substring(0, attribute.length() - 1), data);
  }

  public List<VObject> getList() {
    return events;
  }




}
