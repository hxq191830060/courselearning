package cn.seecoder.courselearning.po.question;


import cn.seecoder.courselearning.vo.question.QuestionVO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private int questionId;
    private int courseId;
    private String description;
    private String answer;
    private String analysis;

    public Question(){}
    public Question(@NonNull QuestionVO questionVO){
        this.questionId=questionVO.getQuestionId();
        this.courseId = questionVO.getCourseId();
        this.description = questionVO.getDescription();
        this.analysis = questionVO.getAnalysis();
        this.answer = questionVO.getAnswer();
    }


    public Question(int questionId, int courseId, String description, String answer, String analysis) {
        this.questionId = questionId;
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
