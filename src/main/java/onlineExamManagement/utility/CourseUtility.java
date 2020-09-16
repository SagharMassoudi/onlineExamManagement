package onlineExamManagement.utility;

import onlineExamManagement.model.dto.CourseDto;
import onlineExamManagement.model.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseUtility {
    public CourseDto prepareCourseDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setClassification(course.getClassification());
        courseDto.setUsers(course.getUsers());
        return courseDto;
    }

    public Course prepareCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setClassification(courseDto.getClassification());
        course.setUsers(courseDto.getUsers());
        return course;
    }

    public List<CourseDto> prepareCourseDtoList(List<Course> courseList) {
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course course : courseList) {
            CourseDto courseDto = prepareCourseDto(course);
            courseDtoList.add(courseDto);
        }
        return courseDtoList;
    }

}
