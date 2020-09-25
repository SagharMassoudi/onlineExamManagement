package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.entity.Question;
import onlineExamManagement.model.entity.Student;
import onlineExamManagement.model.entity.StudentAnswer;
import onlineExamManagement.model.enumeration.studentAnswerEnum.StudentAnswerStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static onlineExamManagement.model.enumeration.studentAnswerEnum.StudentAnswerStatus.Checked;

@Repository
public interface StudentAnswerDao extends CrudRepository<StudentAnswer, Long> {
    StudentAnswer save(StudentAnswer studentAnswer);

    StudentAnswer findByExamAndQuestionAndStudent(Exam exam, Question question, Student student);

    List<StudentAnswer> findByExamAndStudent(Exam exam, Student student);

    List<StudentAnswer> findByExam(Exam exam);

    @Modifying
    @Query("update StudentAnswer set answer=:newAnswer where question=:question")
    void updateAnswer(@Param("newAnswer") String answer,
                      @Param("question") Question question);

    @Modifying
    @Query("update StudentAnswer set point=:newPoint,answerStatus=:newStatus where question=:question")
    void updatePoint(@Param("newPoint") Double point,
                     @Param("newStatus") StudentAnswerStatus status,
                     @Param("question") Question question);
}
