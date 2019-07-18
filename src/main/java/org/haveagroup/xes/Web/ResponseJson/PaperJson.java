package org.haveagroup.xes.Web.ResponseJson;

public class PaperJson {
    StatusJson status;
    PaperDataJson paperData;
    String paperId;

    public PaperJson(StatusJson status, String paperId) {
        this.status = status;
        this.paperId = paperId;
    }

    public PaperJson(StatusJson status, PaperDataJson paperData) {
        this.status = status;
        this.paperData = paperData;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public PaperDataJson getPaperData() {
        return paperData;
    }

    public void setPaperData(PaperDataJson paperData) {
        this.paperData = paperData;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
