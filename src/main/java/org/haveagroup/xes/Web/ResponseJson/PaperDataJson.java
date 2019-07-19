package org.haveagroup.xes.Web.ResponseJson;

public class PaperDataJson {
    private String paperId;
    private String paperName;
    private String ExaminationId;
    private String ExaminationName;

    public PaperDataJson() {
    }

    public PaperDataJson(String paperId, String paperName, String examinationId, String examinationName) {
        this.paperId = paperId;
        this.paperName = paperName;
        ExaminationId = examinationId;
        ExaminationName = examinationName;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getExaminationId() {
        return ExaminationId;
    }

    public void setExaminationId(String examinationId) {
        ExaminationId = examinationId;
    }

    public String getExaminationName() {
        return ExaminationName;
    }

    public void setExaminationName(String examinationName) {
        ExaminationName = examinationName;
    }
}
