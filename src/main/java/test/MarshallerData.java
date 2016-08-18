package test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "test")
public class MarshallerData {

    @DynamoDBMarshalling(marshallerClass = CustomDataMarshalling.class)
    private CustomData aData;

    private MarshallerSubData subProperty;

    public CustomData getaData() {
        return aData;
    }

    public void setaData(CustomData aData) {
        this.aData = aData;
    }

    public MarshallerSubData getSubProperty() {
        return subProperty;
    }

    public void setSubProperty(MarshallerSubData subProperty) {
        this.subProperty = subProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarshallerData that = (MarshallerData) o;
        return Objects.equals(aData, that.aData) &&
                Objects.equals(subProperty, that.subProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aData, subProperty);
    }
}
