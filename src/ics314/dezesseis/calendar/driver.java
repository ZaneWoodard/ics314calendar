
import java.io.IOException;

public class driver {

  public static void main(String[] args) throws IOException {

    if (args.length != 5) {
      System.out.println("Error: Incorrect number of arguments");
      System.out.println("Arguments must be in the order: START END DESCRIPTION LOCATION SUMMARY");
      return;
    }

    Evan VObject test = new VObject("test.ics");
    test.setDTSTART(args[0]);
    test.setDTEND(args[1]);
    test.setDESCRIPTION(args[2]);
    test.setLOCATION(args[3]);
    test.setSUMMARY(args[4]);
    test.write();

  }
}
