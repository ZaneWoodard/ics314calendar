package ics314.dezesseis.calendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class GEO {
	private String location;
	private String output;
	
	//structure
	public GEO(String location){
		this.location = location;
		this.output="";
	}
	
	
	/*********************
	 * connect to google map, send the address to the map to find the position
	 * @param address
	 * @return a String of "latitude;longitude" or "ERROR" google map cannot location the position;
	 * @throws Exception
	 **********************/
	private static String getLatLongPositions(String address) throws Exception
	  {
	    int responseCode = 0;
	    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    //HTTP Status Codes value= 200 -> OK;
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	      //httpConnection.getinputstream return a XML file 
	      BufferedReader stdout = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
	      String line;

          while((line = stdout.readLine())!= null){
        	 // System.out.println(line);
        	  if(line.indexOf("location") != -1){
        		 String latitude = stdout.readLine();
        		 latitude = latitude.replace("<lat>", "");
        		 latitude = latitude.replace("</lat>", "");
        		 String longitude = stdout.readLine();
        		 longitude = longitude.replace("<lng>", "");
        		 longitude = longitude.replace("</lng>", "");
        		 return latitude +";"+ longitude.trim();
        	 	}
        	  
          }
          System.out.println("ERROR: cannot connect to internet.");
	    }
	    return "ERROR";
	  }
	
	public void setPosition(String position){
		output =position;
		
	}
	
	/************************
	 * return the position
	 * @return .ics format position of location or ERROR for not found
	 */
	public String getPosition() {
		try {
			
			output = getLatLongPositions(location);
			if(output.equals("ERROR"))
				return "ERROR";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
		}
		return output.trim();
	}
		
}
