package test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class CustomDataConverter implements DynamoDBTypeConverter<String, CustomData> {

    public String convert(CustomData object) {
        return object.getValue();
    }

    public CustomData unconvert(String object) {
        return new CustomData(object);
    }
}
