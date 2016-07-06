# VehicleFlowMonitoring

It is a project for a Near-RealTime City Traffic Monitoring System to help drivers avoid jam and find parking spaces easier.
The project has five main components; 
  - an IoT sensor simulator
  - MQTT Client (on AWS)
  - NoSQL DB (on AWS)
  - Java Analytics App
  - API Management (ApiGee)
  
The project in its current form depends on an existing Amazon account.

## The folders

In this section the content of the folders is explained, with information about how to bind the project to an existing AWS account

### ApiGee configurations

XML files configuring the API Management

### AWS Configurations

Contains the AWS Settings, with screeenshots and configflies

### DOCUMENTATION

Presentation and screencast

### Sensors

Contains the C program's source and header files plus libraries for implementing the IoT devices. The ../certs folder must contain the certificate files from AWS

### vfma

The Eclipse project's home folder implementing the analytics

## How to try?

### Requirements

  - An Amazon AWS account (IoT, DynamoDB, Lambda, ApiGateway)
  - Eclipse
  - [ApiGee account]
  
### Steps

  - Start the MQTT Client with client ID 18979 anf topic name 'tf'
  - Run sensors/sample_apps/subscribe_publish_sample/subscribe_publish_sample
  - Open vfma in Eclipse and run the AppRunner class
  - Now the data from the sensors gets into the database and is reachable via:
    - https://fjmizgmco3.execute-api.us-west-2.amazonaws.com/prod/vfmapi OR
    - http://toczee127997-test.apigee.net/vfm/zones (Managed API)
  
  
