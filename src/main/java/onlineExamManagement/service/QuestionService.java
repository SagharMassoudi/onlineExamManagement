package onlineExamManagement.service;

import onlineExamManagement.model.dao.QuestionDao;
import onlineExamManagement.model.dto.QuestionDto;
import onlineExamManagement.model.entity.Classification;
import onlineExamManagement.model.entity.EssayQuestion;
import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.entity.Question;
import onlineExamManagement.model.enumeration.questionEnum.QuestionType;
import onlineExamManagement.utility.QuestionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

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

    QuestionUtility questionUtility = new QuestionUtility();

    public Question findQuestionById(long id) {
        return questionDao.findById(id);
    }

    public String addQuestionToExamFromQBank(Exam exam, Question currentQuestion) {
        String message = "";
        int counter = 0;
        List<Question> examQuestions = exam.getQuestions();
        Iterator<Question> iterator = examQuestions.iterator();
        while (iterator.hasNext()) {
            Question examQuestion = iterator.next();
            if (examQuestion.getId() == currentQuestion.getId())
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

    public List<QuestionDto> getClassificationQuestions(String title) {
        Classification classification = classificationService.findClassificationByTitle(title);
        List<Question> questions = classification.getQuestionList();
        List<QuestionDto> questionDtoList = questionUtility.prepareQuestionDtoList(questions);
        return questionDtoList;
    }

    public Object getExamQuestion(Exam exam, int index) {
        List<Question> questions = exam.getQuestions();
        Question question = questions.get(index);
        if (question.getQuestionType().equals(QuestionType.EssayQuestion))
            return essayQuestionService.getEssayQuestion(question.getId());
        else if (question.getQuestionType().equals(QuestionType.MultipleChoiceQuestion))
            return multipleChoiceQuestionService.getMulQuestion(question.getId());
        return null;
    }
}
