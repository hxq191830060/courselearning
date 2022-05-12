package cn.seecoder.courselearning.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


//服务器启动后自动运行并且执行定时任务
@Component
public class StartInitAndSchedule implements ApplicationRunner {

    private static AtomicInteger logininNum=new AtomicInteger(0);

    public static int getLogininNum(){
        return logininNum.get();
    }

    public static void logininNumAddOne(){
        logininNum.incrementAndGet();
    }
    //每天的0点，执行一次,将登陆人数重为0
    @Scheduled(cron="0 0 0 * * ?")
    public void runAtZero(){
        logininNum.set(0);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
