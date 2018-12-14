package com.sev4ikwasd.bike_quest.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotNull
    private String login;

    @NotNull
    private String password;
}
