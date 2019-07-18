package org.haveagroup.xes.Web.ResponseJson;

public class ExaminationDataJson {
    private String examinationId;
    private String examinationName;
    private String examinationCreator;

    public ExaminationDataJson() {
    }

    public ExaminationDataJson(String examinationId, String examinationName, String examinationCreator) {
        this.examinationId = examinationId;
        this.examinationName = examinationName;
        this.examinationCreator = examinationCreator;
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

    public String getExaminationCreator() {
        return examinationCreator;
    }

    public void setExaminationCreator(String examinationCreator) {
        this.examinationCreator = examinationCreator;
    }
}
