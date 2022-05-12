package cn.seecoder.courselearning.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
//用于测试某个方法的执行时间
public class TimeUtil {
    String message;
    long beginTime;
    long endTime;
    public void start(){
        beginTime=System.currentTimeMillis();
    }
    public void end(){
        endTime=System.currentTimeMillis();
        System.out.println("执行"+message+"  耗时"+String.valueOf(endTime-beginTime)+"毫秒");
    }
}
