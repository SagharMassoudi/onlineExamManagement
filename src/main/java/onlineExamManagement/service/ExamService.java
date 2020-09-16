package onlineExamManagement.service;

import onlineExamManagement.model.dao.ExamDao;
import onlineExamManagement.model.dto.ExamDto;
import onlineExamManagement.model.entity.Course;
import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.entity.StudentScore;
import onlineExamManagement.model.enumeration.examEnum.ExamStatus;
import onlineExamManagement.utility.ExamUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Service
public class ExamService {
    @Autowired
    ExamDao examDao;
    @Autowired
    CourseService courseService;
    ExamUtility examUtility = new ExamUtility();


    @Transactional
    public List<Exam> getCourseExams(Course course) {
        List<Exam> examList = examDao.findByCourse(course);
        syncExamStatus(examList);
        return examList;
    }

    @Transactional
    public void addNewExamToCourse(ExamDto examDto) {
        Exam exam = examUtility.prepareExam(examDto);
        examDao.save(exam);
    }

    public Exam findExamById(long id) {
        return examDao.findById(id);
    }

    @Transactional
    public void updateExamStatus(Exam exam) {
        Long id = exam.getId();
        examDao.updateExamStatus(id, exam.getStatus());
    }

    @Transactional
    public void editExam(Exam exam) {
        Long id = exam.getId();
        int duration = exam.getDuration();
        String subject = exam.getSubject();
        Date startDate = exam.getStartDate();
        Date endDate = exam.getEndDate();
        String moreInfo = exam.getMoreInfo();
        examDao.updateExam(id, subject, startDate, endDate, duration, moreInfo);
    }

    public boolean timeIsUp(Exam exam) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = exam.getStartDate().toLocalDate();
        LocalDate endDate = exam.getEndDate().toLocalDate();
        if (currentDate.isBefore(startDate) || currentDate.isBefore(endDate))
            return false;
        else
            return true;
    }

    public boolean timeIsOverlapping(Exam exam) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = exam.getStartDate().toLocalDate();
        LocalDate endDate = exam.getEndDate().toLocalDate();
        if (currentDate.isBefore(startDate) || currentDate.isAfter(endDate))
            return false;
        else
            return true;
    }

    @Transactional
    public void deleteExam(Exam exam) {
        examDao.delete(exam);
    }

    @Transactional
    public void updateExam(Exam exam) {
        examDao.save(exam);
    }

    public void syncExamStatus(List<Exam> examList) {
        for (Exam exam : examList) {
            ExamStatus status = exam.getStatus();
            if (!status.equals(ExamStatus.Cancelled)) {
                if (timeIsUp(exam)) {
                    exam.setStatus(ExamStatus.Finished);
                } else if (timeIsOverlapping(exam)) {
                    exam.setStatus(ExamStatus.InProgress);
                }
                updateExamStatus(exam);
            }
        }
    }

    public void calculateTotalScore(Exam exam, float point) {
        float totalScore = exam.getTotalScore();
        totalScore += point;
        exam.setTotalScore(totalScore);
    }

    public List<ExamDto> getInProgressExams(List<StudentScore> studentScores, List<Exam> exams) {
        Iterator<StudentScore> iterator = studentScores.iterator();
        while (iterator.hasNext()) {
            StudentScore studentScore = iterator.next();
            long finishedExamId = studentScore.getExam().getId();
            for (Exam exam : exams) {
                long examId = exam.getId();
                if (finishedExamId == examId || timeIsUp(exam)) {
                    exams.remove(exam);
                }
            }
        }
        return examUtility.prepareExamDtoList(exams);
    }
}
