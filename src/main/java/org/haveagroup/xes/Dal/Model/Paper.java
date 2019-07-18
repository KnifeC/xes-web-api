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
    private String paperName;
    private String examinationId;
    private String examinationnName;
    private double averageScoreOfPaper;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }

    public String getExaminationnName() {
        return examinationnName;
    }

    public void setExaminationnName(String examinationnName) {
        this.examinationnName = examinationnName;
    }
}
