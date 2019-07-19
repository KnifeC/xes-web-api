package org.haveagroup.xes.Web.ResponseJson;

import java.util.List;

public class QuestionBank_Question_Json {
    StatusJson status;
    List<QuestionBankDataJson> questionBankDataList;
    List<QuestionDataJson> questionDataList;

    public QuestionBank_Question_Json() {
    }

    public QuestionBank_Question_Json(StatusJson status, List<QuestionBankDataJson> questionBankDataList, List<QuestionDataJson> questionDataList) {
        this.status = status;
        this.questionBankDataList = questionBankDataList;
        this.questionDataList = questionDataList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<QuestionBankDataJson> getQuestionBankDataList() {
        return questionBankDataList;
    }

    public void setQuestionBankDataList(List<QuestionBankDataJson> questionBankDataList) {
        this.questionBankDataList = questionBankDataList;
    }

    public List<QuestionDataJson> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionDataJson> questionDataList) {
        this.questionDataList = questionDataList;
    }
}
