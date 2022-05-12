package cn.seecoder.courselearning.controller.statistics;

import cn.seecoder.courselearning.service.statistics.StatisticsService;
import cn.seecoder.courselearning.vo.statistics.StatisticsInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    StatisticsService statisticsService;

    //获取统计信息
    @GetMapping("/getInfo")
    public StatisticsInfoVO getStatisticsInfo(){
        return statisticsService.getInfo();
    }
}
