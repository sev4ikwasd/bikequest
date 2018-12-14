package com.sev4ikwasd.bike_quest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sev4ikwasd.bike_quest.domain.entity.PlaceStep;
import com.sev4ikwasd.bike_quest.domain.entity.Quest;
import com.sev4ikwasd.bike_quest.domain.entity.QuestionStep;
import com.sev4ikwasd.bike_quest.domain.entity.Step;

import java.io.IOException;

public class QuestJsonSerializer extends JsonSerializer<Quest> {

    @Override
    public void serialize(Quest quest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", quest.getId().toString());
        jsonGenerator.writeStringField("name", quest.getName());
        jsonGenerator.writeStringField("description", quest.getDescription());
        jsonGenerator.writeStringField("bgImage", quest.getBgImage());
        jsonGenerator.writeStringField("city", quest.getCity());
        jsonGenerator.writeNumberField("duration", quest.getDuration());
        jsonGenerator.writeStringField("creationDate", quest.getCreationDate().toString());
        jsonGenerator.writeStringField("creator", quest.getCreator().getId().toString());
        jsonGenerator.writeNumberField("stepsNumber", quest.getStepsNumber());
        jsonGenerator.writeArrayFieldStart("steps");
        for(Step step : quest.getSteps()){
            if(step instanceof QuestionStep){
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("text", step.getText());
                jsonGenerator.writeStringField("question", ((QuestionStep) step).getQuestion());
                jsonGenerator.writeStringField("answer", ((QuestionStep) step).getAnswer());
                jsonGenerator.writeEndObject();
            }
            else if(step instanceof PlaceStep){
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("text", step.getText());
                jsonGenerator.writeNumberField("latencyPlaceDot", ((PlaceStep) step).getLatencyPlaceDot());
                jsonGenerator.writeNumberField("longitudePlaceDot", ((PlaceStep) step).getLongitudePlaceDot());
                jsonGenerator.writeNumberField("placeRadius", ((PlaceStep) step).getPlaceRadius());
                jsonGenerator.writeNumberField("showedLatencyPlaceDot", ((PlaceStep) step).getShowedLatencyPlaceDot());
                jsonGenerator.writeNumberField("showedLongitudePlaceDot", ((PlaceStep) step).getShowedLongitudePlaceDot());
                jsonGenerator.writeNumberField("showedPlaceRadius", ((PlaceStep) step).getShowedPlaceRadius());
                jsonGenerator.writeEndObject();
            }
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
