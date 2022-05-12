package cn.seecoder.courselearning.controller.question;


import cn.seecoder.courselearning.service.forum.PostService;
import cn.seecoder.courselearning.service.question.QuestionService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.PostVO;
import cn.seecoder.courselearning.vo.question.QuestionVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    QuestionService questionService;



    //创建问题
    @PostMapping("/create")
    public ResultVO<QuestionVO> create(@RequestBody QuestionVO questionVO){
        return questionService.create(questionVO);
    }


    //根据courseId,返回该课程下的所有问题
    @GetMapping("/getQuestionByCourseId")
    public List<QuestionVO> getQuestionByCourseId(@RequestParam int courseId){
        return questionService.getQuestionByCourseId(courseId);
    }
}
