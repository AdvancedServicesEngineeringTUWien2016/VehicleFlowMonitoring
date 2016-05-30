package vfma;

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
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

import org.json.*;


public class LoadSensorData {

	public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
            .withEndpoint("https://dynamodb.us-west-2.amazonaws.com");

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("sensorData");

        ScanSpec scanSpec = new ScanSpec();
        
        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                Item item = iter.next();
//                System.out.println(item.toString());

                RawSensorData rsd = new RawSensorData();
                
                
                
//                final JSONObject rawData = new JSONObject(item));
//                rsd.setTimestamp(rawData.getString("pass"));
//                final JSONObject timedData = new JSONObject(rawData.get("payload"));
//                rsd.setSensorId(timedData.getString("sensor_id_time"));
//                rsd.setPassing(timedData.getString("passing"));
//                
                rsd.setTimestamp(item.getJSON("pass").replace("\"", ""));
                final JSONObject rawData = new JSONObject(item.getJSON("payload"));
                rsd.setSensorId(rawData.getString("sensor_id_time"));
                rsd.setPassing(rawData.getString("passing"));
                
                System.out.println(rsd.getTimestamp());
                System.out.println(rsd.getSensorId());
                System.out.println(rsd.getPassing());
                System.out.println("-------");
                
                
                
            }
            
        } catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }
    }
}