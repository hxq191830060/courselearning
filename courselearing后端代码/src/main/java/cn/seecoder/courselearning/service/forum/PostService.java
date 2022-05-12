package cn.seecoder.courselearning.service.forum;

import cn.seecoder.courselearning.po.forum.Post;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.forum.PostVO;

import java.util.List;

public interface PostService {
    //返回某个课程下的所有帖子，并按时间排序(时间早的在前)
    List<PostVO> getAllPostsByCourseId(int courseId);

    //创建新的帖子
    ResultVO<PostVO>create(PostVO postVO);

    //删除指定的贴
    ResultVO<String> delete(int postId);

    //返回某个用户创建的所有贴子
    List<PostVO>getOneUserAllPosts(int userId);

    PostVO getPostByPostId(Integer postId);
}
