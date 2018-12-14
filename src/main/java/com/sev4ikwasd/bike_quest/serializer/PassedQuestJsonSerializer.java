package com.sev4ikwasd.bike_quest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sev4ikwasd.bike_quest.domain.entity.PassedQuest;

import java.io.IOException;

public class PassedQuestJsonSerializer extends JsonSerializer<PassedQuest> {
    @Override
    public void serialize(PassedQuest passedQuest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", passedQuest.getId().toString());
        jsonGenerator.writeStringField("passedDate", passedQuest.getPassedDate().toString());
        jsonGenerator.writeNumberField("passDuration", passedQuest.getPassDuration());
        jsonGenerator.writeStringField("questId", passedQuest.getQuest().getId().toString());
        jsonGenerator.writeStringField("passerId", passedQuest.getPasser().getId().toString());
        jsonGenerator.writeEndObject();
    }
}
