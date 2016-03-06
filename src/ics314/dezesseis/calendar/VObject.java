package ics314.dezesseis.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VObject {
    //The following are constants used for .ics file output
    private final static String PRODID = "Team Dezessis - Calendaring Project";
    private final static String VERSION = "2.0"; //THe iCal spec version required
    private final static String CRLF = "\r\n"; //Used to terminate lines

    private VObjectType objType;
    private Map<String, Object> content;
   
    private List<VObject> children;
    
    public VObject(VObjectType type) {
        this.objType = type;
        this.content = new HashMap<String, Object>();
        this.children = new ArrayList<VObject>();
    }
    
    /**
     * Method to add content lines to the object
     * Content lines have the format <NAME>:<VALUE>
     * In this case, dataLabel is <NAME> and data is <VALUE>
     * @param dataLabel - the name of the content line component, e.g. DTSTART
     * @param data - the value associated, e.g. 20160226T203000Z
     * @return true if the new content replaced existing content
     */
    public boolean addContentLine(String dataLabel, Object data) {
        return content.put(dataLabel, data)!=null;
    }
    
    /**
     * Adds the provided VObject to the list of children of the object this method
     * is called on.
     * @param child - the VObject to add as a child
     */
    public void addChild(VObject child) {
        children.add(child);
    }
    
    /**
     * Removes the provided VObject from the list of children of the object this method
     * is called on.
     * @param child - the VObject to remove
     */
    public void removeChild(VObject child) {
        children.remove(child);
    }
    
    /**
     * Returns a String representing all the data of this object and its children
     * @return string - contains all the data associated with this object according to RFC 5545
     */
    public String getTextRepresentation() {
        StringBuilder text = new StringBuilder();
        //Add object begin content line
        text.append("BEGIN:").append("V").append(objType.name()).append(CRLF);
        
        //Calendar types require additional header information
        if(objType==VObjectType.CALENDAR) {
            //Append the PRODID of the software
            text.append("PRODID:").append(PRODID).append(CRLF);
            //Append the version of the software
            text.append("VERSION:").append(VERSION).append(CRLF);
        }
        
        //Append all non-static content lines
        for(String dataLabel : content.keySet()) {
            text.append(dataLabel).append(":")
                                  .append(content.get(dataLabel))
                                  .append(CRLF);
        }
        
        //Append all child scheduling objects
        for(VObject child : children) {
            text.append(child.getTextRepresentation());
        }
        
        //Close body of the scheduling object
        text.append("END:").append("V").append(objType.name()).append(CRLF);
        
        return text.toString();   
    }
    
    //The following are shortcut functions for addContentLines
    public boolean addDtStart(String dtstart) {
        return addContentLine("DTSTART", dtstart);
    }
    public boolean addDtEnd(String dtend) {
        return addContentLine("DTEND", dtend);
    }
    public boolean addDtStamp(String dtstamp) {
        return addContentLine("DTSTAMP", dtstamp);
    }
    public boolean addSummary(String summary) {
        return addContentLine("SUMMARY", summary);
    }
    public boolean addDescription(String description) {
        return addContentLine("DESCRIPTION", description);
    }
    public boolean addLocation(String location) {
        return addContentLine("LOCATION", location);
    }

    /**
     * @return Returns the PRODID string constant
     */
    public static String getProdid() {
        return PRODID;
    }

    /**
     * @return Returns the VERSION string constant
     */
    public static String getVersion() {
        return VERSION;
    }

    /**
     * @return Returns the CRLF(new line) string constant
     */
    public static String getCrlf() {
        return CRLF;
    }

    /**
     * @return Returns the type of the object, see VObjectType
     */
    public VObjectType getObjType() {
        return objType;
    }

    /**
     * @return Returns the map containing all content lines
     */
    public Map<String, Object> getContent() {
        return content;
    }

    /**
     * @return Returns the list of immediate VObject children of this instance
     */
    public List<VObject> getChildren() {
        return children;
    }
    
}
