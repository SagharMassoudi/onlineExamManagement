package onlineExamManagement.model.dto;

import onlineExamManagement.model.entity.Classification;
import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.entity.User;

import java.util.List;

public class CourseDto {
    private Long id;
    private String title;
    private Classification classification;
    private List<User> users;
    private List<Exam> exams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
