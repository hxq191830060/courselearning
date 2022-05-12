package cn.seecoder.courselearning.po.question;

import cn.seecoder.courselearning.vo.question.ExamVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

public class Exam {
    private int examId;
    private int courseId;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    private List<Integer> questionIds;

    public Exam(@NonNull ExamVO examVO){
        this.examId = examVO.getExamId();
        this.courseId = examVO.getCourseId();
        this.name = examVO.getName();
        this.description = examVO.getDescription();
        this.startTime = examVO.getStartTime();
        this.endTime = examVO.getEndTime();
        this.questionIds = examVO.getQuestionIds();
    }

    public Exam(int examId, int courseId, String name, String description, Date startTime, Date endTime, List<Integer> questionIds) {
        this.examId = examId;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionIds = questionIds;
    }

    public Exam() {
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }
}
