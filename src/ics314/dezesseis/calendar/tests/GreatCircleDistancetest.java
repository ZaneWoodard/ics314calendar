package tests;

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
		String distance[] = new String [2];
		//in mile
		distance[0] = "2444.3093104614445";
		//in km
		distance[1] = "3933.7326361029877";
		String dsum = distance[0] + distance[1];
		String computeDistance[] = p.GreatCircleDistance(position1, position2);
		String csum =  computeDistance[0] + computeDistance[1]; 
		assertEquals(dsum,csum);
	}
}
