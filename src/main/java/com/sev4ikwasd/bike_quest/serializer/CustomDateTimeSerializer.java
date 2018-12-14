package com.sev4ikwasd.bike_quest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class CustomDateTimeSerializer extends JsonSerializer<DateTime> {
    //private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //jsonGenerator.writeString(formatter.print(dateTime));
        jsonGenerator.writeString(dateTime.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
    }
}
