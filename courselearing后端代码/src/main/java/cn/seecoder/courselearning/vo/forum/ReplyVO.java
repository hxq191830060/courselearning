package cn.seecoder.courselearning.vo.forum;

import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.forum.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;
import java.util.Date;

public class ReplyVO {
    @Resource
    private UserMapper userMapper;

    private int postId;
    private int replyId;
    private int userId;
    private String userName;

    public ReplyVO(){

    }

    public ReplyVO(@NonNull Reply reply){
        this.content= reply.getContent();
        this.replyId=reply.getReplyId();
        this.postId=reply.getPostId();
        this.createTime=reply.getCreateTime();
        this.userId=reply.getUserId();

        this.userName=userMapper.selectByPrimaryKey(userId).getUname();
    }

    public ReplyVO(@NonNull Reply reply,String userName){
        this.userName=userName;
        this.content= reply.getContent();
        this.replyId=reply.getReplyId();
        this.postId=reply.getPostId();
        this.createTime=reply.getCreateTime();
        this.userId=reply.getUserId();
    }

    public ReplyVO(int postId,int replyId,int userId,String userName,Date createTime,String content){
        this.userName=userName;
        this.postId=postId;
        this.replyId=replyId;
        this.userId=userId;
        this.content=content;
        this.createTime=createTime;
    }

    public ReplyVO(int postId,int replyId,int userId,Date createTime,String content){
        this.userName=userMapper.selectByPrimaryKey(userId).getUname();
        this.postId=postId;
        this.replyId=replyId;
        this.userId=userId;
        this.content=content;
        this.createTime=createTime;
    }


    public ReplyVO(int postId,int userId,String content){
        this.postId=postId;
        this.replyId=replyId;
        this.userId=userId;
        this.content=content;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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



}
