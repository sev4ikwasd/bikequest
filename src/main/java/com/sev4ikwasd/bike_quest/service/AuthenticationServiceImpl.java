package com.sev4ikwasd.bike_quest.service;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.exception.EmailExistsException;
import com.sev4ikwasd.bike_quest.repository.RestUserRepository;
import com.sev4ikwasd.bike_quest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private RestUserRepository restUserRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthenticationServiceImpl(RestUserRepository restUserRepository, RoleRepository roleRepository) {
        this.restUserRepository = restUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public RestUser getUserByUsernameOrEmail(String login) throws UsernameNotFoundException {
        return restUserRepository.getByUsername(login).orElseGet(() -> restUserRepository.getByEmail(login).orElseThrow(() -> new UsernameNotFoundException("No user found with username or email " + login)));
    }

    @Override
    public RestUser registerUser(String username, String email, String password) throws EmailExistsException {
        if (restUserRepository.existsByEmail(email)) {
            throw new EmailExistsException("There is an account with that email address: " + email);
        }
        RestUser user = new RestUser();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(true);

        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").get()));
        return restUserRepository.save(user);
    }
}
