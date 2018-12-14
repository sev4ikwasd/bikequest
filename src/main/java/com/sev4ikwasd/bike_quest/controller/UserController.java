package com.sev4ikwasd.bike_quest.controller;

import com.sev4ikwasd.bike_quest.config.CustomUserDetails;
import com.sev4ikwasd.bike_quest.domain.dto.UserInfoResponse;
import com.sev4ikwasd.bike_quest.domain.entity.PassedQuest;
import com.sev4ikwasd.bike_quest.domain.entity.Quest;
import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.exception.UserDoesNotExist;
import com.sev4ikwasd.bike_quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable UUID id) throws UserDoesNotExist {
        if(userDetails.getRestUser().getId().equals(id)){
            RestUser user = userService.getUser(id);
            UserInfoResponse response = new UserInfoResponse();
            response.setId(id.toString());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            List<String> createdQuestsUuid = new ArrayList<>();
            for(Quest quest : user.getCreatedQuests()){
                createdQuestsUuid.add(quest.getId().toString());
            }
            response.setCreatedQuestsUuid(createdQuestsUuid);
            List<String> passedQuestsUuid = new ArrayList<>();
            for(PassedQuest passedQuest : user.getPassedQuest()){
                passedQuestsUuid.add(passedQuest.getId().toString());
            }
            response.setPassedQuestsUuid(passedQuestsUuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        RestUser user = userService.getUser(id);
        UserInfoResponse response = new UserInfoResponse();
        response.setId(id.toString());
        response.setUsername(user.getUsername());
        List<String> createdQuestsUuid = new ArrayList<>();
        for(Quest quest : user.getCreatedQuests()){
            createdQuestsUuid.add(quest.getId().toString());
        }
        response.setCreatedQuestsUuid(createdQuestsUuid);
        List<String> passedQuestsUuid = new ArrayList<>();
        for(PassedQuest passedQuest : user.getPassedQuest()){
            passedQuestsUuid.add(passedQuest.getId().toString());
        }
        response.setPassedQuestsUuid(passedQuestsUuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
