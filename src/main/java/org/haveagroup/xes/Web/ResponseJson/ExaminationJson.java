package org.haveagroup.xes.Web.ResponseJson;

public class ExaminationJson {
    StatusJson status;
    String examinationId;
    String examinationName;

    public ExaminationJson(StatusJson status, String examinationId, String examinationName) {
        this.status = status;
        this.examinationId = examinationId;
        this.examinationName = examinationName;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public String getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(String examinationId) {
        this.examinationId = examinationId;
    }

    public String getExaminationName() {
        return examinationName;
    }

    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName;
    }
}
