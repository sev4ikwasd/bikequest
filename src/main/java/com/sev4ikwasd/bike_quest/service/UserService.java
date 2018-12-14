package com.sev4ikwasd.bike_quest.service;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.exception.UserDoesNotExist;

import java.util.UUID;

public interface UserService {
    RestUser getUser(UUID id) throws UserDoesNotExist;
}
