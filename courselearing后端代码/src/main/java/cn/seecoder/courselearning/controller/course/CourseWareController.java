package cn.seecoder.courselearning.controller.course;

import cn.seecoder.courselearning.service.course.CourseWareService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.CourseWareVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "课程课件API接口")
@RestController
@RequestMapping("/course_ware")
public class CourseWareController {
    @Resource
    private CourseWareService courseWareService;

    /**
     * 获取单个课件的信息
     * @param uid 用户id
     * @param courseWareId 课件id
     */
    @GetMapping("/{courseWareId}")
    public CourseWareVO getCourseWare(@RequestParam(required = false, defaultValue = "") Integer uid,
                                            @PathVariable Integer courseWareId) {
        return courseWareService.getCourseWare(uid, courseWareId);
    }

    /**
     * 获取课件列表
     * 如果带参数uid，则需要判断该用户是否购买过课程
     * @param uid 用户id
     * @param courseId 课程id
     */
    @ApiOperation(value = "查看课程的课件信息",notes = "学生点击学习课程后/教师进入某个课程页面，通过该接口获取课程的课件信息")
    @GetMapping("/course/{courseId}")
    public List<CourseWareVO> getCourseWares(@RequestParam(required = false, defaultValue = "") Integer uid,
                                            @PathVariable Integer courseId) {
        return courseWareService.getAllCourseWare(uid, courseId);
    }

    /**
     * 教师创建课件
     * @param courseWare 课件VO
     */

    @ApiOperation(value = "创建课件",notes = "教师点击上传课件时，先会使用FileController的upload接口将文件上传到服务器，然后调用该接口，在数据库中插入相关的课件信息")
    @PostMapping("/create")
    public ResultVO<CourseWareVO> createCourseWare(@RequestBody CourseWareVO courseWare){
        return courseWareService.createCourseWare(courseWare);
    }


    /**
     * 教师更新课件
     * @param courseWare 课件VO
     */
    @ApiOperation(value = "更新课件",notes = "教师进行更新课件操作时，调用该接口")
    @PostMapping("/update")
    public ResultVO<CourseWareVO> updateCourseWare(@RequestBody CourseWareVO courseWare){
        return courseWareService.updateCourseWare(courseWare);
    }


    /**
     * 教师删除课件
     * @param courseWareId 课件Id
     */
    @ApiOperation(value = "删除课件",notes ="教师进行删除课件操作时，调用该接口" )
    @PostMapping("/delete/{courseWareId}")
    public ResultVO<String> deleteCourseWare(@PathVariable Integer courseWareId){
        return courseWareService.deleteCourseWare(courseWareId);
    }
}
