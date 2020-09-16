package onlineExamManagement.utility;

import onlineExamManagement.model.dto.UserDto;
import onlineExamManagement.model.entity.Student;
import onlineExamManagement.model.entity.Teacher;
import onlineExamManagement.model.entity.User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserUtility {

    public User prepareUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        user.setCourses(userDto.getCourses());
        user.setStatus(userDto.getStatus());
        user.setToken(userDto.getToken());
        return user;
    }

    public Teacher prepareTeacherFromUserDto(UserDto userDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(userDto.getFirstName());
        teacher.setLastName(userDto.getLastName());
        teacher.setEmailAddress(userDto.getEmailAddress());
        teacher.setRole(userDto.getRole());
        teacher.setPassword(userDto.getPassword());
        teacher.setCourses(userDto.getCourses());
        teacher.setStatus(userDto.getStatus());
        teacher.setToken(userDto.getToken());
        return teacher;
    }

    public Teacher prepareTeacherFromUser(User user) {
        Teacher teacher = new Teacher();
        teacher.setId(user.getId());
        teacher.setFirstName(user.getFirstName());
        teacher.setLastName(user.getLastName());
        teacher.setEmailAddress(user.getEmailAddress());
        teacher.setRole(user.getRole());
        teacher.setPassword(user.getPassword());
        teacher.setCourses(user.getCourses());
        teacher.setStatus(user.getStatus());
        teacher.setToken(user.getToken());
        return teacher;
    }

    public Student prepareStudent(UserDto userDto) {
        Student student = new Student();
        student.setFirstName(userDto.getFirstName());
        student.setLastName(userDto.getLastName());
        student.setEmailAddress(userDto.getEmailAddress());
        student.setRole(userDto.getRole());
        student.setPassword(userDto.getPassword());
        student.setCourses(userDto.getCourses());
        student.setStatus(userDto.getStatus());
        student.setToken(userDto.getToken());
        return student;
    }

    public UserDto prepareUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmailAddress(user.getEmailAddress());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        return userDto;
    }

    public List<User> prepareUserList(List<UserDto> userDtoList) {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            User user = prepareUser(userDto);
            userList.add(user);
        }
        return userList;
    }

    public List<UserDto> prepareUserDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = prepareUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public String prepareUserPanelName(UserDto userDto){
        String role = userDto.getRole().name();
        String panelName = StringUtils.uncapitalize(role)+"Panel";
        return panelName;
    }

    public String prepareUserCoursePageName(User user){
        String role = user.getRole().name();
        String pageName = StringUtils.uncapitalize(role)+"Courses";
        return pageName;
    }
}
