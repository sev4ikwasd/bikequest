package com.sev4ikwasd.bike_quest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestCreateRequest {
    @NotNull
    String name;

    @NotNull
    String description;

    @NotNull
    long duration;

    @NotNull
    String city;

    //@JsonProperty("bg_image")
    String bgImage;

    @NotNull
    List<StepCreateRequest> steps;
}
