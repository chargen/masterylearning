package org.masterylearning.domain.data;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name = "AnswerCandidate")
public class AnswerCandidate {

    @Id
    @GeneratedValue
    public Long id;

    @Type(type = "text")
    public String text;

    public boolean correct;
}
