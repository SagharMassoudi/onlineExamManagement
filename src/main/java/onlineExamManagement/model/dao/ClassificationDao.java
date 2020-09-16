package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Classification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationDao extends CrudRepository<Classification,Long> {

    Classification save(Classification classification);

    List<Classification> findAll();

    Classification findByTitle(String title);
}
