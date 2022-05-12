package cn.seecoder.courselearning.ReplyCreate;

import cn.seecoder.courselearning.mapperservice.forum.ReplyMapper;
import cn.seecoder.courselearning.po.forum.Reply;
import cn.seecoder.courselearning.service.forum.ReplyService;
import cn.seecoder.courselearning.vo.forum.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReplyCreateServiceTest {

    @Resource
    ReplyService replyService;

    @Resource
    ReplyMapper replyMapper;

    //参数内含有postId，userId，content
    @Test
    @Transactional
    public void test1(){
        //创建一个ReplyVO对象用于测试
        ReplyVO replyVO=new ReplyVO(1,2,"test1");
        replyService.createReply(replyVO);
        int postId=replyMapper.getLastedReplyId();

        //获取最近一次插入的Reply对象
        Reply lastedInsertReply=null;
        List<Reply> list= replyMapper.seleceReplyListByPostId(replyVO.getPostId());
        int n=list.size();
        for(int i=0;i<n;i+=1){
            Reply temp=list.get(i);
            if(temp.getReplyId()==postId){
                lastedInsertReply=temp;
                break;
            }
        }

        //检查最近一次插入的Reply对象的属性与测试的ReplyVO对象的属性是否相同
        Assert.assertTrue(replyVO.getPostId()==lastedInsertReply.getPostId());
        Assert.assertTrue(replyVO.getUserId()== lastedInsertReply.getUserId());
        Assert.assertTrue(replyVO.getContent().equals(lastedInsertReply.getContent()));
    }

    //参数内含有postId，userId，content
    @Test
    @Transactional
    public void test2(){
        //创建一个ReplyVO对象用于测试
        ReplyVO replyVO=new ReplyVO(3,1,"test2");
        replyService.createReply(replyVO);
        int postId=replyMapper.getLastedReplyId();

        //获取最近一次插入的Reply对象
        Reply lastedInsertReply=null;
        List<Reply> list= replyMapper.seleceReplyListByPostId(replyVO.getPostId());
        int n=list.size();
        for(int i=0;i<n;i+=1){
            Reply temp=list.get(i);
            if(temp.getReplyId()==postId){
                lastedInsertReply=temp;
                break;
            }
        }

        //检查最近一次插入的Reply对象的属性与测试的ReplyVO对象的属性是否相同
        Assert.assertTrue(replyVO.getPostId()==lastedInsertReply.getPostId());
        Assert.assertTrue(replyVO.getUserId()== lastedInsertReply.getUserId());
        Assert.assertTrue(replyVO.getContent().equals(lastedInsertReply.getContent()));
    }

    //参数内含有postId，userId，content
    @Test
    @Transactional
    public void test3(){
        //创建一个ReplyVO对象用于测试
        ReplyVO replyVO=new ReplyVO(4,3,"test3");
        replyService.createReply(replyVO);
        int postId=replyMapper.getLastedReplyId();

        //获取最近一次插入的Reply对象
        Reply lastedInsertReply=null;
        List<Reply> list= replyMapper.seleceReplyListByPostId(replyVO.getPostId());
        int n=list.size();
        for(int i=0;i<n;i+=1){
            Reply temp=list.get(i);
            if(temp.getReplyId()==postId){
                lastedInsertReply=temp;
                break;
            }
        }

        //检查最近一次插入的Reply对象的属性与测试的ReplyVO对象的属性是否相同
        Assert.assertTrue(replyVO.getPostId()==lastedInsertReply.getPostId());
        Assert.assertTrue(replyVO.getUserId()== lastedInsertReply.getUserId());
        Assert.assertTrue(replyVO.getContent().equals(lastedInsertReply.getContent()));
    }
}
