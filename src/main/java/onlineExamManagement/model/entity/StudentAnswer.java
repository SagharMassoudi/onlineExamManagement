package onlineExamManagement.model.entity;

import onlineExamManagement.model.enumeration.studentAnswerEnum.StudentAnswerStatus;

import javax.persistence.*;

@Entity
@Table(name = "student_answer")
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Student student;
    @OneToOne
    private Question question;
    @OneToOne
    private Exam exam;
    @Column(nullable = true)
    private String answer;
    private Double point;
    private StudentAnswerStatus answerStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student students) {
        this.student = students;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public StudentAnswerStatus getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(StudentAnswerStatus answerStatus) {
        this.answerStatus = answerStatus;
    }
}
