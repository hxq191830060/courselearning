package cn.seecoder.courselearning.mapperservice.question;

import cn.seecoder.courselearning.po.question.Exam;
import cn.seecoder.courselearning.po.question.Question;

import java.util.List;

public interface ExamMapper {

    Integer insert(Exam exam);

    List<Exam> getExamsByCourseId(Integer courseId);

    Integer getlastExamId();
}
