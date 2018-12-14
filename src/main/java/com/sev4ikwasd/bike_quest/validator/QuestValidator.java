package com.sev4ikwasd.bike_quest.validator;


import com.sev4ikwasd.bike_quest.domain.dto.PlaceStepCreateRequest;
import com.sev4ikwasd.bike_quest.domain.dto.QuestCreateRequest;
import com.sev4ikwasd.bike_quest.domain.dto.QuestionStepCreateRequest;
import com.sev4ikwasd.bike_quest.domain.dto.StepCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class QuestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return QuestCreateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        QuestCreateRequest request = (QuestCreateRequest) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Name is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Description is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "Duration is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "City is empty");

        if(request.getSteps().isEmpty()){
            errors.rejectValue("steps", "Steps are empty");
        }

        /*request.getName();
        request.getDescription();
        request.getDescription();
        request.getCity();
        request.getBgImage();
        for(StepCreateRequest step : request.getSteps()){
            step.getText();
            if(step instanceof QuestionStepCreateRequest){
                ((QuestionStepCreateRequest) step).getAnswer();
                ((QuestionStepCreateRequest) step).getQuestion();
            }
            else if(step instanceof PlaceStepCreateRequest){
                double f = ((PlaceStepCreateRequest) step).getLatencyPlaceDot();
                f = ((PlaceStepCreateRequest) step).getLongitudePlaceDot();
                f = ((PlaceStepCreateRequest) step).getPlaceRadius();
                f = ((PlaceStepCreateRequest) step).getShowedLatencyPlaceDot();
                f = ((PlaceStepCreateRequest) step).getShowedLongitudePlaceDot();
                f = ((PlaceStepCreateRequest) step).getShowedPlaceRadius();
            }
        }*/
    }
}
