package cn.seecoder.courselearning.PostCreate;


import cn.seecoder.courselearning.mapperservice.forum.PostMapper;
import cn.seecoder.courselearning.po.forum.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


//测试创建帖子的Dao层
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostCreateDaoTest {

    @Resource
    PostMapper postMapper;


    //以下测试PostMapper的insert()
    @Test
    @Transactional
    public void testInsert1(){
        Post post=new Post(2,1,new Date(),"testTitle1","testContent1");
        postMapper.insert(post);

        //获取刚插入的Post的PostId
        int postId=postMapper.getLastedPostId();

        Post insertedPost=postMapper.getPostByPostId(postId);


        //检测插入的Post跟从数据库取出的Post是否相同
        Assert.assertEquals(post.getCourseId(),insertedPost.getCourseId());
        Assert.assertEquals(post.getUserId(),insertedPost.getUserId());
        Assert.assertEquals(post.getTitle(),insertedPost.getTitle());
        Assert.assertEquals(post.getContent(),insertedPost.getContent());
    }

    @Test
    @Transactional
    public void testInsert2(){
        Post post=new Post(4,2,new Date(),"testTitle2","testContent2");
        postMapper.insert(post);

        //获取刚插入的Post的PostId
        int postId=postMapper.getLastedPostId();

        Post insertedPost=postMapper.getPostByPostId(postId);

        //检测插入的Post跟从数据库取出的Post是否相同
        Assert.assertEquals(post.getCourseId(),insertedPost.getCourseId());
        Assert.assertEquals(post.getUserId(),insertedPost.getUserId());
        Assert.assertEquals(post.getTitle(),insertedPost.getTitle());
        Assert.assertEquals(post.getContent(),insertedPost.getContent());
    }

    @Test
    @Transactional
    public void testInsert3(){
        Post post=new Post(1,2,new Date(),"testTitle3","testContent3");
        postMapper.insert(post);

        //获取刚插入的Post的PostId
        int postId=postMapper.getLastedPostId();

        Post insertedPost=postMapper.getPostByPostId(postId);

        //检测插入的Post跟从数据库取出的Post是否相同
        Assert.assertEquals(post.getCourseId(),insertedPost.getCourseId());
        Assert.assertEquals(post.getUserId(),insertedPost.getUserId());
        Assert.assertEquals(post.getTitle(),insertedPost.getTitle());
        Assert.assertEquals(post.getContent(),insertedPost.getContent());
    }


}
