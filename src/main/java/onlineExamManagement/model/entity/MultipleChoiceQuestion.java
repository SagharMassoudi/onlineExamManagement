package onlineExamManagement.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MultipleChoiceQuestion extends Question {
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    @Column(name = "answers")
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
