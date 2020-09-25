package onlineExamManagement.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Classification classification;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "course")
    private List<Exam> exams;

    public Long getId() {
        return id;
    }

    @Column(name = "id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Column(name = "title", unique = true, nullable = false)
    public void setTitle(String title) {
        this.title = title;
    }

    public Classification getClassification() {
        return classification;
    }

    @Column(name = "classification", nullable = false)
    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public List<User> getUsers() {
        return users;
    }

    @Column(name = "users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Exam> getExams() {
        return exams;
    }

    @Column(name = "exams")
    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
