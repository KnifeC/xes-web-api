package org.haveagroup.xes.Dal.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="examination")
public class Examination {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String examinationId;
    private String numberOfPapers;
    private double avgOfExamination;;
}
