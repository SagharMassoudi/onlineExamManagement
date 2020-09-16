package onlineExamManagement.model.entity;

import onlineExamManagement.model.enumeration.questionEnum.QuestionType;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float point;
    private String subject;
    private String questionContent;
    private QuestionType questionType;
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private Classification classification;

    public Long getId() {
        return id;
    }

    @Column(name = "id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    @Column(name = "subject", nullable = false)
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    @Column(name = "questionContent", nullable = false)
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public float getPoint() {
        return point;
    }

    @Column(name = "point", nullable = false)
    public void setPoint(float point) {
        this.point = point;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Classification getClassification() {
        return classification;
    }

    @Column(name = "classification", nullable = false)
    public void setClassification(Classification classification) {
        this.classification = classification;
    }

}
