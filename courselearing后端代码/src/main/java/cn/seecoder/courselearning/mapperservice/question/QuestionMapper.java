package cn.seecoder.courselearning.mapperservice.question;

import cn.seecoder.courselearning.po.question.Question;

import java.util.List;

public interface QuestionMapper {

    Integer insert(Question question);

    List<Question> getQuestionsByCourseId(int courseId);

    Integer getLastedQuestionId();
}
