package cn.seecoder.courselearning.CreateQuestion;

import cn.seecoder.courselearning.mapperservice.question.QuestionMapper;
import cn.seecoder.courselearning.po.question.Question;
import cn.seecoder.courselearning.service.question.QuestionService;
import cn.seecoder.courselearning.vo.question.QuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CreateQuestionServiceTest {

    /*
    //传进来的参数
    {"courseId":"6","description":"aa","answer":"a","analysis":"a"}
    返回结果
    {"code":1,"msg":"创建题目成功","data":{"questionId":2,"courseId":6,"description":"aa","answer":"a","analysis":"a"}}
     */

    @Resource
    QuestionService questionService;

    @Resource
    QuestionMapper questionMapper;


    @Test
    @Transactional
    public void test1(){
        //创建一个测试QuestionVO对象
        QuestionVO questionVO=new QuestionVO(3,"descriptionTest1","answerTest1","analysisTest1");
        questionService.create(questionVO);

        int id=questionMapper.getLastedQuestionId();
        List<Question> list=questionMapper.getQuestionsByCourseId(questionVO.getCourseId());
        for(int i=0;i<list.size();i+=1){
            if(list.get(i).getQuestionId()==id){
                Question insert=list.get(i);
                Assert.assertTrue(questionVO.getCourseId()==insert.getCourseId());
                Assert.assertTrue(questionVO.getDescription().equals(insert.getDescription()));
                Assert.assertTrue(questionVO.getAnalysis().equals(insert.getAnalysis()));
                Assert.assertTrue(questionVO.getAnswer().equals(insert.getAnswer()));
                break;
            }
        }
    }


    @Test
    @Transactional
    public void test2(){
        //创建一个测试QuestionVO对象
        QuestionVO questionVO=new QuestionVO(1,"descriptionTest2","answerTest2","analysisTest2");
        questionService.create(questionVO);

        int id=questionMapper.getLastedQuestionId();
        List<Question> list=questionMapper.getQuestionsByCourseId(questionVO.getCourseId());
        for(int i=0;i<list.size();i+=1){
            if(list.get(i).getQuestionId()==id){
                Question insert=list.get(i);
                Assert.assertTrue(questionVO.getCourseId()==insert.getCourseId());
                Assert.assertTrue(questionVO.getDescription().equals(insert.getDescription()));
                Assert.assertTrue(questionVO.getAnalysis().equals(insert.getAnalysis()));
                Assert.assertTrue(questionVO.getAnswer().equals(insert.getAnswer()));
                break;
            }
        }
    }

    @Test
    @Transactional
    public void test3(){
        //创建一个测试QuestionVO对象
        QuestionVO questionVO=new QuestionVO(4,"descriptionTest3","answerTest3","analysisTest3");
        questionService.create(questionVO);

        int id=questionMapper.getLastedQuestionId();
        List<Question> list=questionMapper.getQuestionsByCourseId(questionVO.getCourseId());
        for(int i=0;i<list.size();i+=1){
            if(list.get(i).getQuestionId()==id){
                Question insert=list.get(i);
                Assert.assertTrue(questionVO.getCourseId()==insert.getCourseId());
                Assert.assertTrue(questionVO.getDescription().equals(insert.getDescription()));
                Assert.assertTrue(questionVO.getAnalysis().equals(insert.getAnalysis()));
                Assert.assertTrue(questionVO.getAnswer().equals(insert.getAnswer()));
                break;
            }
        }
    }
}
