package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public class QuestionBankDataJson {
    String questionBankId;
    List<String> questionIdList;
    List<Question> questionList;

    public QuestionBankDataJson(String questionBankId) {
        this.questionBankId = questionBankId;
    }

    public QuestionBankDataJson(String questionBankId, List<String> questionIdList, List<Question> questionList) {
        this.questionBankId = questionBankId;
        this.questionIdList = questionIdList;
        this.questionList = questionList;
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId;
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
}
