package com.sev4ikwasd.bike_quest.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question_steps")
public class QuestionStep extends Step {

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    public QuestionStep(String text, String question, String answer) {
        super(text);
        this.question = question;
        this.answer = answer;
    }
}
