package onlineExamManagement.model.dao;

import onlineExamManagement.model.entity.Course;
import onlineExamManagement.model.entity.Exam;
import onlineExamManagement.model.enumeration.examEnum.ExamStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExamDao extends CrudRepository<Exam,Long> {
    Exam save(Exam exam);

   List<Exam> findByCourse(Course course);

   Exam findById(long id);

    @Modifying
    @Query("update Exam set status=:newStatus where id=:id")
    void updateExamStatus(@Param("id") Long id, @Param("newStatus") ExamStatus status);

    @Modifying
    @Query("update Exam set subject=:newSubject, startDate=:newStartDate," +
            "endDate=:newEndDate, duration=:newDuration, " +
            "moreInfo=:newMoreInfo where id=:id")
    void updateExam(@Param("id") Long id,
                    @Param("newSubject") String subject,
                    @Param("newStartDate") Date startDate,
                    @Param("newEndDate") Date endDate,
                    @Param("newDuration") int duration,
                    @Param("newMoreInfo") String moreInfo);

    void delete(Exam exam);
}
