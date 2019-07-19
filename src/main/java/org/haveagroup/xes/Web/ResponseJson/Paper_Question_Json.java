package org.haveagroup.xes.Web.ResponseJson;

import java.util.List;

public class Paper_Question_Json {
    StatusJson status;
    List<PaperDataJson> paperDataList;
    List<QuestionDataJson> questionDataList;

    public Paper_Question_Json() {
    }

    public Paper_Question_Json(StatusJson status, List<PaperDataJson> paperDataList, List<QuestionDataJson> questionDataList) {
        this.status = status;
        this.paperDataList = paperDataList;
        this.questionDataList = questionDataList;
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

    public List<QuestionDataJson> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionDataJson> questionDataList) {
        this.questionDataList = questionDataList;
    }
}
