package org.haveagroup.xes.Web.Forms;

import java.util.Date;

public class EditExaminationForm {
    private String examinationName;
    private Date beginDate;
    private Date endDate;

    public String getExaminationName() {
        return examinationName;
    }

    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
