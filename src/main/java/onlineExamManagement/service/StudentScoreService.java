package onlineExamManagement.service;

import onlineExamManagement.model.dao.StudentDao;
import onlineExamManagement.model.dao.StudentScoreDao;
import onlineExamManagement.model.entity.Student;
import onlineExamManagement.model.entity.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScoreService {
    @Autowired
    StudentScoreDao studentScoreDao;
    @Autowired
    StudentDao studentDao;

    public List<StudentScore> getStudentFinishedExam(String email) {
        Student student = studentDao.findByEmailAddress(email);
        List<StudentScore> studentScores = studentScoreDao.findByStudent(student);
        return studentScores;
    }
}
