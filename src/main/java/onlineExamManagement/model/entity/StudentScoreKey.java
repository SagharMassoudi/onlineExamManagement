package onlineExamManagement.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudentScoreKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;
    @Column(name = "exam_id")
    Long examId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }
}
