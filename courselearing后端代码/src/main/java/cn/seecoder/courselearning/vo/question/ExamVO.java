package cn.seecoder.courselearning.vo.question;

import cn.seecoder.courselearning.po.question.Exam;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

public class ExamVO {
    private int examId;
    private int courseId;
    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private List<Integer> questionIds;

    private List<QuestionVO> questions;

    public ExamVO(@NonNull Exam exam){
        this.examId = exam.getExamId();
        this.courseId = exam.getCourseId();
        this.name = exam.getName();
        this.description = exam.getDescription();
        this.startTime = exam.getStartTime();
        this.endTime = exam.getEndTime();
        this.questionIds = exam.getQuestionIds();
    }

    public ExamVO(int examId, int courseId, String name, String description, Date startTime, Date endTime, List<Integer> questionIds, List<QuestionVO> questions) {
        this.examId = examId;
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionIds = questionIds;
        this.questions = questions;
    }

    public ExamVO() {
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

    public List<QuestionVO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionVO> questions) {
        this.questions = questions;
    }
}
