package onlineExamManagement.service;


import onlineExamManagement.comparator.SortQuestionsById;
import onlineExamManagement.model.dao.QuestionDao;
import onlineExamManagement.model.dto.QuestionDto;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.questionEnum.QuestionType;
import onlineExamManagement.model.valueObject.QuestionVO;
import onlineExamManagement.utility.QuestionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    ExamService examService;
    @Autowired
    ClassificationService classificationService;
    @Autowired
    EssayQuestionService essayQuestionService;
    @Autowired
    MultipleChoiceQuestionService multipleChoiceQuestionService;
    @Autowired
    StudentAnswerService studentAnswerService;

    QuestionUtility questionUtility = new QuestionUtility();

    public Question findQuestionById(long id) {
        return questionDao.findById(id);
    }

    public String addQuestionToExamFromQBank(Exam exam, Question currentQuestion) {
        String message = "";
        int counter = 0;
        List<Question> examQuestions = extractQuestionsFromMap(exam);
        Iterator<Question> iterator = examQuestions.iterator();
        while (iterator.hasNext()) {
            Question examQuestion = iterator.next();
            if (examQuestion.getId().equals(currentQuestion.getId()))
                counter++;
        }
        if (counter > 0)
            message = "Current Question Already Exists! ";
        else {
            examQuestions.add(currentQuestion);
            examService.updateExam(exam);
            message = "Question Has Been Successfully Added To Exam!";
        }

        return message;
    }

    public Object addNewQuestionToExam(QuestionVO questionVO, QuestionType questionType) {
        long examId = Long.parseLong(questionVO.getExamId());
        double point = Double.parseDouble(questionVO.getPoint());
        Exam exam = examService.findExamById(examId);
        Object question = null;
        if (questionType.equals(QuestionType.EssayQuestion)) {
            EssayQuestion essayQuestion = (EssayQuestion) questionUtility.prepareQuestionFromQuestionVO(questionVO, questionType);
            setClassificationAndExam(questionVO, essayQuestion);
            EssayQuestion savedEssayQuestion = essayQuestionService.saveEssayQuestion(essayQuestion);
            exam.getQuestionPointMap().put(savedEssayQuestion, point);
            question = savedEssayQuestion;
        } else if (questionType.equals(QuestionType.MultipleChoiceQuestion)) {
            MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) questionUtility.prepareQuestionFromQuestionVO(questionVO, questionType);
            setClassificationAndExam(questionVO, multipleChoiceQuestion);
            MultipleChoiceQuestion savedMulQuestion = multipleChoiceQuestionService.saveMultipleQuestion(multipleChoiceQuestion);
            exam.getQuestionPointMap().put(multipleChoiceQuestion, point);
            question = savedMulQuestion;
        }
        examService.calculateTotalScore(exam, point);
        examService.updateExam(exam);
        return question;
    }

    public List<QuestionDto> getClassificationQuestions(String title) {
        Classification classification = classificationService.findClassificationByTitle(title);
        List<Question> questions = classification.getQuestionList();
        List<QuestionDto> questionDtoList = questionUtility.prepareQuestionDtoList(questions);
        return questionDtoList;
    }

    public Question getExamQuestion(Exam exam, int index) {
        List<Question> questions = extractQuestionsFromMap(exam);
        Question question = questions.get(index);
        if (question.getQuestionType().equals(QuestionType.EssayQuestion))
            return essayQuestionService.getEssayQuestion(question.getId());
        else if (question.getQuestionType().equals(QuestionType.MultipleChoiceQuestion))
            return multipleChoiceQuestionService.getMulQuestion(question.getId());
        return null;
    }

    public void setClassificationAndExam(QuestionVO questionVO, Object question) {
        Classification classification = classificationService
                .findClassificationByTitle(questionVO.getClassificationTitle());

        if (question instanceof EssayQuestion) {
            ((EssayQuestion) question).setClassification(classification);
        } else if (question instanceof MultipleChoiceQuestion) {
            ((MultipleChoiceQuestion) question).setClassification(classification);
        }
    }

    public List<Question> extractQuestionsFromMap(Exam exam) {
        Map<Question, Double> questionPointMap = exam.getQuestionPointMap();
        List<Question> questions = new ArrayList<>(questionPointMap.keySet());
        for (int i = 0; i < questions.size(); i++) {
            questions.sort(new SortQuestionsById());
        }
        return questions;
    }

    public Double getQuestionPoint(Question question, Exam exam) {
        Map<Question, Double> questionPointMap = exam.getQuestionPointMap();
        Double point = 0.0;
        for (Map.Entry<Question, Double> entry : questionPointMap.entrySet()) {
            Question key = entry.getKey();
            if (key.getId().equals(question.getId())) {
                point = entry.getValue();
            }
        }
        return point;
    }
}