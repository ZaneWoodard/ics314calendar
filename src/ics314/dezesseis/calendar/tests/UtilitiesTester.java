package ics314.dezesseis.calendar.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.Test;

import ics314.dezesseis.calendar.Utilities;
import ics314.dezesseis.calendar.VObject;
import ics314.dezesseis.calendar.constants.Component;

public class UtilitiesTester {
    @Test
    public void testEventSortingRandom() {
        int startLen = 1;
        int stepLen = 5;
        int steps = 100;
        
        int permutations = 5; //How many list permutations should be tried at each step
        
        for(int arrLen = startLen; arrLen < startLen + (stepLen*steps); arrLen += stepLen) {
            
            ArrayList<VObject> sortedEvents = new ArrayList<VObject>(arrLen);
            Date start = new Date();
            
            //Produce a sorted event array of the variable length
            for(int x = 0; x < arrLen; x++) {
                VObject event = new VObject(Component.EVENT);
                event.addDtStart(Utilities.dateToDtString(start));
//                System.out.println(event.getProperty(CalendarProperty.DTSTART));
                sortedEvents.add(event);
                //Move date forward by amount between 1ms and 5000 minutes
                start = new Date(start.getTime() + 1 + (long)(1000*60*5000*Math.random()));
            }
            
            //Copy the sorted array and shuffle it
            ArrayList<VObject> unsorted = (ArrayList<VObject>) sortedEvents.clone();
            //Test <permutations> different permutations
            for(int x = 0; x < permutations; x++) {
                Collections.shuffle(unsorted);
                assertEquals(sortedEvents, Utilities.sortVObjectByStartDate(unsorted));
                assertEquals(sortedEvents, unsorted); //Assert sorted in place   
            }

        }
    }
}
