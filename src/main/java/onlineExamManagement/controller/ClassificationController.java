package onlineExamManagement.controller;

import onlineExamManagement.model.dto.ClassificationDto;
import onlineExamManagement.model.entity.Classification;
import onlineExamManagement.service.ClassificationService;
import onlineExamManagement.utility.ClassificationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClassificationController {

    public ClassificationService classificationService;
    ClassificationUtility utility = new ClassificationUtility();

    @Autowired
    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @RequestMapping(value = "/addClassification", method = RequestMethod.GET)
    public String addNewClassification(Model model) {
        model.addAttribute("classification", new ClassificationDto());
        return "addClassification";
    }

    @RequestMapping(value = "/addNewClassificationProcess", method = RequestMethod.POST)
    public String addNewClassificationProcess(@ModelAttribute("classification")
                                                      ClassificationDto classificationDto) {
        Classification classification = utility.prepareClassification(classificationDto);
        classificationService.addNewClassification(classification);
        return "adminPanel";
    }


}
