package onlineExamManagement.service;

import onlineExamManagement.model.dao.StudentDao;
import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.entity.Question;
import onlineExamManagement.model.entity.Student;
import onlineExamManagement.model.entity.StudentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public Student findStudentByEmail(String email) {
        return studentDao.findByEmailAddress(email);
    }

    public Map<Exam, Double> getStudentFinishedExams(String email) {
        Student student = findStudentByEmail(email);
        return student.getExamScoreMap();
    }

    public Double getStudentScore(Student student, Exam exam) {
        Map<Exam, Double> examScoreMap = student.getExamScoreMap();
        Double score = 0.0;
        for (Map.Entry<Exam, Double> entry : examScoreMap.entrySet()) {
            Exam key = entry.getKey();
            if (key.getId().equals(exam.getId())) {
                score = entry.getValue();
            }
        }
        return score;
    }

    @Transactional
    public void updateExamScore(StudentAnswer studentAnswer, Double point) {
        Student student = studentAnswer.getStudent();
        Exam exam = studentAnswer.getExam();
        Double score = getStudentScore(student, exam);
        Map<Exam, Double> examScoreMap = student.getExamScoreMap();
        score += point;
        examScoreMap.put(exam, score);
        studentDao.save(student);
    }
}
