package org.haveagroup.xes.Web.ResponseJson;

import java.util.Date;

public class ExaminationDataJson {
    private String examinationId;
    private String examinationName;
    private String creatorId;
    private String creatorName;
    private String beginTime;
    private String endTime;
    private String examinationStatus;

    public ExaminationDataJson() {
    }

    public ExaminationDataJson(String examinationId, String examinationName, String creatorId, String creatorName, String beginTime, String endTime, String examinationStatus) {
        this.examinationId = examinationId;
        this.examinationName = examinationName;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.examinationStatus = examinationStatus;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExaminationStatus() {
        return examinationStatus;
    }

    public void setExaminationStatus(String examinationStatus) {
        this.examinationStatus = examinationStatus;
    }
}
