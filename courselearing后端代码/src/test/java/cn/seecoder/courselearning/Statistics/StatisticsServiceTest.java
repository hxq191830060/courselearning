package cn.seecoder.courselearning.Statistics;

import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.service.statistics.StatisticsService;
import cn.seecoder.courselearning.util.StartInitAndSchedule;
import cn.seecoder.courselearning.vo.statistics.StatisticsInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticsServiceTest {

    @Resource
    StatisticsService statisticsService;

    @Resource
    UserMapper userMapper;

    @Resource
    CourseMapper courseMapper;

    @Test
    @Transactional
    public void test(){
        //测试
        StatisticsInfoVO statisticsInfoVO=statisticsService.getInfo();

        Assert.assertTrue(statisticsInfoVO.getStudentNum()==userMapper.getNumberByName("STUDENT"));

        Assert.assertTrue(statisticsInfoVO.getAdminNum()==userMapper.getNumberByName("ADMIN"));

        Assert.assertTrue(statisticsInfoVO.getTeacherNum()==userMapper.getNumberByName("TEACHER"));

        Assert.assertTrue(statisticsInfoVO.getCourseNum()==courseMapper.getCourseNum());

        Assert.assertTrue(statisticsInfoVO.getLogininNum()==StartInitAndSchedule.getLogininNum());
    }
}
