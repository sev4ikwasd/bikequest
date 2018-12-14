package com.sev4ikwasd.bike_quest.jpa;

import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.repository.RestUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class RestUserRepositoryTest {
    private String username = "username_test";
    private String email = "email_test";
    private String password = "password_test";
    private RestUser user;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RestUserRepository restUserRepository;

    @Before
    public void init(){
        user = new RestUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user = entityManager.persist(user);
    }

    @Test
    public void getByUsernameTest(){
        assertEquals(restUserRepository.getByUsername(username).get(), user);
    }

    @Test
    public void getByEmailTest(){
        assertEquals(restUserRepository.getByEmail(email).get(), user);
    }
}
