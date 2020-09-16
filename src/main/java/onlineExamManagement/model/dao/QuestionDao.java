package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends CrudRepository<Question,Long> {
    Question findById(long Id);

    Question save(Question question);
}
