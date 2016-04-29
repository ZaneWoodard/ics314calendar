package ics314.dezesseis.calendar.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ics314.dezesseis.calendar.GEO;

public class GreatCircleDistancetest {

	@Test
	public void TestGEO1(){
		//do not nothing 
		GEO p = new GEO("Hawaii");
		//Los angeles
		String position1 = "34.05;-118.25";
		//New York
		String position2 = "40.7127;-74.0059";
		Double distance[] = new Double [2];
		//in mile
		distance[0] = 2444.3093104614445;
		//in km
		distance[1] = 3933.7326361029877;
		Double computeDistance[] = p.GreatCircleDistance(position1, position2);
		assertEquals(distance[0], computeDistance[0]);
		assertEquals(distance[1], computeDistance[1]);
	}
	@Test
	public void TestGCD2(){
		VObject calendar = new VObject(Component.CALENDAR);
		List<VObject> events = ReadICSFiles.test();
        events.stream().forEach((VObject event) -> calendar.addChild(event));
        events = calendar.getChildren();
        Utilities.sortVObjectByStartDate(events);
        Double[] distances = GEO.cumulativeGCD(events);

	}
}
