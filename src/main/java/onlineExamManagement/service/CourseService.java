package onlineExamManagement.service;

import onlineExamManagement.model.dao.CourseDao;
import onlineExamManagement.model.dto.CourseDto;
import onlineExamManagement.model.entity.Course;
import onlineExamManagement.model.entity.User;
import onlineExamManagement.utility.CourseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    UserService userService;

   CourseUtility courseUtility = new CourseUtility();

    public List<CourseDto> getAllCourses() {
        List<Course> courseList = courseDao.findAll();
        return courseUtility.prepareCourseDtoList(courseList);
    }

    @Transactional
    public void addNewCourse(CourseDto courseDto) {
        Course course = courseUtility.prepareCourse(courseDto);
        String title = course.getTitle();
        if (findCourseByTitle(title) == null)
            courseDao.save(course);
    }

    public Course findCourseByTitle(String title) {
        return courseDao.findByTitle(title);
    }


    public List<User> getCourseUsers(String title) {
        Course course = findCourseByTitle(title);
        List<User> userList = course.getUsers();
        return userList;
    }

    @Transactional
    public void updateCourseUsers(User user,Course course) {
        course.getUsers().add(user);
        courseDao.save(course);
    }
}
