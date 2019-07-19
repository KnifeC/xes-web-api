package org.haveagroup.xes.Dal.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="paper_question")
public class Paper_Question {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String paper_question_Id;

    private String paperId;
    private String questionId;

    public String getPaper_question_Id() {
        return paper_question_Id;
    }

    public void setPaper_question_Id(String paper_question_Id) {
        this.paper_question_Id = paper_question_Id;
    }

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
}
