package cn.seecoder.courselearning.serviceimpl.statistics;

import cn.seecoder.courselearning.util.StartInitAndSchedule;
import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.service.statistics.StatisticsService;
import cn.seecoder.courselearning.util.StartInitAndSchedule;
import cn.seecoder.courselearning.vo.statistics.StatisticsInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    UserMapper userMapper;

    @Resource
    CourseMapper courseMapper;

    @Override
    public StatisticsInfoVO getInfo() {
        StatisticsInfoVO res=new StatisticsInfoVO(userMapper.getNumberByName("STUDENT"),
                userMapper.getNumberByName("TEACHER"),
                userMapper.getNumberByName("ADMIN"),
                courseMapper.getCourseNum(),
                StartInitAndSchedule.getLogininNum()
                );

        return res;
    }
}
