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
		
		ArrayList<ZoneChange> zchange = null;
		ArrayList<RawSensorData> rsd = null;
		ArrayList<SensorMap> sm;
		
		try {
			
			
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

		} catch (VFMException e) {
			Mailer mailer = new Mailer();
			mailer.sendMessage(e.getMessage(), "Pleace contact XY for further actions! Best Regards, VFM");
			System.out.println("Resync attempt....");
			zl.resyncZones(zchange);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
