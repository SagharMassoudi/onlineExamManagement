package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Student;
import onlineExamManagement.model.entity.StudentScore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentScoreDao extends CrudRepository<StudentScore, Long> {
    StudentScore save(StudentScore studentScore);

    List<StudentScore> findByStudent(Student student);
}
