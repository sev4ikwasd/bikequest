package com.sev4ikwasd.bike_quest.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoResponse {
    String id;
    String email;
    String username;
    Collection<String> createdQuestsUuid;
    Collection<String> passedQuestsUuid;
}
