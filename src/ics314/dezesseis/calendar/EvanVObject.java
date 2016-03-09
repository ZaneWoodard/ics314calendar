
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EvanVObject {

  private static String DTSTART = "DTSTART:";
  private static String DTEND = "DTEND:";
  private static String DESCRIPTION = "DESCRIPTION:";
  private static String LOCATION = "LOCATION:";
  private static String SUMMARY = "SUMMARY:";
  private static String fileName;
  private static String combine;
  private static String position="GEO:";
  public VObject(String fileName) throws IOException{
    this.fileName = fileName;
  }

  public void setDTSTART(String start) {
    DTSTART = DTSTART + start + "\n";
  }

  public void setDTEND(String end) {
    DTEND = DTEND + end + "\n";
  }

  public void setDESCRIPTION(String description) {
    DESCRIPTION = DESCRIPTION + description + "\n";
  }

  public void setLOCATION(String location) {
    LOCATION = LOCATION + location + "\n";
  }

  public void setSUMMARY(String summary) {
    SUMMARY = SUMMARY + summary + "\n";
  }
  public void setGEO(String position){
    this.position += position+"\n";
  }
  private static void catenate() {
    String BEGINC = "BEGIN:VCALENDAR\n";
    String PRODID = "PRODID:Team Dezessis - Calendaring Project\n";
    String VERSION = "VERSION:2.0\n";
    String CALSCALE = "CALSCALE:GREGORIAN\n";
    String BEGIN = "BEGIN:VEVENT\n";
    String END = "END:VEVENT\nEND:VCALENDAR\n";
    combine = BEGINC + PRODID + VERSION + CALSCALE;
    combine = combine + BEGIN + DTSTART + DTEND + DESCRIPTION;
    combine = combine + LOCATION + position + SUMMARY + END;
  }

  public void write() throws IOException {
    File file = new File(fileName);
    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    catenate();
    bw.write(combine);
    bw.close();
  }
}
