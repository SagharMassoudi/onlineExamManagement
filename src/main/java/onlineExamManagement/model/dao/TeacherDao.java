package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends CrudRepository<Teacher,Long> {

    Teacher save(Teacher teacher);

}
