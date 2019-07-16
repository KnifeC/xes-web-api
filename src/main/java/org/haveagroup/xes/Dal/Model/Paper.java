package org.haveagroup.xes.Dal.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="paper")
public class Paper {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String paperId;
    private String examinationId;
    private double averageScoreOfPaper;

}