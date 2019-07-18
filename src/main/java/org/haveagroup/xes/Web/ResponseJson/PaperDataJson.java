package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public class PaperDataJson {
    String paperId;
    List<String> questionIdList;
    List<Question> questionList;

    public PaperDataJson(String paperId) {
        this.paperId = paperId;
    }

    public PaperDataJson(String paperId, List<String> questionIdList, List<Question> questionList) {
        this.paperId = paperId;
        this.questionIdList = questionIdList;
        this.questionList = questionList;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
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
