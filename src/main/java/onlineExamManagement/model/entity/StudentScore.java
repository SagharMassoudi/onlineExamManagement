package onlineExamManagement.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "exam_student")
public class StudentScore {
    @EmbeddedId
    StudentScoreKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;

    double score;

    public StudentScoreKey getId() {
        return id;
    }

    public void setId(StudentScoreKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
