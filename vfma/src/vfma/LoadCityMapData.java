package vfma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;

public class LoadCityMapData {

	
	public ArrayList<SensorMap> getCityMap() {
		
        ArrayList<SensorMap> sensors = new ArrayList<SensorMap>();
		
		try {
		
        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("https://dynamodb.us-west-2.amazonaws.com");
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("CityMap");
        ScanSpec scanSpec = new ScanSpec();

    	System.out.println("Get Map...");
    	
        ItemCollection<ScanOutcome> items = table.scan(scanSpec);

        Iterator<Item> iter = items.iterator();
        while (iter.hasNext()) {
                                   
            final JSONObject rawData = new JSONObject(iter.next().toJSON());
            
            Iterator<SensorMap> ism = sensors.iterator();
            Boolean found = false;
            while (ism.hasNext()) {
            	
            	SensorMap curr = ism.next();
            	
            	if (curr.getSensorId().equals(rawData.getString("sensor_id"))) {
            		if(curr.getLowerZoneId().compareToIgnoreCase(rawData.getString("zone_id")) > 0) {
            			curr.setUpperZoneId(curr.getLowerZoneId());
            			curr.setLowerZoneId(rawData.getString("zone_id"));
            		} else {
            			curr.setUpperZoneId(rawData.getString("zone_id"));
            		}
            	found = true;
            	} 
            }
            
            if(!found) {
            	SensorMap elem = new SensorMap();
            	elem.setSensorId(rawData.getString("sensor_id"));
            	elem.setLowerZoneId(rawData.getString("zone_id"));
            	sensors.add(elem);
            }
         
            System.out.println("Read " + rawData.getString("zone_id") );
            
        }
                     
        
    } catch (Exception e) {
        System.err.println("Unable to scan the table:");
        System.err.println(e.getMessage());
    }

	return sensors;

}
	
}
