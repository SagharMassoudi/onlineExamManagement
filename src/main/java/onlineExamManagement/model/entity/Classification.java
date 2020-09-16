package onlineExamManagement.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "classification")
    private List<Course> courses;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    private List<Question> questionList;

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

    @Column(name = "title",unique = true,nullable = false)
    public void setTitle(String title) {
        this.title = title;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Column(name = "courses")
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
