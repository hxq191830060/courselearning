package cn.seecoder.courselearning.serviceimpl.question;

import cn.seecoder.courselearning.mapperservice.question.ExamMapper;
import cn.seecoder.courselearning.mapperservice.question.ExamQuestionMapper;
import cn.seecoder.courselearning.po.question.Exam;
import cn.seecoder.courselearning.po.question.Question;
import cn.seecoder.courselearning.service.question.ExamService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.question.ExamVO;
import cn.seecoder.courselearning.vo.question.QuestionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Resource
    ExamMapper examMapper;

    @Resource
    ExamQuestionMapper examQuestionMapper;

    @Override
    public ResultVO<ExamVO> create(ExamVO examVO) {
        Exam exam=new Exam(examVO);
        examMapper.insert(exam);
        Integer examId=examMapper.getlastExamId();
        for(int i=0;i<exam.getQuestionIds().size();i++){
            examQuestionMapper.insert(examId, exam.getQuestionIds().get(i));
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"成功");
    }

    @Override
    public List<ExamVO> getExamsByCourseId(int courseId) {
        List<Exam> examList=examMapper.getExamsByCourseId(courseId);
        List<ExamVO> res=new ArrayList<>();

        for(Exam exam : examList){
            ExamVO examVO=new ExamVO(exam);
            ArrayList<QuestionVO> questionVOList=new ArrayList<>();
            List<Question> questionList= examQuestionMapper.getQuestionsByExamId(examVO.getExamId());
            for(Question question:questionList) {
                questionVOList.add(new QuestionVO(question));
            }
            examVO.setQuestions(questionVOList);
            res.add(examVO);

        }
        //排序
        res.sort(Comparator.comparingInt(ExamVO::getExamId));
        return res;
    }
}
