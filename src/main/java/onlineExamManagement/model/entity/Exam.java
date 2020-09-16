package onlineExamManagement.model.entity;

import onlineExamManagement.model.enumeration.examEnum.ExamStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float totalScore;
    private String subject;
    private String moreInfo;
    private Date startDate;
    private Date endDate;
    private int duration;
    private ExamStatus status;
    @ManyToOne
    private Teacher examiner;
    @ManyToOne
    private Course course;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    private List<Question> questions;
    @OneToMany(mappedBy = "exam")
    List<StudentScore> scores;

    public Long getId() {
        return id;
    }

    @Column(name = "id")
    public void setId(Long id) {
        this.id = id;
    }

    public float getTotalScore() {
        return totalScore;
    }

    @Column(name = "totalScore")
    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public String getSubject() {
        return subject;
    }

    @Column(name = "subject", nullable = false)
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Column(name = "startDate", nullable = false)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Column(name = "endDate", nullable = false)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    @Column(name = "duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    @Column(name = "moreInfo")
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Teacher getExaminer() {
        return examiner;
    }

    public ExamStatus getStatus() {
        return status;
    }

    @Column(name = "status")
    public void setStatus(ExamStatus status) {
        this.status = status;
    }

    @Column(name = "examiner", nullable = false)
    public void setExaminer(Teacher examiner) {
        this.examiner = examiner;
    }

    public Course getCourse() {
        return course;
    }

    @Column(name = "courses")
    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Column(name = "questions")
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<StudentScore> getScores() {
        return scores;
    }

    public void setScores(List<StudentScore> scores) {
        this.scores = scores;
    }
}
