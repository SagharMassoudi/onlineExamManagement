package onlineExamManagement.service;

import com.mysql.cj.util.StringUtils;
import onlineExamManagement.model.dao.EssayQuestionDao;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.studentAnswerEnum.StudentAnswerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class EssayQuestionService {
    @Autowired
    EssayQuestionDao essayQuestionDao;

    @Autowired
    QuestionService questionService;

    @Autowired
    StudentAnswerService studentAnswerService;

    @Autowired
    StudentService studentService;

    public EssayQuestion saveEssayQuestion(EssayQuestion essayQuestion) {
        return essayQuestionDao.save(essayQuestion);
    }

    public EssayQuestion getEssayQuestion(long id) {
        return essayQuestionDao.findById(id);
    }

    public void correctNotAnsweredEssayQuestions(Exam exam, Student student) {
        Double point = null;
        List<StudentAnswer> studentAnswers = studentAnswerService
                .getStudentAnswersByExamAndStudent(exam, student);
        Iterator<StudentAnswer> iterator = studentAnswers.iterator();
        while (iterator.hasNext()) {
            StudentAnswer studentAnswer = iterator.next();
            Question question = studentAnswer.getQuestion();
            if (question instanceof EssayQuestion &&
                    StringUtils.isNullOrEmpty(studentAnswer.getAnswer())) {
                    point = 0.0;
                    studentAnswerService.updateStudentAnswerPoint(studentAnswer,point);
                    studentService.updateExamScore(studentAnswer, point);
            }
        }
    }

    public void correctAnsweredEssayQuestion(StudentAnswer studentAnswer, double givenPoint) {
        Exam exam = studentAnswer.getExam();
        Question question = studentAnswer.getQuestion();
        double realPoint = questionService.getQuestionPoint(question, exam);
        if (givenPoint <= realPoint) {
            studentAnswerService.updateStudentAnswerPoint(studentAnswer,givenPoint);
            studentService.updateExamScore(studentAnswer, givenPoint);
        }
    }
}
