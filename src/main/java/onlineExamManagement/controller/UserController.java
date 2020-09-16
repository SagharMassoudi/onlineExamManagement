package onlineExamManagement.controller;

import onlineExamManagement.model.dto.CourseDto;
import onlineExamManagement.model.dto.UserDto;
import onlineExamManagement.model.entity.Course;
import onlineExamManagement.model.entity.User;
import onlineExamManagement.model.enumeration.userEnum.UserStatus;
import onlineExamManagement.service.CourseService;
import onlineExamManagement.service.MailService;
import onlineExamManagement.service.UserService;
import onlineExamManagement.utility.CourseUtility;
import onlineExamManagement.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    public UserService userService;
    public MailService mailService;
    public CourseService courseService;
    UserUtility  userUtility = new UserUtility();

    @Autowired
    public UserController(UserService userService, MailService mailService,
                          CourseService courseService) {
        this.userService = userService;
        this.mailService = mailService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute("user") UserDto userDto) {
        userService.provideToken(userDto);
        userDto.setStatus(UserStatus.Unknown);
        userService.registerNewUser(userDto);
        mailService.sendVerificationMail(userDto);
        return "wait";
    }

    @RequestMapping(value = "/verifyAccount", method = RequestMethod.GET)
    public ModelAndView verifyAccount(@RequestParam String token) {
        UserDto userDto = userService.findUserByToken(token);
        userService.updateStatus(userDto, UserStatus.Waiting);
        String userPanel = userUtility.prepareUserPanelName(userDto);
        String message = "Welcome " + userDto.getFirstName();
        ModelAndView mav = new ModelAndView(userPanel);
        mav.addObject("message", message);
        mav.addObject("user", userDto);
        return mav;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.GET)
    public ModelAndView login(@ModelAttribute("user") UserDto userDto) {
        UserDto currentUser = userService.findUser(userDto);
        if (currentUser != null) {
            String userPanel = userUtility.prepareUserPanelName(currentUser);
            String message = "Welcome " + currentUser.getFirstName();
            ModelAndView mav = new ModelAndView(userPanel);
            mav.addObject("message", message);
            mav.addObject("emailAddress", currentUser.getEmailAddress());
            return mav;
        }
        return null;
    }


    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    public String showSearchUsersPage(Model model) {
        model.addAttribute("user", new User());
        return "searchUsers";
    }

    @RequestMapping(value = "/searchUsersProcess", method = RequestMethod.POST)
    public ModelAndView searchStudent(@ModelAttribute("user") User user) {
        Page<User> users = userService.findMAxMatch(user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress(),
                user.getRole(),
                user.getStatus(),
                0,
                5);

        return new ModelAndView("searchUsers", "userList", users.getContent());
    }

    @RequestMapping(value = "/showAllUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ModelAndView mav = new ModelAndView("allUsers");
        mav.addObject("userList", users);
        mav.addObject("user", new UserDto());
        return mav;
    }

    @RequestMapping(value = "/showCourseUsers", method = RequestMethod.GET)
    public ModelAndView getCourseUsers(@RequestParam("title") String title) {
        Course course = courseService.findCourseByTitle(title);
        List<User> userList = courseService.getCourseUsers(course.getTitle());
        List<UserDto> userDtoList = userUtility.prepareUserDtoList(userList);
        ModelAndView mav = new ModelAndView("courseUsers");
        mav.addObject("users", userDtoList);
        return mav;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public ModelAndView updateUser(@ModelAttribute("user") UserDto userDto) {
        userService.updateUser(userDto);
        String message = "User successfully has been updated.";
        ModelAndView mav = new ModelAndView("adminPanel");
        mav.addObject("message", message);
        return mav;
    }

    @RequestMapping(value = "/addUserToCourse", method = RequestMethod.GET)
    public ModelAndView addUserToCourse() {
        List<CourseDto> courseDtoList = courseService.getAllCourses();
        List<UserDto> userDtoList = userService.getAllUsers();
        ModelAndView mav = new ModelAndView("addUserToCourse");
        mav.addObject("users", userDtoList);
        mav.addObject("courses", courseDtoList);
        return mav;
    }

    @RequestMapping(value = "/addUserToCourseProcess", method = RequestMethod.GET)
    public ModelAndView addUserToCourseProcess(@RequestParam("courseTitle") String courseTitle,
                                               @RequestParam("userEmail") String emailAddress) {
        User user = userService.getUserByEmail(emailAddress);
        Course course = courseService.findCourseByTitle(courseTitle);
        courseService.updateCourseUsers(user, course);
        String message = "User successfully added to course! ";
        ModelAndView mav = new ModelAndView("adminPanel");
        mav.addObject("message", message);
        return mav;
    }

}
