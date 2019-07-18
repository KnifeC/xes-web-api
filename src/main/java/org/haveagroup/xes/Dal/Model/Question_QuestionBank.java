package org.haveagroup.xes.Dal.Model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "question_question_bank")
public class Question_QuestionBank {
    @Id
    @Field("question_questionBank_id")
    private String question_questionBank_id;
    @Field("questionId")
    private String questionId;
    @Field("questionBankId")
    private String questionBankId;

    public String getQuestion_questionBank_id() {
        return question_questionBank_id;
    }

    public void setQuestion_questionBank_id(String question_questionBank_id) {
        this.question_questionBank_id = question_questionBank_id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(String questionBankId) {
        this.questionBankId = questionBankId;
    }
}
