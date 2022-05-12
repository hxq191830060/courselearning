package cn.seecoder.courselearning.po.forum;

import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.vo.forum.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

public class Post {
    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", courseId=" + courseId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    private int postId;
    private int courseId;


    private int userId;

    private Date createTime;
    private String title;
    private String content;



    public int getPostId() {
        return postId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getUserId() {
        return userId;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post(){

    }
    public Post(int postId,int userId,int courseId,Date createTime,String title,String content){
        this.postId=postId;
        this.userId=userId;
        this.courseId=courseId;
        this.createTime=createTime;
        this.title=title;
        this.content=content;
    }

    public Post(@NonNull PostVO postVO){
        this.postId=postVO.getPostId();
        this.userId=postVO.getUserId();
        this.courseId=postVO.getCourseId();
        this.createTime=postVO.getCreateTime();
        this.title=postVO.getTitle();
        this.content=postVO.getContent();
    }


    public Post(int courseId,int userId,Date createTime,String title,String content){
        this.courseId=courseId;
        this.userId=userId;
        this.createTime=createTime;
        this.title=title;
        this.content=content;
    }

}
