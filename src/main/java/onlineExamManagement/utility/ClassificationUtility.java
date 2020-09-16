package onlineExamManagement.utility;

import onlineExamManagement.model.dto.ClassificationDto;
import onlineExamManagement.model.entity.Classification;

public class ClassificationUtility {

    public Classification prepareClassification(ClassificationDto classificationDto) {
        Classification classification = new Classification();
        classification.setTitle(classificationDto.getTitle());
        classification.setCourses(classificationDto.getCourses());
        return classification;
    }
}
