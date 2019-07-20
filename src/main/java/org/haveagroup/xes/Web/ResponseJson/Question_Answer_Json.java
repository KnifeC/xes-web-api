package org.haveagroup.xes.Web.ResponseJson;

import java.util.List;

public class Question_Answer_Json {
    StatusJson status;
    List<AnswerDataJson> answerList;

    public Question_Answer_Json() {
    }

    public Question_Answer_Json(StatusJson status, List<AnswerDataJson> answerList) {
        this.status = status;
        this.answerList = answerList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<AnswerDataJson> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerDataJson> answerList) {
        this.answerList = answerList;
    }
}
