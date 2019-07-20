package org.haveagroup.xes.Web.ResponseJson;

public class AnswerDataJson {
    private String examinationId;
    private String paperId;
    private String questionId;
    private String questionContent;
    private String userId;
    private String answer;

    public AnswerDataJson() {
    }

    public AnswerDataJson(String examinationId, String paperId, String questionId, String questionContent, String answer) {
        this.examinationId = examinationId;
        this.paperId = paperId;
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.answer = answer;
    }

    public AnswerDataJson(String examinationId, String paperId, String questionId, String questionContent, String userId, String answer) {
        this.examinationId = examinationId;
        this.paperId = paperId;
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.userId = userId;
        this.answer = answer;
    }

    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
