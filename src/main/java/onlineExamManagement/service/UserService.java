package onlineExamManagement.service;

import onlineExamManagement.model.dao.StudentDao;
import onlineExamManagement.model.dao.TeacherDao;
import onlineExamManagement.model.dao.UserDao;
import onlineExamManagement.model.dto.UserDto;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.userEnum.UserRole;
import onlineExamManagement.model.enumeration.userEnum.UserStatus;
import onlineExamManagement.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;

    UserUtility userUtility = new UserUtility();

    public void provideToken(UserDto userDto) {
        String token = UUID.randomUUID().toString();
        userDto.setToken(token);
    }

    public UserDto findUserByToken(String token) {
        User user = userDao.findByToken(token);
        return userUtility.prepareUserDto(user);
    }

    @Transactional
    public void updateStatus(UserDto userDto, UserStatus status) {
        Long id = userDto.getId();
        userDao.updateUserStatus(id, status);
    }

    @Transactional
    public void registerNewUser(UserDto userDto) {
        UserRole role = userDto.getRole();
        if (role.equals(UserRole.Student)){
            Student student = userUtility.prepareStudent(userDto);
            studentDao.save(student);
        }
        else if (role.equals((UserRole.Teacher))){
            Teacher teacher = userUtility.prepareTeacherFromUserDto(userDto);
            teacherDao.save(teacher);
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userDao.findAll();
        return userUtility.prepareUserDtoList(userList);
    }

    public UserDto findUser(UserDto userDto) {
        String emailAddress = userDto.getEmailAddress();
        String password = userDto.getPassword();
        UserRole role = userDto.getRole();
        User user = userDao.findByEmailAddressAndPasswordAndRole(emailAddress,
                password, role);
        userDto = userUtility.prepareUserDto(user);
        return userDto;
    }


    public Page<User> findMAxMatch(String firstName,
                                   String lastName, String emailAddress,
                                   UserRole role, UserStatus status,
                                    int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return userDao.findAll(UserDao.findMaxMatch(firstName, lastName, emailAddress, role, status), pageable);

    }

    @Transactional
    public void updateUser(UserDto userDto) {
        String emailAddress = userDto.getEmailAddress();
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        UserRole role = userDto.getRole();
        UserStatus status = userDto.getStatus();
        userDao.updateUser(emailAddress, firstName, lastName, role, status);
    }

    @Transactional
    public User getUserByEmail(String emailAddress) {
        User user = userDao.findByEmailAddress(emailAddress);
        return user;
    }

    public boolean teacherIsExaminer(Exam exam,String email){
        Teacher examiner = exam.getExaminer();
        User user = getUserByEmail(email);
        Teacher teacher = userUtility.prepareTeacherFromUser(user);
        if (!teacher.getEmailAddress().equals(examiner.getEmailAddress()))
            return false;
        return true;
    }

}
