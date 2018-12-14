package com.sev4ikwasd.bike_quest.service;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.exception.EmailExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthenticationService {
    RestUser getUserByUsernameOrEmail(String login) throws UsernameNotFoundException;
    RestUser registerUser(String username, String email, String password) throws EmailExistsException;
}
