package com.sev4ikwasd.bike_quest.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sev4ikwasd.bike_quest.serializer.CustomDateTimeSerializer;
import com.sev4ikwasd.bike_quest.serializer.PassedQuestJsonSerializer;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.UUID;

@JsonSerialize(using = PassedQuestJsonSerializer.class)
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passed_quests")
public class PassedQuest {

    @Id
    @Type(type = "pg-uuid")
    @Column(name = "passed_quest_id")
    @GeneratedValue
    private UUID id;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTimeAsUtcString")
    @Column(name = "passed_date")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private DateTime passedDate;

    @Column(name = "pass_duration")
    private long passDuration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private RestUser passer;
}
