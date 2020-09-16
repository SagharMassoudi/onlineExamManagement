package onlineExamManagement.controller;

import onlineExamManagement.model.dto.EssayQuestionDto;
import onlineExamManagement.model.dto.MultipleChoiceQuestionDto;
import onlineExamManagement.model.dto.QuestionDto;
import onlineExamManagement.model.entity.*;
import onlineExamManagement.model.enumeration.questionEnum.QuestionType;
import onlineExamManagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    public ExamService examService;
    public UserService userService;
    public QuestionService questionService;
    public ClassificationService classificationService;
    public EssayQuestionService essayQuestionService;
    public MultipleChoiceQuestionService mulQuestionService;
    public AnswerService answerService;

    @Autowired
    public QuestionController(ExamService examService, UserService userService,
                              QuestionService questionService,
                              EssayQuestionService essayQuestionService,
                              ClassificationService classificationService,
                              AnswerService answerService,
                              MultipleChoiceQuestionService mulQuestionService) {
        this.examService = examService;
        this.userService = userService;
        this.questionService = questionService;
        this.classificationService = classificationService;
        this.essayQuestionService = essayQuestionService;
        this.answerService = answerService;
        this.mulQuestionService = mulQuestionService;
    }

    @RequestMapping(value = "/addQuestionToExam")
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
            mav.addObject("question", new Question());
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

    @RequestMapping(value = "/addEssayQuestionToExam", method = RequestMethod.GET)
    public ModelAndView addEssayQuestionToExam(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("result");
        EssayQuestion essayQuestion = new EssayQuestion();
        String message = "Question Added To Exam!";
        String title = request.getParameter("classification");
        String subject = request.getParameter("subject");
        String questionText = request.getParameter("questionText");
        float point = Float.parseFloat(request.getParameter("point"));
        long examId = Long.parseLong(request.getParameter("examId"));
        String radioValue = request.getParameter("questionBank");
        Classification classification = classificationService.findClassificationByTitle(title);
        Exam exam = examService.findExamById(examId);
        essayQuestion.setClassification(classification);
        essayQuestion.setSubject(subject);
        essayQuestion.setQuestionContent(questionText);
        essayQuestion.setPoint(point);
        essayQuestion.setQuestionType(QuestionType.EssayQuestion);
        examService.calculateTotalScore(exam, point);
        essayQuestionService.saveEssayQuestion(essayQuestion);
        exam.getQuestions().add(essayQuestion);
        examService.updateExam(exam);

        if (radioValue.equals("yes")) {
            classificationService.updateClassificationQuestions(title, essayQuestion);
            message += " Question Added To QuestionBank";
        }
        mav.addObject("message", message);
        return mav;
    }

    @RequestMapping(value = "/addMultipleChoiceQuestionToExam", method = RequestMethod.GET)
    public ModelAndView addMultipleChoiceQuestionToExam(@RequestParam("falseAnswers") String falseAnswers,
                                                        HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("result");
        MultipleChoiceQuestion mulQuestion = new MultipleChoiceQuestion();
        String message = "Multiple Choice Question Added To Exam!";
        String title = request.getParameter("classification");
        String subject = request.getParameter("subject");
        String questionText = request.getParameter("questionText");
        float point = Float.parseFloat(request.getParameter("point"));
        long examId = Long.parseLong(request.getParameter("examId"));
        String radioValue = request.getParameter("questionBank");
        String correctAnswer = request.getParameter("correctAnswer");
        Answer answer = answerService.addNewAnswer(correctAnswer, true);
        List<Answer> answerList = answerService.createQuestionAnswerList(falseAnswers);
        answerList.add(answer);
        Classification classification = classificationService.findClassificationByTitle(title);
        Exam exam = examService.findExamById(examId);
        mulQuestion.setClassification(classification);
        mulQuestion.setSubject(subject);
        mulQuestion.setQuestionContent(questionText);
        mulQuestion.setPoint(point);
        mulQuestion.setAnswers(answerList);
        mulQuestion.setQuestionType(QuestionType.MultipleChoiceQuestion);
        examService.calculateTotalScore(exam, point);
        mulQuestionService.saveMultipleQuestion(mulQuestion);
        exam.getQuestions().add(mulQuestion);
        examService.updateExam(exam);
        answerService.setQuestionForAnswer(answerList, mulQuestion);

        if (radioValue.equals("yes")) {
            classificationService.updateClassificationQuestions(title, mulQuestion);
            message += " Question Added To QuestionBank";
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
        question.setPoint(Float.parseFloat(point));
        examService.calculateTotalScore(exam,Float.parseFloat(point));
        String message = questionService.addQuestionToExamFromQBank(exam, question);
        return mav.addObject("message", message);
    }

    @RequestMapping(value = {"/getExamQuestion", "/getPreviousQuestion"}, method = RequestMethod.GET)
    public ModelAndView getQuestion(HttpServletRequest request) {
        String requestedValue = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        ModelAndView mav = new ModelAndView("exam");
        long id = Long.parseLong(request.getParameter("examId"));
        int questionIndex = Integer.parseInt(request.getParameter("index"));
        Exam exam = examService.findExamById(id);
        if (requestedValue.equals("/getExamQuestion")) {
            Object question = questionService.getExamQuestion(exam, questionIndex);
            mav.addObject("question", question);
        } else if (requestedValue.equals("/getPreviousQuestion")) {
            questionIndex -= 2;
            Object question = questionService.getExamQuestion(exam, questionIndex);
            mav.addObject("question", question);
        }
        mav.addObject("examId", id);
        mav.addObject("index", questionIndex + 1);
        return mav;
    }
}
