package cn.seecoder.courselearning.serviceimpl.forum;

import cn.seecoder.courselearning.mapperservice.forum.PostMapper;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.forum.Post;
import cn.seecoder.courselearning.service.forum.PostService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.forum.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Resource
    PostMapper postMapper;

    @Resource
    UserMapper userMapper;

    //返回某个课程下的所有帖子，并按时间排序(时间早的在前)
    @Override
    public List<PostVO> getAllPostsByCourseId(int courseId) {
        //获取某个课程下的所有帖子
        List<Post> postList=postMapper.getAllPostsByCourseId(courseId);

        List<PostVO> list=new ArrayList<>();

        int n=postList.size();
        //创建PostVO的列表
        for (Post post : postList) {
            list.add(new PostVO(post, userMapper.selectByPrimaryKey(post.getUserId()).getUname()));
        }

        list.sort(new Comparator<PostVO>() {
            @Override
            public int compare(PostVO o1, PostVO o2) {
                return o1.getCreateTime().compareTo(o2.getCreateTime());
            }
        });

        //对list进行排序
        return list;

    }

    @Override
    public ResultVO<PostVO> create(PostVO postVO) {
        Post post=new Post(postVO);
        post.setCreateTime(new Date());
        //number为成功插入条数
        //改动 2021.7.7
        //如果帖子标题或是内容为空，那么返回创建失败
        if(post.getTitle().equals("")) return new ResultVO<PostVO>(Constant.REQUEST_FAIL,
                "请输入标题");
        if(post.getContent().equals("")) return new ResultVO<PostVO>(Constant.REQUEST_FAIL,
                "请输入内容");
        int number=postMapper.insert(post);
        if(number==0){
            return new ResultVO<PostVO>(Constant.REQUEST_FAIL,"创贴失败");
        }else {
            //获取自增长的主键值
            post.setPostId(postMapper.getLastedPostId());
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "创帖成功", new PostVO(post, userMapper.selectByPrimaryKey(postVO.getUserId()).getUname()));
        }
    }

    //删除操作
    @Override
    public ResultVO<String> delete(int postId) {
        int res=postMapper.delete(postId);

        if(res!=0){
            return new ResultVO<String>(Constant.REQUEST_SUCCESS,"删除成功");
        }else {
            return new ResultVO<String>(Constant.REQUEST_FAIL,"删除失败");
        }
    }

    @Override
    public List<PostVO> getOneUserAllPosts(int userId) {
        List<Post> postList= postMapper.getOneUserAllPosts(userId);
        List<PostVO> list=new ArrayList<>();
        int n=postList.size();
        for(int i=0;i<n;i+=1){
            list.add(new PostVO(postList.get(i)));
        }

        return list;
    }

    @Override
    public PostVO getPostByPostId(Integer postId) {
        PostVO postVO=new PostVO(postMapper.getPostByPostId(postId));
        postVO.setUserName(userMapper.selectByPrimaryKey(postVO.getUserId()).getUname());
        return postVO;
    }
}
