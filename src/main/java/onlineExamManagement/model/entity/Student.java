package onlineExamManagement.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends User{
    public Student() {
        super();
    }

    @OneToMany(mappedBy = "student")
    List<StudentScore> scores;

    public List<StudentScore> getScores() {
        return scores;
    }

    public void setScores(List<StudentScore> scores) {
        this.scores = scores;
    }
}
