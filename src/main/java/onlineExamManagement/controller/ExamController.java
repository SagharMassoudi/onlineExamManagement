package onlineExamManagement.controller;

import onlineExamManagement.model.dto.ExamDto;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.examEnum.ExamStatus;
import onlineExamManagement.service.CourseService;
import onlineExamManagement.service.ExamService;
import onlineExamManagement.service.StudentScoreService;
import onlineExamManagement.service.UserService;
import onlineExamManagement.utility.ExamUtility;
import onlineExamManagement.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class ExamController {
    public ExamService examService;
    public CourseService courseService;
    public UserService userService;
    public StudentScoreService studentScoreService;
    UserUtility userUtility = new UserUtility();
    ExamUtility examUtility = new ExamUtility();

    @Autowired
    public ExamController(ExamService examService, CourseService courseService,
                          UserService userService,
                          StudentScoreService studentScoreService) {
        this.examService = examService;
        this.courseService = courseService;
        this.userService = userService;
        this.studentScoreService = studentScoreService;
    }

    @RequestMapping(value = "/addNewExamToCourse", method = RequestMethod.GET)
    public ModelAndView addNewExamToCourse(@RequestParam("title") String title,
                                           @RequestParam("emailAddress") String emailAddress) {
        User user = userService.getUserByEmail(emailAddress);
        Teacher teacher = userUtility.prepareTeacherFromUser(user);
        Course course = courseService.findCourseByTitle(title);
        ModelAndView mav = new ModelAndView("addExam");
        mav.addObject("exam", new ExamDto());
        mav.addObject("examiner", teacher);
        mav.addObject("course", course);
        return mav;

    }

    @RequestMapping(value = "/addNewExamToCourseProcess", method = RequestMethod.POST)
    public String addNewExamToCourseProcess(@ModelAttribute("exam") ExamDto examDto,
                                            @RequestParam("courseTitle") String title,
                                            @RequestParam("examinerEmail") String emailAddress) {
        User user = userService.getUserByEmail(emailAddress);
        Teacher teacher = userUtility.prepareTeacherFromUser(user);
        Course course = courseService.findCourseByTitle(title);
        examDto.setExaminer(teacher);
        examDto.setCourse(course);
        examDto.setStatus(ExamStatus.NotStarted);
        examService.addNewExamToCourse(examDto);
        return "teacherPanel";
    }

    @RequestMapping(value = {"/showCourseExams", "/showStudentFinishedExams",
            "/showInProgressExams"}, method = RequestMethod.GET)
    public ModelAndView getCourseExams(HttpServletRequest request) {
        String requestedValue = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String title = request.getParameter("title");
        String email = request.getParameter("emailAddress");
        Course course = courseService.findCourseByTitle(title);
        List<Exam> examList = examService.getCourseExams(course);
        List<StudentScore> finishedExams = studentScoreService.getStudentFinishedExam(email);
        ModelAndView mav = null;
        switch (requestedValue) {
            case "/showCourseExams":
                List<ExamDto> examDtoList = examUtility.prepareExamDtoList(examList);
                mav = new ModelAndView("courseExams");
                mav.addObject("emailAddress", email);
                mav.addObject("exams", examDtoList);
                mav.addObject("exam", new ExamDto());
                break;
            case "/showStudentFinishedExams":
                mav = new ModelAndView("studentFinishedExams");
                mav.addObject("finishedExams", finishedExams);
                break;
            case "/showInProgressExams":
                List<ExamDto> inProgressExams = examService.getInProgressExams(finishedExams, examList);
                mav = new ModelAndView("inProgressExams");
                mav.addObject("emailAddress", email);
                mav.addObject("exams", inProgressExams);
                break;
        }
        return mav;
    }

    @RequestMapping(value = {"/editExam", "/deleteExam", "/cancelExam"}, method = RequestMethod.GET)
    public ModelAndView manageExam(@RequestParam("emailAddress") String email,
                                   HttpServletRequest request) {
        String requestedValue = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        ModelAndView mav = new ModelAndView("result");
        String message = "";
        long id = Long.parseLong(request.getParameter("id"));
        Exam exam = examService.findExamById(id);
        if (!examService.timeIsUp(exam) && userService.teacherIsExaminer(exam, email)) {
            switch (requestedValue) {
                case "/editExam":
                    int duration = Integer.parseInt(request.getParameter("duration"));
                    String subject = request.getParameter("subject");
                    String moreInfo = request.getParameter("moreInfo");
                    Date startDate = Date.valueOf(request.getParameter("startDate"));
                    Date endDate = Date.valueOf(request.getParameter("endDate"));
                    exam.setDuration(duration);
                    exam.setSubject(subject);
                    exam.setMoreInfo(moreInfo);
                    exam.setStartDate(startDate);
                    exam.setEndDate(endDate);
                    examService.editExam(exam);
                    message = "Current Exam Has Been Edited Successfully!";
                    break;
                case "/cancelExam":
                    exam.setStatus(ExamStatus.Cancelled);
                    examService.updateExamStatus(exam);
                    message = "Exam has been successfully cancelled!";
                    break;
                case "/deleteExam":
                    examService.deleteExam(exam);
                    message = "Exam has been successfully deleted!";
                    break;
            }
        } else
            message = "You Are Not Examiner Of Current Exam!";
        mav.addObject("message", message);
        return mav;
    }
}
