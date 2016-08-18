package test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;

import java.util.Objects;

@DynamoDBDocument
public class MarshallerSubData {

    @DynamoDBMarshalling(marshallerClass = CustomDataMarshalling.class)
    private CustomData aData;

    public CustomData getaData() {
        return aData;
    }

    public void setaData(CustomData aData) {
        this.aData = aData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarshallerSubData that = (MarshallerSubData) o;
        return Objects.equals(aData, that.aData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aData);
    }
}
