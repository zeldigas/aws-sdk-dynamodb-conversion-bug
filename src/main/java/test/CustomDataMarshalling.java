package test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;

public class CustomDataMarshalling implements DynamoDBMarshaller<CustomData> {
    public String marshall(CustomData getterReturnResult) {
        return getterReturnResult.getValue();
    }

    public CustomData unmarshall(Class<CustomData> clazz, String obj) {
        return new CustomData(obj);
    }
}
