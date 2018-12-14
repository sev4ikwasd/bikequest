package com.sev4ikwasd.bike_quest.service;

import com.sev4ikwasd.bike_quest.domain.entity.PassedQuest;
import com.sev4ikwasd.bike_quest.domain.entity.Quest;
import com.sev4ikwasd.bike_quest.domain.entity.Step;
import com.sev4ikwasd.bike_quest.exception.QuestDoesNotExistException;
import com.sev4ikwasd.bike_quest.exception.UserDoesNotExist;
import com.sev4ikwasd.bike_quest.repository.PassedQuestRepository;
import com.sev4ikwasd.bike_quest.repository.QuestRepository;
import com.sev4ikwasd.bike_quest.repository.RestUserRepository;
import com.sev4ikwasd.bike_quest.repository.StepRepository;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
public class QuestServiceImpl implements QuestService {

    private QuestRepository questRepository;
    private PassedQuestRepository passedQuestRepository;
    private StepRepository stepRepository;
    private RestUserRepository restUserRepository;

    @Autowired
    public QuestServiceImpl(QuestRepository questRepository, PassedQuestRepository passedQuestRepository, StepRepository stepRepository, RestUserRepository restUserRepository) {
        this.questRepository = questRepository;
        this.passedQuestRepository = passedQuestRepository;
        this.stepRepository = stepRepository;
        this.restUserRepository = restUserRepository;
    }

    @Override
    public Quest createNewQuest(Quest quest, UUID creatorUuid) throws UserDoesNotExist {
        quest.setCreationDate(LocalDateTime.now().toDateTime(DateTimeZone.UTC));
        quest.setCreator(restUserRepository.findById(creatorUuid).orElseThrow(() -> new UserDoesNotExist("User does not exist")));
        Quest afterSave = questRepository.save(quest);
        Collection<Step> steps = new ArrayList<>(quest.getSteps());
        for(Step step : steps){
            step.setQuest(afterSave);
        }
        stepRepository.saveAll(steps);
        return afterSave;
    }

    @Override
    public void passQuest(UUID questUuid, long duration, UUID passerUuid) throws QuestDoesNotExistException, UserDoesNotExist {
        PassedQuest passedQuest = new PassedQuest();
        passedQuest.setQuest(getQuest(questUuid));
        passedQuest.setPasser(restUserRepository.findById(passerUuid).orElseThrow(() -> new UserDoesNotExist("User does not exist")));
        passedQuest.setPassedDate(LocalDateTime.now().toDateTime(DateTimeZone.UTC));
        passedQuest.setPassDuration(duration);
        passedQuestRepository.save(passedQuest);
    }

    @Override
    public Quest getQuest(UUID id) throws QuestDoesNotExistException {
        return questRepository.findById(id).orElseThrow(() -> new QuestDoesNotExistException("Quest doesn't exist"));
    }

    @Override
    public Collection<Quest> getQuests(int page, int size, Specification<Quest> specification) {
        Page<Quest> pageResp = questRepository.findAll(specification, PageRequest.of(page, size, new Sort(Sort.Direction.DESC, "creationDate")));
        return pageResp.getContent();
    }

    @Override
    public PassedQuest getPassedQuest(UUID id) throws QuestDoesNotExistException {
        return passedQuestRepository.findById(id).orElseThrow(() -> new QuestDoesNotExistException("Quest doesn't exist"));
    }

    @Override
    public Collection<PassedQuest> getPassedQuests(int page, int size, Specification<PassedQuest> specification) {
        Page<PassedQuest> pageResp = passedQuestRepository.findAll(specification, PageRequest.of(page, size, new Sort(Sort.Direction.DESC, "passedDate")));
        return pageResp.getContent();
    }
}
