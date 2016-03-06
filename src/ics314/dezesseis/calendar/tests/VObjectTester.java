package ics314.dezesseis.calendar.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ics314.dezesseis.calendar.VObject;
import ics314.dezesseis.calendar.VObjectType;

public class VObjectTester {

    @Test
    public void testCalendarConstants() {
        assertEquals("Team Dezessis - Calendaring Project", VObject.getProdID());
        assertEquals("2.0", VObject.getVersion());
        assertEquals("\r\n", VObject.getCRLF());
    }
    @Test
    public void testInstantiation() {
        for(VObjectType componentType : VObjectType.values()) {
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
        fail("Test not implemented!");
    }
    
    @Test
    public void testAddChild() {
        fail("Test not implemented!");
    }
    
    @Test
    public void testRemoveChild() {
        fail("Test not implemented!");
    }
    
    @Test
    public void testGetTextRepresentation() {
        fail("Test not implemented!");
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
        fail("Test not implemented!");
    }
}
