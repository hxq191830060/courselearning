package cn.seecoder.courselearning.vo.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsInfoVO {
    private int studentNum;
    private int teacherNum;
    private int adminNum;
    private int courseNum;
    private int logininNum;
}
