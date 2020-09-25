package onlineExamManagement.model.dto;

import onlineExamManagement.model.entity.Classification;
import onlineExamManagement.model.enumeration.questionEnum.QuestionType;

public class QuestionDto {
    private Long id;
    private String subject;
    private String questionContent;
    private Classification classification;
    private QuestionType questionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
