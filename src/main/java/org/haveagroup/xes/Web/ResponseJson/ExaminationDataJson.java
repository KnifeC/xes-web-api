package org.haveagroup.xes.Web.ResponseJson;

import java.util.Date;

public class ExaminationDataJson {
    private String examinationId;
    private String examinationName;
    private String creatorId;
    private String creatorName;
    private Date beginTime;
    private Date endTime;
    private String examinationStatus;

    public ExaminationDataJson() {
    }

    public ExaminationDataJson(String examinationId, String examinationName, String creatorId, String creatorName, Date beginTime, Date endTime, String examinationStatus) {
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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
