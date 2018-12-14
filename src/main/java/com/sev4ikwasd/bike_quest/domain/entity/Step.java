package com.sev4ikwasd.bike_quest.domain.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "steps")
public class Step {

    @Id
    @Type(type = "pg-uuid")
    @Column(name = "step_id")
    @GeneratedValue
    private UUID uuid;

    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quest_id")
    private Quest quest;

    public Step(String text) {
        this.text = text;
    }
}