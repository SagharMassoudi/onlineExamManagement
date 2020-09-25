package onlineExamManagement.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends User{
    public Student() {
        super();
    }

    @ElementCollection
    @MapKeyJoinColumn(name = "exam_id")
    @Column(name = "studentScore")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<Exam, Double> examScoreMap;

    public Map<Exam, Double> getExamScoreMap() {
        return examScoreMap;
    }

    public void setExamScoreMap(Map<Exam, Double> examScoreMap) {
        this.examScoreMap = examScoreMap;
    }
}
