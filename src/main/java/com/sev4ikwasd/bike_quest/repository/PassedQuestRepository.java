package com.sev4ikwasd.bike_quest.repository;

import com.sev4ikwasd.bike_quest.domain.entity.PassedQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassedQuestRepository extends JpaRepository<PassedQuest, UUID>, JpaSpecificationExecutor<PassedQuest> {
}
