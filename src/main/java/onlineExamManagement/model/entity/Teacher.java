package onlineExamManagement.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Teacher extends User {
    @OneToMany(mappedBy = "examiner")
    private List<Exam> exam;

    public Teacher() {
        super();
    }

    public List<Exam> getExam() {
        return exam;
    }

    public void setExam(List<Exam> exam) {
        this.exam = exam;
    }
}
