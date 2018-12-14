package com.sev4ikwasd.bike_quest.service;

import com.sev4ikwasd.bike_quest.domain.entity.PassedQuest;
import com.sev4ikwasd.bike_quest.domain.entity.Quest;
import com.sev4ikwasd.bike_quest.exception.QuestDoesNotExistException;
import com.sev4ikwasd.bike_quest.exception.UserDoesNotExist;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.UUID;

public interface QuestService {
    Quest createNewQuest(Quest quest, UUID creatorUuid) throws UserDoesNotExist;
    void passQuest(UUID questUuid, long duration, UUID passerUuid) throws QuestDoesNotExistException, UserDoesNotExist;
    Quest getQuest(UUID id) throws QuestDoesNotExistException;
    Collection<Quest> getQuests(int page, int size, Specification<Quest> specification);
    PassedQuest getPassedQuest(UUID id) throws QuestDoesNotExistException;
    Collection<PassedQuest> getPassedQuests(int page, int size, Specification<PassedQuest> specification);
}
