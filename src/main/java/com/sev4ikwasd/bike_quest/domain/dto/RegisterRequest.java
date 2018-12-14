package com.sev4ikwasd.bike_quest.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
