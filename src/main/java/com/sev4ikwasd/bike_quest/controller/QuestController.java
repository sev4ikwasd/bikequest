package com.sev4ikwasd.bike_quest.controller;

import com.sev4ikwasd.bike_quest.config.CustomUserDetails;
import com.sev4ikwasd.bike_quest.domain.dto.*;
import com.sev4ikwasd.bike_quest.domain.entity.*;
import com.sev4ikwasd.bike_quest.exception.QuestDoesNotExistException;
import com.sev4ikwasd.bike_quest.exception.UserDoesNotExist;
import com.sev4ikwasd.bike_quest.repository.RestUserRepository;
import com.sev4ikwasd.bike_quest.service.QuestService;
import com.sev4ikwasd.bike_quest.specification.PassedQuestSpecification;
import com.sev4ikwasd.bike_quest.specification.QuestSpecification;
import com.sev4ikwasd.bike_quest.validator.PassedQuestValidator;
import com.sev4ikwasd.bike_quest.validator.QuestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Validated
public class QuestController {
    private QuestService questService;

    private QuestValidator questValidator;
    private PassedQuestValidator passedQuestValidator;

    @InitBinder("createQuest")
    protected void initQuestBinder(WebDataBinder binder) {
        binder.setValidator(questValidator);
    }

    @InitBinder("passQuest")
    protected void initPassedQuestBinder(WebDataBinder binder) {
        binder.setValidator(passedQuestValidator);
    }

    @Autowired
    public QuestController(QuestService questService, QuestValidator questValidator, PassedQuestValidator passedQuestValidator) {
        this.questService = questService;
        this.questValidator = questValidator;
        this.passedQuestValidator = passedQuestValidator;
    }

    @GetMapping("/quest/{questUuid}")
    ResponseEntity getQuest(@PathVariable UUID questUuid) throws QuestDoesNotExistException {
        return new ResponseEntity<>(questService.getQuest(questUuid), HttpStatus.OK);
    }

    @GetMapping("/quests")
    ResponseEntity getAllQuestsBy(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size){
        QuestSpecification.QuestSpecificationsBuilder builder = new QuestSpecification.QuestSpecificationsBuilder();
        //Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(.+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Quest> spec = builder.build();
        return new ResponseEntity<>(questService.getQuests(page, size, spec), HttpStatus.OK);
    }

    @PostMapping("/quest")
    ResponseEntity createQuest(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid QuestCreateRequest questCreateRequest, BindingResult bindingResult) throws BindException, UserDoesNotExist {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        Quest quest = new Quest();

        quest.setName(questCreateRequest.getName());
        quest.setDescription(questCreateRequest.getDescription());
        quest.setDuration(questCreateRequest.getDuration());
        quest.setCity(questCreateRequest.getCity());
        quest.setBgImage(questCreateRequest.getBgImage());

        List<StepCreateRequest> requestSteps = questCreateRequest.getSteps();
        List<Step> steps = new ArrayList<>();

        for (StepCreateRequest requestStep1 : requestSteps) {
            if (requestStep1 instanceof QuestionStepCreateRequest) {
                QuestionStepCreateRequest requestStep = (QuestionStepCreateRequest) requestStep1;
                Step step = new QuestionStep(requestStep.getText(), requestStep.getQuestion(), requestStep.getAnswer());
                steps.add(step);
            } else if (requestStep1 instanceof PlaceStepCreateRequest) {
                PlaceStepCreateRequest requestStep = (PlaceStepCreateRequest) requestStep1;
                Step step = new PlaceStep(requestStep.getText(), requestStep.getLatencyPlaceDot(), requestStep.getLongitudePlaceDot(), requestStep.getPlaceRadius(), requestStep.getShowedLatencyPlaceDot(), requestStep.getShowedLongitudePlaceDot(), requestStep.getShowedPlaceRadius());
                steps.add(step);
            }
        }
        quest.setSteps(steps);
        quest.setStepsNumber(steps.size());
        Quest after = questService.createNewQuest(quest, userDetails.getRestUser().getId());
        return new ResponseEntity<>(after.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/passed_quest/{passedQuestUuid}")
    ResponseEntity getPassedQuest(@PathVariable UUID passedQuestUuid) throws QuestDoesNotExistException {
        return new ResponseEntity<>(questService.getPassedQuest(passedQuestUuid), HttpStatus.OK);
    }

    @GetMapping("/passed_quests")
    ResponseEntity getAllPassedQuestsBy(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size){
        PassedQuestSpecification.PassedQuestSpecificationsBuilder builder = new PassedQuestSpecification.PassedQuestSpecificationsBuilder();
        //Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(.+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<PassedQuest> spec = builder.build();
        return new ResponseEntity<>(questService.getPassedQuests(page, size, spec), HttpStatus.OK);
    }

    @PostMapping("/passed_quest")
    ResponseEntity passQuest(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid PassQuestRequest passQuestRequest, BindingResult bindingResult) throws UserDoesNotExist, QuestDoesNotExistException, BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        UUID uuid = UUID.fromString(passQuestRequest.getId());
        questService.passQuest(uuid, passQuestRequest.getDuration(), userDetails.getRestUser().getId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
