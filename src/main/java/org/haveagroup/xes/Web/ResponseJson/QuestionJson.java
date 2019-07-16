package org.haveagroup.xes.Web.ResponseJson;

public class QuestionJson {
    StatusJson status;
    String questionId;
    String questionContent;
    String questionAnswer;


    public QuestionJson(StatusJson status, String questionId, String questionContent) {
        this.status = status;
        this.questionId = questionId;
        this.questionContent = questionContent;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
