package onlineExamManagement.service;

import onlineExamManagement.model.dao.MultipleChoiceQuestionDao;
import onlineExamManagement.model.entity.MultipleChoiceQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultipleChoiceQuestionService {

    @Autowired
    MultipleChoiceQuestionDao multipleChoiceQuestionDao;

    public void saveMultipleQuestion(MultipleChoiceQuestion mCQuestion){
        multipleChoiceQuestionDao.save(mCQuestion);
    }

    public MultipleChoiceQuestion getMulQuestion(long id){
       return multipleChoiceQuestionDao.findById(id);
    }
}
