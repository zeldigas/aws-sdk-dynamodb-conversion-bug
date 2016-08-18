package test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.Objects;

@DynamoDBTable(tableName = "test")
public class ConverterData {

    @DynamoDBTypeConverted(converter = CustomDataConverter.class)
    private CustomData aData;

    private ConverterSubData subProperty;

    public CustomData getaData() {
        return aData;
    }

    public void setaData(CustomData aData) {
        this.aData = aData;
    }

    public ConverterSubData getSubProperty() {
        return subProperty;
    }

    public void setSubProperty(ConverterSubData subProperty) {
        this.subProperty = subProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConverterData that = (ConverterData) o;
        return Objects.equals(aData, that.aData) &&
                Objects.equals(subProperty, that.subProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aData, subProperty);
    }
}
