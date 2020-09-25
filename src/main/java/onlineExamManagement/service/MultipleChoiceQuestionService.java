package onlineExamManagement.service;

import onlineExamManagement.model.dao.MultipleChoiceQuestionDao;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.studentAnswerEnum.StudentAnswerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class MultipleChoiceQuestionService {

    @Autowired
    MultipleChoiceQuestionDao multipleChoiceQuestionDao;
    @Autowired
    StudentAnswerService studentAnswerService;
    @Autowired
    StudentService studentService;
    @Autowired
    QuestionService questionService;

    public MultipleChoiceQuestion saveMultipleQuestion(MultipleChoiceQuestion mulQuestion) {
        return multipleChoiceQuestionDao.save(mulQuestion);
    }

    public MultipleChoiceQuestion getMulQuestion(long id) {
        return multipleChoiceQuestionDao.findById(id);
    }

    public void correctStudentMultipleChoiceQuestions(Exam exam, Student student) {
        Double point = 0.0;
        List<StudentAnswer> studentAnswers = studentAnswerService
                .getStudentAnswersByExamAndStudent(exam, student);
        Iterator<StudentAnswer> iterator = studentAnswers.iterator();
        while (iterator.hasNext()) {
            StudentAnswer studentAnswer = iterator.next();
            Question question = studentAnswer.getQuestion();
            if (question instanceof MultipleChoiceQuestion) {
                Answer answer = getCorrectAnswerOfMulQuestion(question.getId());
                if (studentAnswer.getAnswer().equals(answer.getContent()))
                    point = questionService.getQuestionPoint(question, exam);
                else
                    point = 0.0;
                studentAnswerService.updateStudentAnswerPoint(studentAnswer,point);
                studentService.updateExamScore(studentAnswer,point);
            }
        }
    }

    public Answer getCorrectAnswerOfMulQuestion(long questionId) {
        MultipleChoiceQuestion mulQuestion = getMulQuestion(questionId);
        List<Answer> answers = mulQuestion.getAnswers();
        Answer answer = new Answer();
        for (int i = 0; i < answers.size(); i++) {
            answer = answers.get(i);
            if (answer.isCorrect())
                return answer;
        }
        return answer;
    }

}
