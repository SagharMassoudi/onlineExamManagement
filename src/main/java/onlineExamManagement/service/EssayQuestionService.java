package onlineExamManagement.service;

import onlineExamManagement.model.dao.EssayQuestionDao;
import onlineExamManagement.model.entity.EssayQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EssayQuestionService {
    @Autowired
    EssayQuestionDao essayQuestionDao;

    public void saveEssayQuestion(EssayQuestion essayQuestion) {
        essayQuestionDao.save(essayQuestion);
    }

    public EssayQuestion getEssayQuestion(long id) {
        return essayQuestionDao.findById(id);
    }
}
