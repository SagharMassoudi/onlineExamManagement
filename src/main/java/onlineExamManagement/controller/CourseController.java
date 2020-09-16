package onlineExamManagement.controller;

import onlineExamManagement.model.dto.CourseDto;
import onlineExamManagement.model.entity.Classification;
import onlineExamManagement.model.entity.Course;
import onlineExamManagement.model.entity.User;
import onlineExamManagement.service.ClassificationService;
import onlineExamManagement.service.CourseService;
import onlineExamManagement.service.UserService;
import onlineExamManagement.utility.CourseUtility;
import onlineExamManagement.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseController {

    public CourseService courseService;
    public ClassificationService classificationService;
    public UserService userService;

    CourseUtility courseUtility = new CourseUtility();
    UserUtility userUtility = new UserUtility();

    @Autowired
    public CourseController(CourseService courseService,
                            ClassificationService classificationService,
                            UserService userService) {
        this.courseService = courseService;
        this.classificationService = classificationService;
        this.userService = userService;
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView showAddCoursePage() {
        List<Classification> classifications =
                classificationService.getAllClassifications();
        ModelAndView mav = new ModelAndView("addCourse");
        mav.addObject("classifications", classifications);
        mav.addObject("course", new CourseDto());
        return mav;
    }

    @RequestMapping(value = "/addNewCourseProcess", method = RequestMethod.POST)
    public ModelAndView addNewCourse(@ModelAttribute("course") CourseDto courseDto,
                                     @RequestParam("classificationTitle") String title) {
        Classification classification = classificationService
                .findClassificationByTitle(title);
        courseDto.setClassification(classification);
        courseService.addNewCourse(courseDto);
        String message = "New course added successfully!";
        ModelAndView mav = new ModelAndView("adminPanel");
        mav.addObject("message", message);
        return mav;
    }

    @RequestMapping(value = "/showAllCourses", method = RequestMethod.GET)
    public ModelAndView getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        ModelAndView mav = new ModelAndView("allCourses");
        mav.addObject("courseList", courses);
        mav.addObject("course", new CourseDto());
        return mav;
    }

    @RequestMapping(value = "/showUserCourses", method = RequestMethod.GET)
    public ModelAndView getUserCourses(@RequestParam("emailAddress") String emailAddress) {
        User user = userService.getUserByEmail(emailAddress);
        String view = userUtility.prepareUserCoursePageName(user);
        List<Course> courseList = user.getCourses();
        List<CourseDto> courseDtoList = courseUtility.prepareCourseDtoList(courseList);
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("courses", courseDtoList);
        mav.addObject("course", new CourseDto());
        mav.addObject("emailAddress",user.getEmailAddress());
        return mav;
    }

}
