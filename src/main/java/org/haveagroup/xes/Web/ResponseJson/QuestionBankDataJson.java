package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.Question;


public class QuestionBankDataJson {
    private String questionBankId;
    private String questionBankName;
    private String ownerId;
    private String ownerName;
    private String visibility;

    public QuestionBankDataJson(String questionBankId, String questionBankName, String ownerId, String ownerName) {
        this.questionBankId = questionBankId;
        this.questionBankName = questionBankName;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public QuestionBankDataJson(String questionBankId, String questionBankName, String ownerId, String ownerName, String visibility) {
        this.questionBankId = questionBankId;
        this.questionBankName = questionBankName;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.visibility = visibility;
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId;
    }

    public String getQuestionBankName() {
        return questionBankName;
    }

    public void setQuestionBankName(String questionBankName) {
        this.questionBankName = questionBankName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
