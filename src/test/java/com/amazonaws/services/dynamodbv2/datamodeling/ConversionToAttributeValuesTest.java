package com.amazonaws.services.dynamodbv2.datamodeling;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import test.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ConversionToAttributeValuesTest {

    private DynamoDBMapperModelFactory.Factory models;
    private DynamoDBMapperConfig finalConfig;

    @Before
    public void setUp() throws Exception {
        finalConfig = new DynamoDBMapperConfig.Builder()
                .withConversionSchema(ConversionSchemas.V2)
                .build();
        this.models = StandardModelFactories.of(new ConversionSchema.Dependencies());
    }

    private <T> DynamoDBMapperTableModel<T> getTableModel(Class<T> clazz) {
        return this.models.getModelFactory(finalConfig).getTableModel(clazz);
    }

    @Test
    public void marshallingWorksForAllCases() throws Exception {
        DynamoDBMapperTableModel<MarshallerData> tableModel = getTableModel(MarshallerData.class);

        Map<String, AttributeValue> withoutSubData = tableModel.convert(marshallingDataWithoutSubData());
        assertEquals(marshallingDataWithoutSubData(), tableModel.unconvert(withoutSubData));

        Map<String, AttributeValue> withSubData = tableModel.convert(marshallingDataWithSubData());
        assertEquals(marshallingDataWithSubData(), tableModel.unconvert(withSubData));
    }

    @Test
    public void converterWorksForDirectProperty() throws Exception {
        DynamoDBMapperTableModel<ConverterData> tableModel = getTableModel(ConverterData.class);

        Map<String, AttributeValue> withoutSubData = tableModel.convert(converterDataWithoutSubData());
        assertEquals(converterDataWithoutSubData(), tableModel.unconvert(withoutSubData));
    }

    @Test(expected = DynamoDBMappingException.class)
    public void converterFailsForSubProperty() throws Exception {
        DynamoDBMapperTableModel<ConverterData> tableModel = getTableModel(ConverterData.class);

        Map<String, AttributeValue> withSubData = tableModel.convert(converterDataWithSubData());
        assertEquals(converterDataWithSubData(), tableModel.unconvert(withSubData));
    }

    private ConverterData converterDataWithoutSubData(){
        ConverterData d = new ConverterData();
        d.setaData(new CustomData("hello"));
        return d;
    }

    private ConverterData converterDataWithSubData(){
        ConverterData d = converterDataWithoutSubData();
        ConverterSubData sd = new ConverterSubData();
        sd.setaData(new CustomData("world"));
        d.setSubProperty(sd);
        return d;
    }

    private MarshallerData marshallingDataWithoutSubData(){
        MarshallerData d = new MarshallerData();
        d.setaData(new CustomData("hello"));
        return d;
    }

    private MarshallerData marshallingDataWithSubData(){
        MarshallerData d = marshallingDataWithoutSubData();
        MarshallerSubData sd = new MarshallerSubData();
        sd.setaData(new CustomData("world"));
        d.setSubProperty(sd);
        return d;
    }
}
