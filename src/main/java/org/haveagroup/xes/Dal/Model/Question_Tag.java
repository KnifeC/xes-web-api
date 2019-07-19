package org.haveagroup.xes.Dal.Model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="question_tag")
public class Question_Tag {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String question_tag_id;

    private String questionId;
    private String tagId;

    public String getQuestion_tag_id() {
        return question_tag_id;
    }

    public void setQuestion_tag_id(String question_tag_id) {
        this.question_tag_id = question_tag_id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
