package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDao extends CrudRepository<Answer, Long> {
    Answer save(Answer answer);
}
