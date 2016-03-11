package ics314.dezesseis.calendar.tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import ics314.dezesseis.calendar.GEO;
import ics314.dezesseis.calendar.Utilities;
import ics314.dezesseis.calendar.VObject;
import ics314.dezesseis.calendar.constants.CalendarProperty;
import ics314.dezesseis.calendar.constants.Component;
import ics314.dezesseis.calendar.exceptions.ScopeException;

public class VObjectTester {

    @Test
    public void testCalendarConstants() {
        assertEquals("Team Dezessis - Calendaring Project", VObject.getProdID());
        assertEquals("2.0", VObject.getVersion());
        assertEquals("\r\n", VObject.getCRLF());
    }
    @Test
    public void testInstantiation() {
        for(Component componentType : Component.values()) {
            VObject component = new VObject(componentType);
            assertEquals(componentType, component.getObjType());
            assertNotNull(component.getChildren());
            assertNotNull(component.getContent());
            assertTrue(component.getChildren().isEmpty());
            assertTrue(component.getContent().isEmpty());
        }
    }

    @Test
    public void testAddContentLine() {
        VObject component = new VObject(Component.EVENT);
        //VObject should accept unknown properties, return false for new content
        assertFalse(component.addContentLine("SHJSDFH21", "FOO"));
        //Should return true when replacing content
        assertTrue(component.addContentLine("SHJSDFH21", "BAR"));

        for(CalendarProperty property : CalendarProperty.values()) {
            assertFalse(component.addContentLine(property.getTag(), "FOO"));
            assertFalse(component.addContentLine(property.getTag(), "BAR"));
        }
    }

    @Test(expected = ScopeException.class)
    public void testScopeValidation() {
        VObject component = new VObject(Component.TODO);
        //Should fail, DTEND can't be added to a Component.TODO
        component.addContentLine("DTEND", "asadjhasjd");
    }

    @Test
    public void testAddChild() {
        fail("Test not implemented!");
    }

    @Test
    public void testRemoveChild() {
        fail("Test not implemented!");
    }

    /**
     * Full test of adding all tags and generating output
     */
    @Test
    public void testGetTextRepresentation() {
        VObject calendar = new VObject(Component.CALENDAR);
        VObject event = new VObject(Component.EVENT);
        calendar.addChild(event);

        Calendar start = Calendar.getInstance();
        start.set(2016, 2, 26, 10, 0, 0);
        Calendar end = Calendar.getInstance();
        end.set(2016, 2, 26, 10, 15, 0);
        Calendar now = Calendar.getInstance();

        String latlong = "21.2966783;-157.8174581"; //Known pos of 2563 Dole St

        event.addDescription("A check-in meeting with the ICS314 TA.");
        event.addSummary("ICS314 TA Meeting");
        event.addDtStart(Utilities.dateToDtString(start.getTime()));
        event.addDtEnd(Utilities.dateToDtString(end.getTime()));
        event.addDtStamp(Utilities.dateToDtString(now.getTime()));
        event.addLocation("UH Manoa POST 314B");
        event.addGeo(latlong);

        assertEquals(
                "BEGIN:VCALENDAR" + VObject.getCRLF() 
                + "PRODID:Team Dezessis - Calendaring Project" + VObject.getCRLF() 
                + "VERSION:2.0" + VObject.getCRLF() 
                + "BEGIN:VEVENT" + VObject.getCRLF() 
                + "LOCATION:UH Manoa POST 314B" + VObject.getCRLF() 
                + "GEO:21.2966783;-157.8174581" + VObject.getCRLF() 
                + "SUMMARY:ICS314 TA Meeting" + VObject.getCRLF() 
                + "DTSTAMP:" + Utilities.dateToDtString(now.getTime()) + VObject.getCRLF() 
                + "DESCRIPTION:A check-in meeting with the ICS314 TA." + VObject.getCRLF() 
                + "DTSTART:" + Utilities.dateToDtString(start.getTime()) + VObject.getCRLF() 
                + "DTEND:" + Utilities.dateToDtString(end.getTime()) + VObject.getCRLF() 
                + "END:VEVENT" + VObject.getCRLF() 
                + "END:VCALENDAR" + VObject.getCRLF(),
                calendar.getTextRepresentation()
                );
    }

    @Test
    public void testAddDtStart() {
        fail("Test not implemented!");
    }

    @Test
    public void testAddDtEnd() {
        fail("Test not implemented!");
    }
    @Test
    public void testAddDtStamp() {
        fail("Test not implemented!");
    }
    @Test
    public void testAddSummary() {
        fail("Test not implemented!");
    }
    @Test
    public void testAddDescription() {
        fail("Test not implemented!");
    }
    @Test
    public void testAddLocation() {
        VObject component = new VObject(Component.EVENT);
        component.addLocation("UH Manoa");
        assertEquals(component.getContent().get("LOCATION"), "UH Manoa");
    }
}
