package cn.seecoder.courselearning.controller.question;


import cn.seecoder.courselearning.service.question.ExamService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.question.ExamVO;
import cn.seecoder.courselearning.vo.question.QuestionVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Resource
    ExamService examService;

    @PostMapping("/create")
    public ResultVO<ExamVO> create(@RequestBody ExamVO examVO){
        return examService.create(examVO);
    }

    @GetMapping("/getExamsByCourseId")
    public List<ExamVO> getQuestionByCourseId(@RequestParam int courseId){
        return examService.getExamsByCourseId(courseId);
    }
}
