package com.sev4ikwasd.bike_quest.service;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.exception.UserDoesNotExist;
import com.sev4ikwasd.bike_quest.repository.RestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private RestUserRepository restUserRepository;

    @Autowired
    public UserServiceImpl(RestUserRepository restUserRepository) {
        this.restUserRepository = restUserRepository;
    }

    @Override
    public RestUser getUser(UUID id) throws UserDoesNotExist {
        return restUserRepository.findById(id).orElseThrow(() -> new UserDoesNotExist("User doesn't exist"));
    }
}
