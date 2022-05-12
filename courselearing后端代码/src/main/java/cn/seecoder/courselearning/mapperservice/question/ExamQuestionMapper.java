package cn.seecoder.courselearning.mapperservice.question;

import cn.seecoder.courselearning.po.question.Question;

import java.util.List;

public interface ExamQuestionMapper {

    Integer insert(Integer examId,Integer questionId);

    List<Question> getQuestionsByExamId(Integer examId);
}
