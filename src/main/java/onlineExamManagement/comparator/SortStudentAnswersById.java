package onlineExamManagement.comparator;

import onlineExamManagement.model.entity.StudentAnswer;

import java.util.Comparator;

public class SortStudentAnswersById implements Comparator<StudentAnswer> {
    @Override
    public int compare(StudentAnswer s1, StudentAnswer s2) {
        return s1.getId().compareTo(s2.getId());
    }
}
