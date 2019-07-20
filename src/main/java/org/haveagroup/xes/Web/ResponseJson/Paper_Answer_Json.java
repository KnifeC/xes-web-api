package org.haveagroup.xes.Web.ResponseJson;

import java.util.List;

public class Paper_Answer_Json {
    StatusJson status;
    List<PaperDataJson> paperDataList;
    List<AnswerDataJson> answerDataList;

    public Paper_Answer_Json() {
    }

    public Paper_Answer_Json(StatusJson status, List<PaperDataJson> paperDataList, List<AnswerDataJson> answerDataList) {
        this.status = status;
        this.paperDataList = paperDataList;
        this.answerDataList = answerDataList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<PaperDataJson> getPaperDataList() {
        return paperDataList;
    }

    public void setPaperDataList(List<PaperDataJson> paperDataList) {
        this.paperDataList = paperDataList;
    }

    public List<AnswerDataJson> getAnswerDataList() {
        return answerDataList;
    }

    public void setAnswerDataList(List<AnswerDataJson> answerDataList) {
        this.answerDataList = answerDataList;
    }
}
