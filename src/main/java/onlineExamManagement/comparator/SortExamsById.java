package onlineExamManagement.comparator;

import onlineExamManagement.model.entity.Exam;

import java.util.Comparator;

public class SortExamsById implements Comparator<Exam> {
    @Override
    public int compare(Exam e1, Exam e2) {
        return e1.getId().compareTo(e2.getId());
    }
}
