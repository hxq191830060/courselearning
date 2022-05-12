package cn.seecoder.courselearning.service.question;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.PostVO;
import cn.seecoder.courselearning.vo.question.QuestionVO;

import java.util.List;

public interface QuestionService {
    ResultVO<QuestionVO> create(QuestionVO questionVOVO);

    List<QuestionVO> getQuestionByCourseId(int courseId);
}
