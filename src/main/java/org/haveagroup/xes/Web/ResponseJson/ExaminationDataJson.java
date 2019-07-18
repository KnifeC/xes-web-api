package org.haveagroup.xes.Web.ResponseJson;

public class ExaminationDataJson {
    private String examinationId;
    private String examinationName;
    private String creatorId;
    private String creatorName;

    public ExaminationDataJson() {
    }

    public ExaminationDataJson(String examinationId, String examinationName, String creatorId) {
        this.examinationId = examinationId;
        this.examinationName = examinationName;
        this.creatorId = creatorId;
    }

    public ExaminationDataJson(String examinationId, String examinationName, String creatorId, String creatorName) {
        this.examinationId = examinationId;
        this.examinationName = examinationName;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
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
}
