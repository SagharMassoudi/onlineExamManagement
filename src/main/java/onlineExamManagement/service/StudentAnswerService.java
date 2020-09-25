package onlineExamManagement.service;

import com.mysql.cj.util.StringUtils;
import onlineExamManagement.comparator.SortStudentAnswersById;
import onlineExamManagement.model.dao.StudentAnswerDao;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.studentAnswerEnum.StudentAnswerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StudentAnswerService {
    @Autowired
    StudentAnswerDao studentAnswerDao;

    @Autowired
    ExamService examService;

    @Autowired
    StudentService studentService;

    @Autowired
    QuestionService questionService;

    public StudentAnswer findCurrentStudentAnswer(Exam exam, Question question, Student student) {
        return studentAnswerDao.findByExamAndQuestionAndStudent(exam, question, student);
    }


    @Transactional
    public void updateStudentAnswerPoint(StudentAnswer studentAnswer, Double point) {
        studentAnswer.setPoint(point);
        studentAnswer.setAnswerStatus(StudentAnswerStatus.Checked);
        studentAnswerDao.save(studentAnswer);
    }

    @Transactional
    public void updateQuestionAnswer(StudentAnswer studentAnswer, String answer) {
        studentAnswerDao.updateAnswer(answer, studentAnswer.getQuestion());
    }

    public List<StudentAnswer> getStudentAnswersByExamAndStudent(Exam exam, Student student) {
        return studentAnswerDao.findByExamAndStudent(exam, student);
    }

    public Map<Student, Double> getStudentExamResult(long examId) {
        Exam exam = examService.findExamById(examId);
        Map<Student, Double> map = new HashMap<>();
        List<StudentAnswer> studentAnswers = studentAnswerDao.findByExam(exam);
        for (StudentAnswer answer : studentAnswers) {
            Student student = answer.getStudent();
            Double score = studentService.getStudentScore(student, exam);
            map.put(student, score);
        }
        return map;
    }

    public void createStudentAnswersTemplate(Exam exam, Student student) {
        List<Question> questions = questionService.extractQuestionsFromMap(exam);
        for (Question question : questions) {
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setQuestion(question);
            studentAnswer.setExam(exam);
            studentAnswer.setStudent(student);
            studentAnswer.setAnswerStatus(StudentAnswerStatus.UnChecked);
            studentAnswerDao.save(studentAnswer);
        }
    }

    public List<StudentAnswer> getUncheckedStudentAnswers(long examId, String studentEmail) {
        Student student = studentService.findStudentByEmail(studentEmail);
        Exam exam = examService.findExamById(examId);
        List<StudentAnswer> studentAnswers = getSortedStudentAnswers(exam, student);
        Iterator<StudentAnswer> iterator = studentAnswers.iterator();
        while (iterator.hasNext()) {
            StudentAnswer studentAnswer = iterator.next();
            Question question = studentAnswer.getQuestion();
            StudentAnswerStatus answerStatus = studentAnswer.getAnswerStatus();
            if (question instanceof MultipleChoiceQuestion || answerStatus.equals(StudentAnswerStatus.Checked))
                iterator.remove();

        }
        return studentAnswers;
    }

    public List<StudentAnswer> getSortedStudentAnswers(Exam exam, Student student) {
        List<StudentAnswer> studentAnswers = getStudentAnswersByExamAndStudent(exam, student);
        for (int i = 0; i < studentAnswers.size(); i++) {
            studentAnswers.sort(new SortStudentAnswersById());
        }
        return studentAnswers;
    }
}
