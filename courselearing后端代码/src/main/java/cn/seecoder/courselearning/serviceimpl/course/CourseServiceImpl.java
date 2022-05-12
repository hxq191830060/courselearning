package cn.seecoder.courselearning.serviceimpl.course;

import cn.seecoder.courselearning.mapperservice.course.CourseLikesMapper;
import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.service.order.QueryOrderService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.PageInfoUtil;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.CourseVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private QueryOrderService orderService;

    @Resource
    private  CourseLikesMapper courseLikesMapper;


    @Autowired
    public void setOrderService(QueryOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public PageInfo<CourseVO> getCourses(Integer currPage, Integer pageSize, Integer uid, String key) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);

        //这里查出来的course，属性都是全的
        PageInfo<Course> po = new PageInfo<>(courseMapper.queryAll(key));
        return getCourseVOPageInfo(uid, po);
    }


    @Override
    public PageInfo<CourseVO> getCoursesByType(Integer currPage, Integer pageSize, Integer uid, String type) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);
        //这里查询出的是完整的Course信息
        PageInfo<Course> po = new PageInfo<>(courseMapper.selectByType(type));
        return getCourseVOPageInfo(uid, po);
    }

    @Override
    public PageInfo<CourseVO> getHotCourses(Integer currPage, Integer pageSize, Integer uid) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);
        PageInfo<Course> po = new PageInfo<>(courseMapper.selectHotCourses());
        return getCourseVOPageInfo(uid, po);
    }

    @Override
    public List<CourseVO> getBoughtCourses(Integer uid) {
        List<CourseVO> ret = new ArrayList<>();
        List<Course> courseList = courseMapper.selectByStudentId(uid);
        for(Course course: courseList){
            //设置了liked
            ret.add(new CourseVO(course, true, false,userLikeCourse(uid,course.getId())));
        }
        return ret;
    }

    @Override
    public List<CourseVO> getManageableCourses(Integer uid) {
        List<CourseVO> ret = new ArrayList<>();
        List<Course> courseList = courseMapper.selectByTeacherId(uid);
        for(Course course: courseList){
            ret.add(new CourseVO(course, false, true,userLikeCourse(uid,course.getId())));
        }
        return ret;
    }


    @Override
    public CourseVO getCourse(Integer courseId, Integer uid) {
        //得到课程信息，包含id，name，type，intro，pictrue，school，create_time,
        //delete_time,cost,teacher_id,teacher_name,likes
        // (liked,bought,manageable这三个未赋值)
        Course course = courseMapper.selectByPrimaryKey(courseId);

        boolean bought = false;
        boolean manageable = false;
        if(uid != null && uid > 0) {
            //查询出该用户最近的课程订单
            CourseOrder order = orderService.queryMostRecentOrder(uid, courseId);
            if(order != null)
                bought = order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS);
            manageable = uid.equals(course.getTeacherId());
        }
        return new CourseVO(course, bought, manageable,userLikeCourse(uid,courseId));
    }

    //判断该用户是否已经给课程点过赞
    public boolean userLikeCourse(Integer uid,Integer courseId){
        int count=courseLikesMapper.count(courseId,uid);
        if(count==0){
            return false;
        }
        return true;
    }


    @Override
    public ResultVO<CourseVO> createCourse(CourseVO courseVO) {
        courseVO.setCreateTime(new Date());
        for(Course course: courseMapper.selectByTeacherId(courseVO.getTeacherId())){
            if (course.getName().equals(courseVO.getName()))
                return new ResultVO<>(Constant.REQUEST_FAIL, "已存在同名课程！");
        }
        Course course = new Course(courseVO);
        if(courseMapper.insert(course) > 0){
            return new ResultVO<CourseVO>(Constant.REQUEST_SUCCESS, "课程创建成功。", new CourseVO(course, false, true));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }


    @Override
    public Course getByPrimaryKey(Integer courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }


    private PageInfo<CourseVO> getCourseVOPageInfo(Integer uid, PageInfo<Course> po) {
        //把Course的PageInfo，转换为CourseVO的PageInfo
        PageInfo<CourseVO> result = PageInfoUtil.convert(po, CourseVO.class);

        List<CourseVO> courseVOList=result.getList();//获取PageInfo中的CourseVO


        if(uid != null && uid > 0){
            List<CourseVO> voList = result.getList();
            for(CourseVO vo: voList){

                //设置每个CourseVO的liked
                vo.setLiked(userLikeCourse(uid, vo.getId()));

                CourseOrder order = orderService.queryMostRecentOrder(uid, vo.getId());
                if(order != null)
                    vo.setBought(order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS));
                vo.setManageable(uid.equals(vo.getTeacherId()));
            }
        }
        return result;
    }

    @Override
    public ResultVO<CourseVO> setCourseLike(Integer uid, Integer courseId) {
        //todo，进行课程点赞
        //通过courseId取得课程信息,包含了该课程拥有的点赞数
        Course course=courseMapper.selectByPrimaryKey(courseId);

        //创建CourseVO对象
        CourseVO courseVO=new CourseVO(course);

        //计算当前课程的点赞数
        int count=course.getLikes();

        //判断当前用户否是已经点过赞
        boolean liked=courseLikesMapper.count(courseId,uid)!=0;

        ResultVO<CourseVO> result;
        if(liked){
            //已经点赞过
            courseVO.setLiked(count!=0);
            result=new ResultVO<>(Constant.REQUEST_FAIL,"ERROR,该用户已经点过赞",courseVO);
        }else{
            //没有点赞过
            //向course_like中插入新记录
            courseLikesMapper.insert(courseId,uid);
            count+=1;
            courseVO.setLiked(true);
            courseVO.setLikes(count);
            result=new ResultVO<>(Constant.REQUEST_SUCCESS,"点赞成功",courseVO);
        }
        return result;


    }

    @Override
    public ResultVO<CourseVO> cancelCourseLike(Integer uid, Integer courseId) {

        Course course=courseMapper.selectByPrimaryKey(courseId);

        //获取当前的点赞数
        int count=course.getLikes();

        CourseVO courseVO=new CourseVO(course);

        //判断当前用户否是已经点过赞
        boolean liked=courseLikesMapper.deleteByPrimaryKey(courseId,uid)==1;

        if(liked){
            //点过赞
            count-=1;
            courseVO.setLiked(count!=0);
            courseVO.setLikes(count);
            return new ResultVO<CourseVO>(Constant.REQUEST_SUCCESS,"取消点赞成功",courseVO);
        }else{
            //没有点赞过
            return new ResultVO<CourseVO>(Constant.REQUEST_FAIL,"取消点赞失败",courseVO);
        }

    }

}
