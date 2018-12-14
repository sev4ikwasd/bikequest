package com.sev4ikwasd.bike_quest.repository;

import com.sev4ikwasd.bike_quest.domain.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StepRepository extends JpaRepository<Step, UUID> {
}
