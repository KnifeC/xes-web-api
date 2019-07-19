package org.haveagroup.xes.Web.ResponseJson;

public class QuestionDataJson {
    String questionId;
    String questionContent;

    String questionAnswer;
    String questionType;
    String uploaderId;
    String uploaderName;


    public QuestionDataJson() {
    }

    public QuestionDataJson(String questionContent, String questionType) {
        this.questionContent = questionContent;
        this.questionType = questionType;
    }

    public QuestionDataJson(String questionId, String questionContent, String uploaderId, String uploaderName) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
    }

    public QuestionDataJson(String questionId, String questionContent, String questionAnswer, String questionType, String uploaderId, String uploaderName) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.questionAnswer = questionAnswer;
        this.questionType = questionType;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
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

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }
}
