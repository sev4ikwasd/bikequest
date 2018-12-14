package com.sev4ikwasd.bike_quest.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sev4ikwasd.bike_quest.serializer.CustomDateTimeSerializer;
import com.sev4ikwasd.bike_quest.serializer.QuestJsonSerializer;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@JsonSerialize(using = QuestJsonSerializer.class)
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quests")
public class Quest {

    @Id
    @Type(type = "pg-uuid")
    @Column(name = "quest_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name="bg_image")
    private String bgImage;

    @Column(name = "city")
    private String city;

    @Column(name = "duration")
    private long duration;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTimeAsUtcString")
    @Column(name = "creation_date")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private DateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RestUser creator;

    @Column(name = "steps_number")
    private int stepsNumber;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    private Collection<Step> steps;
}
