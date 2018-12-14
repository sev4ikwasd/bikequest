package com.sev4ikwasd.bike_quest.repository;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestUserRepository extends JpaRepository<RestUser, UUID> {
    Optional<RestUser> getByUsername(String username);
    Optional<RestUser> getByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
