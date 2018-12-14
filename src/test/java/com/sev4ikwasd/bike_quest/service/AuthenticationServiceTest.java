package com.sev4ikwasd.bike_quest.service;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.exception.EmailExistsException;
import com.sev4ikwasd.bike_quest.repository.RestUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {
    private String username = "username_test";
    private String email = "email_test";
    private String password = "password_test";
    private RestUser user;

    @Mock
    RestUserRepository restUserRepository;

    @InjectMocks
    AuthenticationServiceImpl userService;

    @Before
    public void init(){
        user = new RestUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
    }

    @Test
    public void getUserByUsernameTest(){
        when(restUserRepository.getByUsername(username)).thenReturn(Optional.of(user));
        assertEquals(userService.getUserByUsernameOrEmail(username), user);
    }

    @Test
    public void getUserByEmailTest(){
        when(restUserRepository.getByEmail(email)).thenReturn(Optional.of(user));
        assertEquals(userService.getUserByUsernameOrEmail(email), user);
    }

    @Test
    public void registerUserTest() throws EmailExistsException {
        assertEquals(userService.registerUser(username, email, password), true);
    }
}
