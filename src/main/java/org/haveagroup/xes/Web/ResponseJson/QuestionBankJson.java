package org.haveagroup.xes.Web.ResponseJson;


public class QuestionBankJson {
    StatusJson status;
    QuestionBankDataJson questionBankData;
    String questionBankId;

    public QuestionBankJson(StatusJson status, QuestionBankDataJson questionBankData) {
        this.status = status;
        this.questionBankData = questionBankData;
    }

    public QuestionBankJson(StatusJson status, String questionBankId) {
        this.status = status;
        this.questionBankId = questionBankId;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public QuestionBankDataJson getQuestionBankData() {
        return questionBankData;
    }

    public void setQuestionBankData(QuestionBankDataJson questionBankData) {
        this.questionBankData = questionBankData;
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId;
    }
}
