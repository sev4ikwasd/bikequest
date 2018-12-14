package com.sev4ikwasd.bike_quest.validator;

import com.sev4ikwasd.bike_quest.domain.dto.PassQuestRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PassedQuestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PassQuestRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uuid", "UUID is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "Duration is empty");
    }
}
