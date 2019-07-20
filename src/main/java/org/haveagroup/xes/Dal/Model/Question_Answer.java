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
    private  String question_answer_id;
    private String questionId;
    private String answerId;
    private String userId;
    private String paperId;
}
