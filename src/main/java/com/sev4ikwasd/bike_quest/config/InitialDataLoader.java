package com.sev4ikwasd.bike_quest.config;

import com.sev4ikwasd.bike_quest.domain.entity.Privilege;
import com.sev4ikwasd.bike_quest.domain.entity.Role;
import com.sev4ikwasd.bike_quest.repository.PrivilegeRepository;
import com.sev4ikwasd.bike_quest.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public InitialDataLoader(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) return;

        Privilege createQuest = createPrivilegeIfNotFound("CREATE_QUEST_PRIVILEGE");
        Privilege passQuest = createPrivilegeIfNotFound("PASS_QUEST_PRIVILEGE");
        Privilege likeQuest = createPrivilegeIfNotFound("LIKE_PRIVILEGE");
        Privilege deleteQuest = createPrivilegeIfNotFound("DELETE_QUEST_PRIVILEGE");
        Privilege disableUser = createPrivilegeIfNotFound("DISABLE_USER_PRIVILEGE");

        List<Privilege> userPrivileges = Arrays.asList(createQuest, passQuest, likeQuest);
        List<Privilege> adminPrivileges = Arrays.asList(createQuest, passQuest, likeQuest, deleteQuest, disableUser);

        createRoleIfNotFound("ROLE_USER", userPrivileges);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege returnValue;
        Optional<Privilege> privilege = privilegeRepository.findByName(name);
        if (!privilege.isPresent()) {
            returnValue = new Privilege();
            returnValue.setName(name);
            privilegeRepository.save(returnValue);
        }
        else {
            returnValue = privilege.get();
            returnValue.setName(name);
        }
        log.info(returnValue.getName());
        return returnValue;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role returnValue;
        Optional<Role> role = roleRepository.findByName(name);
        if (!role.isPresent()) {
            returnValue = new Role();
            returnValue.setName(name);
            returnValue.setPrivileges(privileges);
            roleRepository.save(returnValue);
        }
        else {
            returnValue = role.get();
            returnValue.setName(name);
            returnValue.setPrivileges(privileges);
        }
        log.info(returnValue.getName());
        return returnValue;
    }
}