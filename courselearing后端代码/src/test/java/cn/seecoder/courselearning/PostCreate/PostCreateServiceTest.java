package cn.seecoder.courselearning.PostCreate;


import cn.seecoder.courselearning.mapperservice.forum.PostMapper;
import cn.seecoder.courselearning.po.forum.Post;
import cn.seecoder.courselearning.service.forum.PostService;
import cn.seecoder.courselearning.vo.forum.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


//测试创建帖子的Service层
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostCreateServiceTest {
    @Resource
    PostService postService;

    @Resource
    PostMapper postMapper;

    @Test
    public void test1(){
        //创建一个PostVO对象用于测试
        PostVO postVO=new PostVO(1,1,"testTitle1","testContent1");
        postService.create(postVO);

        //从数据库取得刚插入的对象，检验是否一致
        Post post=postMapper.getPostByPostId(postMapper.getLastedPostId());

        Assert.assertTrue(postVO.getCourseId()==post.getCourseId());
        Assert.assertTrue(postVO.getUserId()==post.getUserId());
        Assert.assertTrue(postVO.getTitle().equals(post.getTitle()));
        Assert.assertTrue(postVO.getContent().equals(post.getContent()));
    }


    @Test
    public void test2(){
        //创建一个PostVO对象用于测试
        PostVO postVO=new PostVO(2,3,"testTitle2","testContent2");
        postService.create(postVO);

        //从数据库取得刚插入的对象，检验是否一致
        Post post=postMapper.getPostByPostId(postMapper.getLastedPostId());

        Assert.assertTrue(postVO.getCourseId()==post.getCourseId());
        Assert.assertTrue(postVO.getUserId()==post.getUserId());
        Assert.assertTrue(postVO.getTitle().equals(post.getTitle()));
        Assert.assertTrue(postVO.getContent().equals(post.getContent()));
    }

    @Test
    public void test3(){
        //创建一个PostVO对象用于测试
        PostVO postVO=new PostVO(4,1,"testTitle3","testContent3");
        postService.create(postVO);

        //从数据库取得刚插入的对象，检验是否一致
        Post post=postMapper.getPostByPostId(postMapper.getLastedPostId());

        Assert.assertTrue(postVO.getCourseId()==post.getCourseId());
        Assert.assertTrue(postVO.getUserId()==post.getUserId());
        Assert.assertTrue(postVO.getTitle().equals(post.getTitle()));
        Assert.assertTrue(postVO.getContent().equals(post.getContent()));
    }
}
