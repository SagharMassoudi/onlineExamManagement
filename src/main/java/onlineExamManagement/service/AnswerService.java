package onlineExamManagement.service;

import onlineExamManagement.model.dao.AnswerDao;
import onlineExamManagement.model.entity.Answer;
import onlineExamManagement.model.entity.MultipleChoiceQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerDao answerDao;

    public Answer addNewAnswer(String content, boolean correct) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCorrect(correct);
        answerDao.save(answer);
        return answer;
    }

    public List<Answer> createQuestionAnswerList(String falseAnswers) {
        ArrayList<Answer> answers = new ArrayList<>();
        String[] answerArray = falseAnswers.split(",");
        for (int i = 0; i < answerArray.length; i++) {
            Answer answer = addNewAnswer(answerArray[i], false);
            answers.add(answer);
        }
        return answers;
    }

    public void setQuestionForAnswer(List<Answer> answers, MultipleChoiceQuestion mulQuestion){
        for (Answer answer : answers) {
            answer.setQuestion(mulQuestion);
            answerDao.save(answer);
        }
    }

}
