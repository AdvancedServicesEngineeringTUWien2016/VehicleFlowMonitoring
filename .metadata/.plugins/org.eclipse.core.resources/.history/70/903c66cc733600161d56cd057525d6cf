package vfma;

import java.util.ArrayList;
import java.util.Iterator;

public class AppRunner {

	private static LoadSensorData lsd;
	private static LoadCityMapData lcmd;
	private static ZoneLogic zl;
	
	public static void main(String[] args) {
	
		
		
		lsd = new LoadSensorData();
		lcmd = new LoadCityMapData();
		zl = new ZoneLogic();
		
		try {
			
		
			ArrayList<ZoneChange> zchange;
			ArrayList<RawSensorData> rsd = null;
			ArrayList<SensorMap> sm;
			
			String lastTimestamp = "0";
			
			while(true) {
				
		
			rsd = lsd.getSensorData(lastTimestamp);
			sm = lcmd.getCityMap();
					
			zchange = zl.calculateChanges(
				rsd,
				sm
				);
			
			zl.applyChanges(zchange);
			
			Thread.sleep(5000);
			
			if(!rsd.equals(null)) {	
				Iterator<RawSensorData> irsd = 	rsd.iterator();
				while(irsd.hasNext()) {
					RawSensorData curr = irsd.next();
					if(curr.getTimestamp().compareTo(lastTimestamp) > 0)
						lastTimestamp = curr.getTimestamp(); 
				}
			}
			
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
