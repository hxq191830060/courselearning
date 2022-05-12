package cn.seecoder.courselearning.service.question;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.question.ExamVO;

import java.util.List;

public interface ExamService {

    ResultVO<ExamVO> create(ExamVO examVO);

    List<ExamVO> getExamsByCourseId(int courseId);
}
