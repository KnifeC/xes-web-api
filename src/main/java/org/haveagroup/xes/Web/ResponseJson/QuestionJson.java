package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public class QuestionJson {
    StatusJson status;

    List<String> questionIdList;
    List<Question> questionList;
    List<String> questionContentList;


    public QuestionJson(StatusJson status, List<String> questionIdList, List<Question> questionList) {
        this.status = status;
        this.questionIdList = questionIdList;
        this.questionList = questionList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<String> getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(List<String> questionIdList) {
        this.questionIdList = questionIdList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<String> getQuestionContentList() {
        return questionContentList;
    }

    public void setQuestionContentList(List<String> questionContentList) {
        this.questionContentList = questionContentList;
    }
}
