package cn.seecoder.courselearning.mapperservice.forum;

import cn.seecoder.courselearning.po.forum.Post;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.forum.PostVO;
import io.swagger.models.auth.In;

import java.util.List;

public interface PostMapper {
    List<Post> getAllPostsByCourseId(int courseId);

    Integer insert(Post post);

    Integer getLastedPostId();

    Integer delete(int postId);

    List<Post>getOneUserAllPosts(int userId);

    Post getPostByPostId(int postId);
}
