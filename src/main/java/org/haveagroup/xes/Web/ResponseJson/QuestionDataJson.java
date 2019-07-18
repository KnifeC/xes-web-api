package org.haveagroup.xes.Web.ResponseJson;

public class QuestionDataJson {
    String questionId;
    String questionContent;

    public QuestionDataJson() {
    }

    public QuestionDataJson(String questionId, String questionContent) {
        this.questionId = questionId;
        this.questionContent = questionContent;
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
}
