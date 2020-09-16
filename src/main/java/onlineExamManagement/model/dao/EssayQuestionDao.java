package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.EssayQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayQuestionDao extends CrudRepository<EssayQuestion, Long> {
    EssayQuestion save(EssayQuestion essayQuestion);

    EssayQuestion findById(long id);
}
