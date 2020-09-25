package onlineExamManagement.service;

import onlineExamManagement.comparator.SortExamsById;
import onlineExamManagement.model.dao.ExamDao;
import onlineExamManagement.model.dto.ExamDto;
import onlineExamManagement.model.entity.Course;
import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.entity.Question;
import onlineExamManagement.model.entity.Student;
import onlineExamManagement.model.enumeration.examEnum.ExamStatus;
import onlineExamManagement.model.enumeration.questionEnum.QuestionType;
import onlineExamManagement.utility.ExamUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ExamService {
    @Autowired
    ExamDao examDao;

    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentAnswerService studentAnswerService;
    @Autowired
    QuestionService questionService;

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

    public void calculateTotalScore(Exam exam, double point) {
        float totalScore = exam.getTotalScore();
        totalScore += point;
        exam.setTotalScore(totalScore);
    }

    public List<ExamDto> getInProgressExams(Course course, String email) {
        List<Exam> allExams = course.getExams();
        List<Exam> finishedExams = extractFinishedExamsFromMap(email);
        for (Exam finishedExam : finishedExams) {
            Iterator<Exam> iterator = allExams.iterator();
            while (iterator.hasNext()) {
                Exam exam = iterator.next();
                if (finishedExam.getId().equals(exam.getId()))
                    allExams.remove(exam);
            }
        }
        return examUtility.prepareExamDtoList(allExams);
    }

    public List<Exam> extractFinishedExamsFromMap(String email) {
        Student student = studentService.findStudentByEmail(email);
        Map<Exam, Double> examScoreMap = student.getExamScoreMap();
        List<Exam> studentFinishedExams = new ArrayList<>(examScoreMap.keySet());
        for (int i = 0; i < studentFinishedExams.size(); i++) {
            studentFinishedExams.sort(new SortExamsById());
        }
        return studentFinishedExams;
    }

    public java.util.Date getExamStartTime(Student student, Exam exam) {
        Map<Student, java.util.Date> studentDateMap = exam.getStudentDateMap();
        java.util.Date date = null;
        for (Map.Entry<Student, java.util.Date> entry : studentDateMap.entrySet()) {
            Student key = entry.getKey();
            if (key.getId().equals(student.getId())) {
                date = entry.getValue();
            }
        }
        return date;
    }
    public boolean examContainsEssayQuestions(long examId) {
        Exam exam = findExamById(examId);
        List<Question> questions = questionService.extractQuestionsFromMap(exam);
        long counter = 0;
        for (Question question : questions) {
            if (question.getQuestionType().equals(QuestionType.EssayQuestion))
                counter++;
        }
        if (counter == 0)
            return false;
        return true;
    }
}
