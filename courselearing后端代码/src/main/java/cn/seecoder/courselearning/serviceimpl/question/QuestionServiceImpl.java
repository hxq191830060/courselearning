package cn.seecoder.courselearning.serviceimpl.question;

import cn.seecoder.courselearning.mapperservice.forum.PostMapper;
import cn.seecoder.courselearning.mapperservice.question.QuestionMapper;
import cn.seecoder.courselearning.po.forum.Post;
import cn.seecoder.courselearning.po.question.Question;
import cn.seecoder.courselearning.service.question.QuestionService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.PostVO;
import cn.seecoder.courselearning.vo.question.QuestionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    QuestionMapper questionMapper;
    @Override
    public ResultVO<QuestionVO> create(QuestionVO questionVOVO) {
        Question question=new Question(questionVOVO);
        //number为成功插入条数
        //添加 2021.7.7
        //如果帖子标题或是内容为空，那么返回创建失败
        if(question.getDescription().equals("")) return new ResultVO<QuestionVO>(Constant.REQUEST_FAIL,
                "请输入题干");
        if(question.getAnswer().equals("")) return new ResultVO<QuestionVO>(Constant.REQUEST_FAIL,
                "请输入答案");
        int number=questionMapper.insert(question);
        if(number==0){
            return new ResultVO<>(Constant.REQUEST_FAIL, "创建题目失败");
        }else {
            //获取自增长的主键值
            QuestionVO res=new QuestionVO(question);
            res.setQuestionId(questionMapper.getLastedQuestionId());
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "创建题目成功", res);
        }
    }

    @Override
    public List<QuestionVO> getQuestionByCourseId(int courseId) {

        List<Question> questionList=questionMapper.getQuestionsByCourseId(courseId);
        //排序
        List<QuestionVO> res=new ArrayList<>();
        for(Question question:questionList){
            res.add(new QuestionVO(question));
        }
        res.sort(new Comparator<QuestionVO>(){
            @Override
            public int compare(QuestionVO o1, QuestionVO o2) {
                return Integer.compare(o1.getQuestionId(), o2.getQuestionId());
            }
        });

        return res;
    }
}
