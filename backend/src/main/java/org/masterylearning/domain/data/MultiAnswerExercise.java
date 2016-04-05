package org.masterylearning.domain.data;


import org.hibernate.annotations.Type;
import org.masterylearning.dto.out.EntryDataOutDto;
import org.masterylearning.dto.out.data.MultiAnswerExerciseDto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity (name = "MultiAnswerExercise")
public class MultiAnswerExercise extends Exercise {

    public String title;

    @Type(type = "text")
    public String text;
    public boolean blocks;

    @OneToMany (cascade = CascadeType.PERSIST)
    public List<AnswerCandidate> answerCandidates;

    public MultiAnswerExercise () {
        this.type = "multianswerexercise";
    }

    @Override
    public EntryDataOutDto toDto () {
        return new MultiAnswerExerciseDto (this);
    }
}
