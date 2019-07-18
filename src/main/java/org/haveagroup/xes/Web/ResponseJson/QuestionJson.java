package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public class QuestionJson {
    StatusJson status;
    List<QuestionDataJson> questionDataList;


    public QuestionJson(){}

    public QuestionJson(StatusJson status, List<QuestionDataJson> questionDataList) {
        this.status = status;
        this.questionDataList = questionDataList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<QuestionDataJson> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionDataJson> questionDataList) {
        this.questionDataList = questionDataList;
    }
}
