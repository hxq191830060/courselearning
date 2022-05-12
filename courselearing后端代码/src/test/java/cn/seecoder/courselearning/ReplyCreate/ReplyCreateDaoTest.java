package cn.seecoder.courselearning.ReplyCreate;

import cn.seecoder.courselearning.mapperservice.forum.ReplyMapper;
import cn.seecoder.courselearning.po.forum.Post;
import cn.seecoder.courselearning.po.forum.Reply;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReplyCreateDaoTest {

    @Resource
    ReplyMapper replyMapper;

    //测试ReplyMapper的insert()
    @Test
    @Transactional
    public void test1(){
        //创建测试对象，插入表中
        Reply reply=new Reply(1,1,new Date(),"test1");
        replyMapper.insert(reply);

        //获取最近插入的Reply对象
        int lastedInsertId=replyMapper.getLastedReplyId();
        Reply lastedInsertReply=null;
        List<Reply>list= replyMapper.seleceReplyListByPostId(reply.getPostId());
        int n=list.size();
        for(int i=0;i<n;i+=1){
            Reply temp=list.get(i);
            if(temp.getReplyId()==lastedInsertId){
                lastedInsertReply=temp;
                break;
            }
        }

        //检测最近插入的Reply对象与创建的Reply对象是否相同
        Assert.assertTrue(reply.getPostId()==lastedInsertReply.getPostId());
        Assert.assertTrue(reply.getUserId()==lastedInsertReply.getUserId());
        Assert.assertTrue(reply.getContent().equals(lastedInsertReply.getContent()));
    }


    @Test
    @Transactional
    public void test2(){
        //创建测试对象，插入表中
        Reply reply=new Reply(3,2,new Date(),"test2");
        replyMapper.insert(reply);

        //获取最近插入的Reply对象
        int lastedInsertId=replyMapper.getLastedReplyId();
        Reply lastedInsertReply=null;
        List<Reply>list= replyMapper.seleceReplyListByPostId(reply.getPostId());
        int n=list.size();
        for(int i=0;i<n;i+=1){
            Reply temp=list.get(i);
            if(temp.getReplyId()==lastedInsertId){
                lastedInsertReply=temp;
                break;
            }
        }

        //检测最近插入的Reply对象与创建的Reply对象是否相同
        Assert.assertTrue(reply.getPostId()==lastedInsertReply.getPostId());
        Assert.assertTrue(reply.getUserId()==lastedInsertReply.getUserId());
        Assert.assertTrue(reply.getContent().equals(lastedInsertReply.getContent()));
    }


    @Test
    @Transactional
    public void test3(){
        //创建测试对象，插入表中
        Reply reply=new Reply(4,2,new Date(),"test1");
        replyMapper.insert(reply);

        //获取最近插入的Reply对象
        int lastedInsertId=replyMapper.getLastedReplyId();
        Reply lastedInsertReply=null;
        List<Reply>list= replyMapper.seleceReplyListByPostId(reply.getPostId());
        int n=list.size();
        for(int i=0;i<n;i+=1){
            Reply temp=list.get(i);
            if(temp.getReplyId()==lastedInsertId){
                lastedInsertReply=temp;
                break;
            }
        }

        //检测最近插入的Reply对象与创建的Reply对象是否相同
        Assert.assertTrue(reply.getPostId()==lastedInsertReply.getPostId());
        Assert.assertTrue(reply.getUserId()==lastedInsertReply.getUserId());
        Assert.assertTrue(reply.getContent().equals(lastedInsertReply.getContent()));
    }
}
