package onlineExamManagement.utility;

import onlineExamManagement.model.dto.QuestionDto;
import onlineExamManagement.model.entity.EssayQuestion;
import onlineExamManagement.model.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionUtility {

    public QuestionDto prepareQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setClassification(question.getClassification());
        questionDto.setQuestionContent(question.getQuestionContent());
        questionDto.setPoint(question.getPoint());
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

    public EssayQuestion prepareEssayQuestion(Question question){
        EssayQuestion essayQuestion = new EssayQuestion();
        essayQuestion.setPoint(question.getPoint());
        essayQuestion.setSubject(question .getSubject());
        essayQuestion.setQuestionContent(question.getQuestionContent());
        essayQuestion.setClassification(question.getClassification());
        return essayQuestion;
    }

}
