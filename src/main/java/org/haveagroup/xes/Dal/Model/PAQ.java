package org.haveagroup.xes.Dal.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="paq")
public class PAQ {
    private String paperId;
    private String questionId;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "PAQ{" +
                "paperId='" + paperId + '\'' +
                ", questionId='" + questionId + '\'' +
                '}';
    }
}
