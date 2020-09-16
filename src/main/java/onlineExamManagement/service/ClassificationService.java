package onlineExamManagement.service;

import onlineExamManagement.model.dao.ClassificationDao;
import onlineExamManagement.model.entity.Classification;
import onlineExamManagement.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService {

    @Autowired
    ClassificationDao classificationDao;



    public void addNewClassification(Classification classification) {
        classificationDao.save(classification);
    }


    public List<Classification> getAllClassifications() {
        return classificationDao.findAll();
    }

    public Classification findClassificationByTitle(String title) {
        return classificationDao.findByTitle(title);
    }

    public void updateClassificationQuestions(String title,Question question){
        Classification classification = findClassificationByTitle(title);
        classification.getQuestionList().add(question);
        classificationDao.save(classification);
    }
}
