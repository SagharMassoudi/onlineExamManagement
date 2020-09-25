package onlineExamManagement.comparator;

import onlineExamManagement.model.entity.Question;

import java.util.Comparator;

public class SortQuestionsById implements Comparator<Question> {
    @Override
    public int compare(Question q1, Question q2) {
        return q1.getId().compareTo(q2.getId()) ;
    }
}
