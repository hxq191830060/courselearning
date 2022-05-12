package cn.seecoder.courselearning.controller.course;

import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.CourseVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "课程信息API接口")
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    /**
     * 查询单一课程
     * @param uid 用户id
     * @param courseId 课程id
     * 改好了
     */
    @ApiOperation(value = "获取指定课程信息",notes = "用户点击游览课程/学习课程时，调用该接口")
    @GetMapping("/{courseId}")
    public CourseVO getCourseById(@RequestParam(required = false, defaultValue = "") Integer uid, @PathVariable Integer courseId) {
        return courseService.getCourse(courseId, uid);
    }


    /**
     * 获取课程列表
     * 如果带参数uid，则需要判断该用户是否购买过课程
     * 如果带参数key，则根据关键字返回分页查询结果
     * @param uid 用户id
     * @param key 关键字
     * 改好了
     */
    @ApiOperation(value = "根据搜索栏输入的内容查询课程，分页返回",notes = "用户搜索课程模块的搜索栏输入内容后，调用该接口")
    @GetMapping("/all/{page}")
    public PageInfo<CourseVO> getCoursesByKey(@RequestParam(required = false, defaultValue = "") Integer uid,
                                                @RequestParam(required = false, defaultValue = "") String key,
                                                @PathVariable Integer page) {
        return courseService.getCourses(page, Constant.COURSE_PAGE_SIZE, uid, key);
    }

    /**
     * 根据分类标签，获取课程列表
     * 如果带参数uid，则需要判断该用户是否购买过课程、该用户是否为课程的教师
     * @param uid 用户id
     * @param type 课程类别
     * 改好了
     */
    @ApiOperation(value = "根据type(初级/中级/高级)分页返回课程信息",notes = "用户打开页面后，初级课程，中级课程，高级课程一栏调用该接口")
    @GetMapping("/type/{page}")
    public PageInfo<CourseVO> getCoursesByType(@RequestParam(required = false, defaultValue = "")  Integer uid,
                                         @RequestParam String type,
                                         @PathVariable Integer page) {
        return courseService.getCoursesByType(page, Constant.COURSE_PAGE_SIZE, uid, type);
    }

    /**
     * 分页获取热门课程列表
     * @param page 页码数
     * 改好了
     */
    @ApiOperation(value = "获取热门课程信息,根据点赞数排序",notes = "用户登录后，热门课程一栏调用该接口")
    @GetMapping("/hot/{page}")
    public PageInfo<CourseVO> getHotCourses(@PathVariable Integer page, Integer uid) {
        return courseService.getHotCourses(page, Constant.COURSE_PAGE_SIZE,uid);
    }

    /**
     * 获取用户已购买的课程列表
     * @param sid 学生id
     * 改好了
     */
    @ApiOperation(value = "显示学生已购课程信息",notes = "学生进入页面时，我的已购课程一栏调用该接口")
    @GetMapping("/sid/{sid}")
    public List<CourseVO> getBoughtCourses(@PathVariable Integer sid) {
        return courseService.getBoughtCourses(sid);
    }

    /**
     * 获取教师可管理的课程列表
     * @param tid 教师id
     * todo
     */
    @ApiOperation(value = "查看教师用户管理的课程信息",notes = "教师进入页面时，调用该接口获取教师管理的课程信息")
    @GetMapping("/tid/{tid}")
    public List<CourseVO> getManageableCourses(@PathVariable Integer tid) {
        return courseService.getManageableCourses(tid);
    }

    /**
     * 教师创建课程
     * @param course 课程VO
     */
    @ApiOperation(value = "教师创建课程",notes = "教师创建课程时，调用该接口")
    @PostMapping("/create")
    public ResultVO<CourseVO> createCourse(@RequestBody CourseVO course) {
        return courseService.createCourse(course);
    }





    /**
     * 用户点赞课程
     * @param uid 用户id
     * @param courseId 课程id
     */
    @ApiOperation(value = "用户点赞",notes = "用户对课程点赞时，调用该接口")
    @PostMapping("/like/{courseId}")
    public ResultVO<CourseVO> setCourseLike(@RequestParam Integer uid, @PathVariable Integer courseId) {
        return courseService.setCourseLike(uid,courseId);
    }

    /**
     * 用户取消点赞课程
     * @param uid 用户id
     * @param courseId 课程id
     */
    @ApiOperation(value = "取消点赞",notes = "用户取消点赞时，调用该接口")
    @PostMapping("/cancel_like/{courseId}")
    public ResultVO<CourseVO> cancelCourseLike(@RequestParam Integer uid, @PathVariable Integer courseId) {
        return courseService.cancelCourseLike(uid,courseId);
    }
}
