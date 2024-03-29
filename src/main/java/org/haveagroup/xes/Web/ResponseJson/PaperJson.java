package org.haveagroup.xes.Web.ResponseJson;

import java.util.List;

public class PaperJson {
    StatusJson status;
    List<PaperDataJson> paperDataList;

    public PaperJson() {
    }

    public PaperJson(StatusJson status, List<PaperDataJson> paperDataList) {
        this.status = status;
        this.paperDataList = paperDataList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<PaperDataJson> getPaperDataList() {
        return paperDataList;
    }

    public void setPaperDataList(List<PaperDataJson> paperDataList) {
        this.paperDataList = paperDataList;
    }
}
