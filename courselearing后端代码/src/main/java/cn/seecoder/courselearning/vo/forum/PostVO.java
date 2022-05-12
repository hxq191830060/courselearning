package cn.seecoder.courselearning.vo.forum;

import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.forum.Post;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.serviceimpl.user.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;
import java.util.Date;


public class PostVO implements Comparable<PostVO>{


    private int postId;
    private int courseId;
    private int userId;

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String title;
    private String content;

    public PostVO(){

    }

    public PostVO(@NonNull Post post){
        this.postId=post.getPostId();
        this.courseId=post.getCourseId();
        this.userId=post.getUserId();
        this.createTime=post.getCreateTime();
        this.title=post.getTitle();
        this.content=post.getContent();
    }

    public PostVO(@NonNull Post post,String userName){
        this.postId=post.getPostId();
        this.courseId=post.getCourseId();
        this.userId=post.getUserId();
        this.userName=userName;
        this.createTime=post.getCreateTime();
        this.title=post.getTitle();
        this.content=post.getContent();
    }
    public PostVO(int postId,int courseId,int userId,String userName,Date createTime,String title,String content){
        this.postId=postId;
        this.courseId=courseId;
        this.userId=userId;
        this.userName=userName;
        this.createTime=createTime;
        this.title=title;
        this.content=content;
    }

    //测试用的
    public PostVO(int courseId,int userId,String title,String content){
        this.courseId=courseId;
        this.userId=userId;
        this.title=title;
        this.content=content;
    }




    public int getPostId() {
        return postId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName(){
        return userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(PostVO o) {
        return this.getCreateTime().compareTo(o.getCreateTime());
    }
}
