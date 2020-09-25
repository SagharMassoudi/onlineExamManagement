package onlineExamManagement.model.dto;

import onlineExamManagement.model.entity.Course;
import onlineExamManagement.model.entity.Question;
import onlineExamManagement.model.entity.Teacher;
import onlineExamManagement.model.enumeration.examEnum.ExamStatus;

import java.sql.Date;
import java.util.Map;

public class ExamDto {
    private Long id;
    private float totalScore;
    private String subject;
    private String moreInfo;
    private Date startDate;
    private Date endDate;
    private int duration;
    private ExamStatus status;
    private Teacher examiner;
    private Course course;
    private Map<Question,Double> questionPointMap;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ExamStatus getStatus() {
        return status;
    }

    public void setStatus(ExamStatus status) {
        this.status = status;
    }

    public Teacher getExaminer() {
        return examiner;
    }

    public void setExaminer(Teacher examiner) {
        this.examiner = examiner;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Map<Question, Double> getQuestionPointMap() {
        return questionPointMap;
    }

    public void setQuestionPointMap(Map<Question, Double> questionPointMap) {
        this.questionPointMap = questionPointMap;
    }
}
