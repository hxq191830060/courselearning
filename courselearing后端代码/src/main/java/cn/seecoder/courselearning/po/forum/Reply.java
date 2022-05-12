package cn.seecoder.courselearning.po.forum;

import cn.seecoder.courselearning.vo.forum.ReplyVO;
import org.springframework.lang.NonNull;

import java.util.Date;

public class Reply {
    private int postId;
    private int replyId;
    private int userId;

    private Date createTime;
    private String content;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Reply(){

    }

    public Reply(int postId,int replyId,int userId,Date createTime,String content){
        this.postId=postId;
        this.replyId=replyId;
        this.userId=userId;
        this.createTime=createTime;
        this.content=content;
    }

    public Reply(int postId,int userId,Date createTime,String content){
        this.postId=postId;
        this.userId=userId;
        this.createTime=createTime;
        this.content=content;
    }

    public Reply(@NonNull ReplyVO replyVO){
        this.postId=replyVO.getPostId();
        this.replyId=replyVO.getReplyId();
        this.userId=replyVO.getUserId();
        this.createTime=replyVO.getCreateTime();
        this.content=replyVO.getContent();
    }
}
