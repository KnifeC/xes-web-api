package org.haveagroup.xes.Dal.Model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="examination_user")
public class Examination_User {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String examination_user_id;
    private String examinationId;
    private String userId;
    private String status;

    public String getExamination_user_id() {
        return examination_user_id;
    }

    public void setExamination_user_id(String examination_user_id) {
        this.examination_user_id = examination_user_id;
    }

    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
