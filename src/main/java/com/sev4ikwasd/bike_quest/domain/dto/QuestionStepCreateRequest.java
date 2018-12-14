package com.sev4ikwasd.bike_quest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class QuestionStepCreateRequest extends StepCreateRequest {
    @NotNull
    String question;

    @NotNull
    String answer;

    public QuestionStepCreateRequest(String text, String question, String answer) {
        super(text);
        this.question = question;
        this.answer = answer;
    }
}
