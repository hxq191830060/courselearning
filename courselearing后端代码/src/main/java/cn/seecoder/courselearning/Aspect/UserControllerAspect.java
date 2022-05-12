package cn.seecoder.courselearning.Aspect;

import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Aspect
@Component
public class UserControllerAspect {

    private final static String phonePattern="1\\d{10}";
    private final static String passwordPattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";

    @Around(value = "execution(public * register(*))")
    public ResultVO<UserVO> around(ProceedingJoinPoint point) throws Throwable {
        UserVO userVO=(UserVO)point.getArgs()[0];

        String phone= userVO.getPhone();
        //对电话号码进行验证
        if(!phoneIsLegal(phone)){
            return new ResultVO<UserVO>(Constant.REQUEST_FAIL,"手机号码格式错误",userVO);
        }

        String password= userVO.getPassword();
        if(!passwordIsLegal(password)){
            return new ResultVO<UserVO>(Constant.REQUEST_FAIL,"密码格式错误，密码8位-20位，只能包含数字和字母，并且要同时包含字母和数字",userVO);
        }
        return (ResultVO<UserVO>) point.proceed();
    }

    public boolean phoneIsLegal(String phone){
        //电话号码必须为11位，并且以1开头
        Pattern pattern=Pattern.compile(phonePattern);
        return pattern.matcher(phone).find();
    }

    public boolean passwordIsLegal(String password){
        //密码8位-20位，包含字母和数字
        Pattern pattern=Pattern.compile(passwordPattern);
        return pattern.matcher(password).find();
    }
}
