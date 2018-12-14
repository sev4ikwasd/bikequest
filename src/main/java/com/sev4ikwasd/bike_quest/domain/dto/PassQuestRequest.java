package com.sev4ikwasd.bike_quest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassQuestRequest {
    @NotNull
    String id;

    @NotNull
    long duration;
}
