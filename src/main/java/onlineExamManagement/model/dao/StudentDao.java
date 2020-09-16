package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {

    Student save(Student student);

    Student findByEmailAddress(String email);
}
