package onlineExamManagement.utility;

import onlineExamManagement.model.dto.ExamDto;
import onlineExamManagement.model.entity.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamUtility {

    public ExamDto prepareExamDto(Exam exam) {
        ExamDto examDto = new ExamDto();
        examDto.setId(exam.getId());
        examDto.setTotalScore(exam.getTotalScore());
        examDto.setSubject(exam.getSubject());
        examDto.setMoreInfo(exam.getMoreInfo());
        examDto.setStartDate(exam.getStartDate());
        examDto.setEndDate(exam.getEndDate());
        examDto.setDuration(exam.getDuration());
        examDto.setStatus(exam.getStatus());
        examDto.setExaminer(exam.getExaminer());
        examDto.setCourse(exam.getCourse());
        return examDto;
    }

    public Exam prepareExam(ExamDto examDto) {
        Exam exam = new Exam();
        exam.setTotalScore(examDto.getTotalScore());
        exam.setSubject(examDto.getSubject());
        exam.setMoreInfo(examDto.getMoreInfo());
        exam.setStartDate(examDto.getStartDate());
        exam.setEndDate(examDto.getEndDate());
        exam.setDuration(examDto.getDuration());
        exam.setStatus(examDto.getStatus());
        exam.setExaminer(examDto.getExaminer());
        exam.setCourse(examDto.getCourse());
        return exam;
    }

    public List<Exam> prepareExamList(List<ExamDto> examDtoList) {
        List<Exam> examList = new ArrayList<>();
        for (ExamDto examDto : examDtoList) {
            Exam exam = prepareExam(examDto);
            examList.add(exam);
        }
        return examList;
    }

    public List<ExamDto> prepareExamDtoList(List<Exam> examList) {
        List<ExamDto> examDtoList = new ArrayList<>();
        for (Exam exam : examList) {
            ExamDto examDto = prepareExamDto(exam);
            examDtoList.add(examDto);
        }
        return examDtoList;
    }
}
