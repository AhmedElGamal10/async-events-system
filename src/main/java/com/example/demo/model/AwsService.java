package com.example.demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "awsServices")
public class AwsService {

    private String id;
    private String serviceName;
    private String serviceDescription;
    private String serviceHomePageUrl;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @DynamoDBAttribute
    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    @DynamoDBAttribute
    public String getServiceHomePageUrl() {
        return serviceHomePageUrl;
    }

    public void setServiceHomePageUrl(String serviceHomePageUrl) {
        this.serviceHomePageUrl = serviceHomePageUrl;
    }
}
