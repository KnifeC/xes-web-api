package org.haveagroup.xes.Web.ResponseJson;

public class Examination_User_Json {
    StatusJson status;
    String userId;
    String userName;
    String examinationId;
    String examinationName;


    public Examination_User_Json() {
    }

    public Examination_User_Json(StatusJson status, String userId, String userName, String examinationId, String examinationName) {
        this.status = status;
        this.userId = userId;
        this.userName = userName;
        this.examinationId = examinationId;
        this.examinationName = examinationName;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
