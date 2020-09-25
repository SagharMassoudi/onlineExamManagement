package onlineExamManagement.model.entity;

import onlineExamManagement.model.enumeration.examEnum.ExamStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.Map;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float totalScore;
    private String subject;
    private String moreInfo;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int duration;
    private ExamStatus status;

    @ManyToOne
    private Teacher examiner;

    @ManyToOne
    private Course course;

    @ElementCollection
    @MapKeyJoinColumn(name = "question_id")
    @Column(name = "questionPoint")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<Question, Double> questionPointMap;

    @ElementCollection
    @MapKeyJoinColumn(name = "student_id")
    @Column(name = "start_time")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<Student, java.util.Date> studentDateMap;

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

    public java.sql.Date getStartDate() {
        return startDate;
    }

    @Column(name = "startDate", nullable = false)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
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

    public Map<Question, Double> getQuestionPointMap() {
        return questionPointMap;
    }

    public void setQuestionPointMap(Map<Question, Double> questionPointMap) {
        this.questionPointMap = questionPointMap;
    }

    public Map<Student, java.util.Date> getStudentDateMap() {
        return studentDateMap;
    }

    public void setStudentDateMap(Map<Student, java.util.Date> studentDateMap) {
        this.studentDateMap = studentDateMap;
    }
}
