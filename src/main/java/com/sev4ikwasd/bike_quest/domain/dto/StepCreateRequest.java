package com.sev4ikwasd.bike_quest.domain.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionStepCreateRequest.class, name = "question"),
        @JsonSubTypes.Type(value = PlaceStepCreateRequest.class, name = "place")
})
@Getter
@Setter
@NoArgsConstructor
public class StepCreateRequest {
    @NotNull
    String text;

    public StepCreateRequest(String text) {
        this.text = text;
    }
}
