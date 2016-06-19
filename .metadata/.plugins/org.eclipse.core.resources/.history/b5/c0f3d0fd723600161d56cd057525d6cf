package vfma;

import java.util.List;
import java.util.ArrayList;
//Copyright 2012-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
//Licensed under the Apache License, Version 2.0.
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;


import org.json.*;


public class LoadSensorData {

	public ArrayList<RawSensorData> getSensorData(String lastTimestamp) {
		
		ArrayList<RawSensorData> rsdlist = new ArrayList<RawSensorData>();	
		
		try {
		
		AmazonDynamoDBClient client = new AmazonDynamoDBClient()
            .withEndpoint("https://dynamodb.us-west-2.amazonaws.com");
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("sensorData");
        ScanSpec scanSpec = new ScanSpec();
        	
       	System.out.println("Get sensors...");
        ItemCollection<ScanOutcome> items = table.scan(scanSpec);

        String lastTimeValue;
        
        Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                Item item = iter.next();

                RawSensorData rsd = new RawSensorData();

                rsd.setTimestamp(item.getJSON("pass").replace("\"", ""));
                final JSONObject rawData = new JSONObject(item.getJSON("payload"));
                rsd.setSensorId(rawData.getString("sensor_id_time"));
                rsd.setPassing(rawData.getString("passing"));
                
                System.out.println("Read " + rsd.getSensorId() );
                
                
                if(rsd.getTimestamp().compareTo(lastTimestamp) > 0)
                	rsdlist.add(rsd);
                
                lastTimeValue = rsd.getTimestamp();
            }

    		
            
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }
		
		
		return rsdlist;
	}
}

