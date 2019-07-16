package org.haveagroup.xes.Web.ResponseJson;

public class PaperJson {
    StatusJson status;
    String paperId;

    public PaperJson(StatusJson status, String paperId) {
        this.status = status;
        this.paperId = paperId;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}
