package onlineExamManagement.utility;

import onlineExamManagement.model.dto.QuestionDto;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.questionEnum.QuestionType;
import onlineExamManagement.model.valueObject.QuestionVO;
import onlineExamManagement.service.ClassificationService;
import onlineExamManagement.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class QuestionUtility {

    public QuestionDto prepareQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setClassification(question.getClassification());
        questionDto.setQuestionContent(question.getQuestionContent());
        questionDto.setQuestionType(question.getQuestionType());
        questionDto.setSubject(question.getSubject());
        return questionDto;
    }

    public List<QuestionDto> prepareQuestionDtoList(List<Question> questionList) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto questionDto = prepareQuestionDto(question);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    public Object prepareQuestionFromQuestionVO(QuestionVO questionVO, QuestionType questionType) {
        if (questionType.equals(QuestionType.EssayQuestion)) {
            EssayQuestion essayQuestion = new EssayQuestion();
            essayQuestion.setSubject(questionVO.getSubject());
            essayQuestion.setQuestionContent(questionVO.getQuestionText());
            essayQuestion.setQuestionType(questionType);
            return essayQuestion;
        } else if (questionType.equals(QuestionType.MultipleChoiceQuestion)) {
            MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
            multipleChoiceQuestion.setSubject(questionVO.getSubject());
            multipleChoiceQuestion.setQuestionContent(questionVO.getQuestionText());
            multipleChoiceQuestion.setQuestionType(questionType);
            return multipleChoiceQuestion;
        }
        return null;
    }

}
