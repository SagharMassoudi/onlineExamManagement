package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.entity.Question;
import onlineExamManagement.model.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {

    Student save(Student student);

    Student findByEmailAddress(String email);

    @Modifying
    @Query("update Student set examScoreMap=:newExamScoreMap where id=:id")
    void updateScore(@Param("newExamScoreMap") Map<Exam,Double> examScoreMap,
                     @Param("id") long id);
}
