package cn.seecoder.courselearning.controller.user;

import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.user.UserFormVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户登录，注册，查看信息API接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册",notes = "点击注册后，调用该接口")
    @PostMapping("/register")
    //请求体为JSON
    public ResultVO<UserVO> register(@RequestBody UserVO user){
        return userService.userRegister(user);
    }



    @ApiOperation(value = "用户登录",notes ="点击登录后，调用该接口" )
    @PostMapping("/login")
    public ResultVO<UserVO> login(@RequestBody UserFormVO userForm){
        return userService.userLogin(userForm.getPhone(), userForm.getPassword());
    }


    @GetMapping("/{uid}")
    public UserVO getUser(@PathVariable Integer uid){
        return userService.getUser(uid);
    }

}
