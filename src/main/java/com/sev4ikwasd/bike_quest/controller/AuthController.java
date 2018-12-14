package com.sev4ikwasd.bike_quest.controller;

import com.sev4ikwasd.bike_quest.domain.dto.LoginRequest;
import com.sev4ikwasd.bike_quest.domain.dto.RegisterRequest;
import com.sev4ikwasd.bike_quest.exception.EmailExistsException;
import com.sev4ikwasd.bike_quest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Validated
public class AuthController {
    private AuthenticationManager authenticationManager;
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        String username = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = "application/json;charset=UTF-8")
    ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest, BindingResult bindingResult) throws EmailExistsException, BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        authenticationService.registerUser(registerRequest.getUsername(),
            registerRequest.getEmail(),
            registerRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}