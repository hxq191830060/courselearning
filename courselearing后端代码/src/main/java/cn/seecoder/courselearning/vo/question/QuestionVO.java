package cn.seecoder.courselearning.vo.question;


import cn.seecoder.courselearning.po.forum.Reply;
import cn.seecoder.courselearning.po.question.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class QuestionVO{
    private int questionId;
    private int courseId;
    private String description;
    private String answer;
    private String analysis;

    public QuestionVO(){}
    public QuestionVO(@NonNull Question question){
        this.questionId=question.getQuestionId();
        this.courseId = question.getCourseId();
        this.description = question.getDescription();
        this.answer = question.getAnswer();
        this.analysis = question.getAnalysis();
    }

    public QuestionVO(int questionId, int courseId, String description, String answer, String analysis) {
        this.questionId = questionId;
        this.courseId = courseId;
        this.description = description;
        this.answer = answer;
        this.analysis = analysis;
    }

    public QuestionVO(int courseId, String description, String answer, String analysis) {
        this.courseId = courseId;
        this.description = description;
        this.answer = answer;
        this.analysis = analysis;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
