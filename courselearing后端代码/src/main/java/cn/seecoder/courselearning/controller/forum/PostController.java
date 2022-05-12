package cn.seecoder.courselearning.controller.forum;

import cn.seecoder.courselearning.mapperservice.forum.PostMapper;
import cn.seecoder.courselearning.service.forum.PostService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.PostVO;
import io.swagger.annotations.Api;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    PostService postService;


    //获取某个课程的讨论区中的所有帖子
    @GetMapping("/getAll/{courseId}")
    public List<PostVO> getAllPostsByCourseId(@PathVariable int courseId){
        //System.out.println("here");
        return postService.getAllPostsByCourseId(courseId);
    }

    //前端传进来一个PostVO对象，然后在数据库中插入对应的帖子信息
    //对象中需要包含courseId,userId,title,content
    //在数据库中插入信息后，返回一个PostVO
    @PostMapping("/create")
    public ResultVO<PostVO> create(@RequestBody PostVO postVO){
        return postService.create(postVO);
    }


    //返回用户有创建的所有帖子
    @GetMapping("/getOneUserAllPosts/{userId}")
    public List<PostVO> getOneUserAllPosts(@PathVariable Integer userId){
        return postService.getOneUserAllPosts(userId);
    }


    //删除指定的贴
    @GetMapping("/delete/{postId}")
    public ResultVO<String>delete(@PathVariable Integer postId){
        return postService.delete(postId);
    }


    //根据postId获取对用的Post数据
    @GetMapping("/getPostByPostId/{postId}")
    public PostVO getPostByPostId(@PathVariable Integer postId){
        return postService.getPostByPostId(postId);
    }
}
