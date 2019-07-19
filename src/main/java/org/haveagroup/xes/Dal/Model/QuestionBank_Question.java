package org.haveagroup.xes.Dal.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="questionBank_question")
public class QuestionBank_Question {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    String questionBank_question_id;
    String questionBankId;
    String questionId;

    public String getQuestionBank_question_id() {
        return questionBank_question_id;
    }

    public void setQuestionBank_question_id(String questionBank_question_id) {
        this.questionBank_question_id = questionBank_question_id;
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
