package org.haveagroup.xes.Web.ResponseJson;

public class QuestionDataJson {
    String questionId;
    String questionContent;

    String questionAnswer;
    String questionType;
    String questionUploader;


    public QuestionDataJson() {
    }

    public QuestionDataJson(String questionId, String questionContent) {
        this.questionId = questionId;
        this.questionContent = questionContent;
    }

    public QuestionDataJson(String questionId, String questionContent, String questionAnswer, String questionType, String questionUploader) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.questionAnswer = questionAnswer;
        this.questionType = questionType;
        this.questionUploader = questionUploader;
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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionUploader() {
        return questionUploader;
    }

    public void setQuestionUploader(String questionUploader) {
        this.questionUploader = questionUploader;
    }
}
