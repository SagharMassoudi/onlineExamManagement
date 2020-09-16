package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.MultipleChoiceQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceQuestionDao extends CrudRepository<MultipleChoiceQuestion, Long> {

    MultipleChoiceQuestion save(MultipleChoiceQuestion multipleChoiceQuestion);

    MultipleChoiceQuestion findById(long id);
}
