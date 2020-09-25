package onlineExamManagement.controller;

import onlineExamManagement.model.entity.Student;
import onlineExamManagement.service.ExamService;
import onlineExamManagement.service.StudentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class StudentController {
    public StudentAnswerService studentAnswerService;
    public ExamService examService;

    @Autowired
    public StudentController(StudentAnswerService studentAnswerService, ExamService examService) {
        this.studentAnswerService = studentAnswerService;
        this.examService = examService;
    }

    @RequestMapping(value = "/showExamResults")
    public ModelAndView getStudentResults(HttpServletRequest request) {
        ModelAndView mav = null;
        long examId = Long.parseLong(request.getParameter("id"));
        Map<Student, Double> studentScoreMap = studentAnswerService.getStudentExamResult(examId);
        if (examService.examContainsEssayQuestions(examId)) {
            mav = new ModelAndView("examResults");
            mav.addObject("examId",examId);
        }
        else
            mav = new ModelAndView("multipleChoiceExamResults");
        mav.addObject("studentScoreMap",studentScoreMap);
        return mav;
    }
}
