package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends CrudRepository<Course, Long> {

    List<Course> findAll();

    Course save(Course course);

    Course findByTitle(String title);

}
