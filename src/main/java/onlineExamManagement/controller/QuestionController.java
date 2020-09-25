package onlineExamManagement.controller;

import com.mysql.cj.util.StringUtils;
import onlineExamManagement.model.dto.QuestionDto;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.questionEnum.QuestionType;
import onlineExamManagement.model.valueObject.QuestionVO;
import onlineExamManagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class QuestionController {
    public ExamService examService;
    public UserService userService;
    public QuestionService questionService;
    public ClassificationService classificationService;
    public EssayQuestionService essayQuestionService;
    public MultipleChoiceQuestionService mulQuestionService;
    public AnswerService answerService;
    public StudentService studentService;
    public StudentAnswerService studentAnswerService;

    @Autowired
    public QuestionController(ExamService examService, UserService userService,
                              QuestionService questionService,
                              EssayQuestionService essayQuestionService,
                              ClassificationService classificationService,
                              AnswerService answerService,
                              MultipleChoiceQuestionService mulQuestionService,
                              StudentService studentService,
                              StudentAnswerService studentAnswerService) {
        this.examService = examService;
        this.userService = userService;
        this.questionService = questionService;
        this.classificationService = classificationService;
        this.essayQuestionService = essayQuestionService;
        this.answerService = answerService;
        this.mulQuestionService = mulQuestionService;
        this.studentService = studentService;
        this.studentAnswerService = studentAnswerService;
    }

    @RequestMapping(value = "/addQuestionToExam", method = RequestMethod.GET)
    public ModelAndView showAddQuestionToExamPage(@RequestParam("id") String examId,
                                                  @RequestParam("emailAddress") String email) {
        String message = "You Are Not Examiner Of Current Exam!";
        long id = Long.parseLong(examId);
        Exam exam = examService.findExamById(id);
        if (!examService.timeIsUp(exam) && userService.teacherIsExaminer(exam, email)) {
            Course course = exam.getCourse();
            Classification classification = course.getClassification();
            ModelAndView mav = new ModelAndView("addQuestionToExam");
            mav.addObject("classificationTitle", classification.getTitle());
            mav.addObject("examId", examId);
            mav.addObject("question", new QuestionVO());
            return mav;
        } else
            return new ModelAndView("result", "message", message);
    }

    @RequestMapping(value = "/getClassificationQuestions", method = RequestMethod.GET)
    public ModelAndView getQuestionsFromQuestionBank(@RequestParam("classificationTitle") String title,
                                                     @RequestParam("id") String examId) {
        List<QuestionDto> questionDtoList = questionService.getClassificationQuestions(title);
        ModelAndView mav = new ModelAndView("questionBank");
        mav.addObject("questions", questionDtoList);
        mav.addObject("question", new QuestionDto());
        mav.addObject("examId", examId);
        return mav;
    }

    @RequestMapping(value = {"/addEssayQuestionToExam", "/addMultipleChoiceQuestionToExam"}, method = RequestMethod.GET)
    public ModelAndView addEssayQuestionToExam(HttpServletRequest request,
                                               @ModelAttribute("question") QuestionVO questionVO) {

        String requestedValue = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String radioValue = request.getParameter("questionBank");
        questionVO.setExamId(request.getParameter("examId"));
        questionVO.setClassificationTitle(request.getParameter("classificationTitle"));
        ModelAndView mav = new ModelAndView("result");
        String message = "Question Added To Exam!";
        if (requestedValue.equals("/addEssayQuestionToExam")) {
            EssayQuestion essayQuestion = (EssayQuestion) questionService
                    .addNewQuestionToExam(questionVO, QuestionType.EssayQuestion);
            if (radioValue.equals("yes")) {
                classificationService.updateClassificationQuestions(questionVO.getClassificationTitle(), essayQuestion);
                message += " Question Added To QuestionBank";
            }
        } else if (requestedValue.equals("/addMultipleChoiceQuestionToExam")) {
            String falseAnswers = request.getParameter("falseAnswers");
            MultipleChoiceQuestion mulQuestion = (MultipleChoiceQuestion) questionService
                    .addNewQuestionToExam(questionVO, QuestionType.MultipleChoiceQuestion);
            String correctAnswer = request.getParameter("correctAnswer");
            Answer answer = answerService.addNewAnswer(correctAnswer, true);
            List<Answer> answerList = answerService.createQuestionAnswerList(falseAnswers);
            answerList.add(answer);
            mulQuestion.setAnswers(answerList);
            answerService.setQuestionForAnswer(answerList, mulQuestion);
            if (radioValue.equals("yes")) {
                classificationService.updateClassificationQuestions(questionVO.getClassificationTitle(), mulQuestion);
                message += " Question Added To QuestionBank";
            }
        }
        mav.addObject("message", message);
        return mav;
    }

    @RequestMapping(value = "/addQuestionToExamFromQuestionBank")
    public ModelAndView addQuestionToExamFromQuestionBank(@RequestParam("examId") String examId,
                                                          @RequestParam("questionId") String id,
                                                          @RequestParam("point") String point) {
        ModelAndView mav = new ModelAndView("result");
        long currentExamId = Long.parseLong(examId);
        long currentQuestionId = Long.parseLong(id);
        Exam exam = examService.findExamById(currentExamId);
        Question question = questionService.findQuestionById(currentQuestionId);
        examService.calculateTotalScore(exam, Float.parseFloat(point));
        String message = questionService.addQuestionToExamFromQBank(exam, question);
        return mav.addObject("message", message);
    }

    @RequestMapping(value = "/startExam", method = RequestMethod.GET)
    public String startExam(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("examId"));
        Exam exam = examService.findExamById(id);
        Student student = studentService.findStudentByEmail(request.getParameter("emailAddress"));
        studentAnswerService.createStudentAnswersTemplate(exam, student);
        Date startDate = new Date();
        exam.getStudentDateMap().put(student, startDate);
        examService.updateExam(exam);
        return "forward:getExamQuestion";
    }

    @RequestMapping(value = {"/getExamQuestion", "/getPreviousQuestion", "/finishExam"}, method = RequestMethod.GET)
    public ModelAndView getQuestion(HttpServletRequest request) {

        String requestedValue = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        Question question = new Question();
        ModelAndView mav = null;
        long id = Long.parseLong(request.getParameter("examId"));
        int questionIndex = Integer.parseInt(request.getParameter("index"));
        Exam exam = examService.findExamById(id);
        Student student = studentService.findStudentByEmail(request.getParameter("emailAddress"));
        int listSize = exam.getQuestionPointMap().size();

        if (questionIndex > 0) {
            String answer = "";
            Question currentQuestion = questionService.getExamQuestion(exam, questionIndex - 1);
            if (currentQuestion instanceof EssayQuestion)
                answer = request.getParameter("studentAnswer");
            else
                answer = request.getParameter("choice");
            StudentAnswer currentStudentAnswer = studentAnswerService
                    .findCurrentStudentAnswer(exam, currentQuestion, student);
            studentAnswerService.updateQuestionAnswer(currentStudentAnswer, answer);
        }


        switch (requestedValue) {
            case "/getExamQuestion":
                question = questionService.getExamQuestion(exam, questionIndex);
                break;
            case "/getPreviousQuestion":
                if (questionIndex > 0) {
                    questionIndex -= 2;
                    question = questionService.getExamQuestion(exam, questionIndex);
                }
                break;
        }
        switch (requestedValue) {
            case "/getExamQuestion":
            case "/getPreviousQuestion":
                if (question instanceof MultipleChoiceQuestion) {
                    answerService.shuffleAnswers((MultipleChoiceQuestion) question);
                }
                mav = new ModelAndView("exam");
                mav.addObject(exam.getDuration());
                mav.addObject("question", question);
                mav.addObject("emailAddress", student.getEmailAddress());
                mav.addObject("examId", id);
                mav.addObject("index", questionIndex + 1);
                mav.addObject("listSize", listSize);
                mav.addObject("startTime", examService.getExamStartTime(student, exam));
                mav.addObject("examDuration",exam.getDuration());
                break;
            case "/finishExam":
                String message = "You Finished The Exam Successfully";
                mulQuestionService.correctStudentMultipleChoiceQuestions(exam, student);
                essayQuestionService.correctNotAnsweredEssayQuestions(exam, student);
                mav = new ModelAndView("result");
                mav.addObject("message", message);
        }
        return mav;
    }

    @RequestMapping(value = "/correctEssayQuestion", method = RequestMethod.GET)
    public Object correctCurrentEssayQuestion(HttpServletRequest request) {
        double point = Double.parseDouble(request.getParameter("point"));
        Student student = studentService
                .findStudentByEmail(request.getParameter("studentEmail"));
        Question question = questionService
                .findQuestionById(Long.parseLong(request.getParameter("questionId")));
        Exam exam = examService
                .findExamById(Long.parseLong(request.getParameter("examId")));
        StudentAnswer studentAnswer = studentAnswerService
                .findCurrentStudentAnswer(exam, question, student);
        essayQuestionService.correctAnsweredEssayQuestion(studentAnswer, point);
        if (StringUtils.isNullOrEmpty((request.getParameter("lastTime")))) {
            return "forward:getUnCheckedEssayQuestion";
        } else {
            String message = "Current Exam Successfully Corrected!";
            ModelAndView mav = new ModelAndView("result");
            mav.addObject("message", message);
            return mav;
        }

    }

    @RequestMapping(value = "/getUnCheckedEssayQuestion", method = RequestMethod.GET)
    public ModelAndView getEssayQuestionToCorrect(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("studentEssayQuestions");

        long examId = Long.parseLong(request.getParameter("examId"));
        String studentEmail = request.getParameter("studentEmail");
        List<StudentAnswer> studentAnswers = studentAnswerService
                .getUncheckedStudentAnswers(examId, studentEmail);
        if (studentAnswers.size() == 1)
            mav.addObject("lastTime", 1);
        StudentAnswer studentAnswer = studentAnswers.get(0);
        mav.addObject("studentAnswer", studentAnswer);
        mav.addObject("index", 0);
        mav.addObject("studentEmail", studentEmail);
        mav.addObject("examId", examId);
        return mav;
    }
}

