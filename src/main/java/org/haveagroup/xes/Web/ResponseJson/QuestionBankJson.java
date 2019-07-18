package org.haveagroup.xes.Web.ResponseJson;


import java.util.List;

public class QuestionBankJson {
    StatusJson status;
    List<QuestionBankDataJson> questionBankData;


    public StatusJson getStatus() {
        return status;
    }

    public QuestionBankJson(StatusJson status, List<QuestionBankDataJson> questionBankData) {
        this.status = status;
        this.questionBankData = questionBankData;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<QuestionBankDataJson> getQuestionBankData() {
        return questionBankData;
    }

    public void setQuestionBankData(List<QuestionBankDataJson> questionBankData) {
        this.questionBankData = questionBankData;
    }
}
