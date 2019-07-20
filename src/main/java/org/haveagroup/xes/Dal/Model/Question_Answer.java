package org.haveagroup.xes.Dal.Model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="question_answer")
public class Question_Answer {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String question_answer_id;
    private String examinationId;
    private String paperId;
    private String questionId;
    private String answer;
    private String userId;


    public Question_Answer() {
    }

    public Question_Answer(String examinationId, String questionId, String userId) {
        this.examinationId = examinationId;
        this.questionId = questionId;
        this.userId = userId;
    }

    public Question_Answer(String examinationId, String paperId, String questionId, String userId) {
        this.examinationId = examinationId;
        this.paperId = paperId;
        this.questionId = questionId;
        this.userId = userId;
    }

    public String getQuestion_answer_id() {
        return question_answer_id;
    }

    public void setQuestion_answer_id(String question_answer_id) {
        this.question_answer_id = question_answer_id;
    }

    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
