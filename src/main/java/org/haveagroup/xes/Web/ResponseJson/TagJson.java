package org.haveagroup.xes.Web.ResponseJson;

import java.util.List;

public class TagJson {
    StatusJson status;
    List<TagDataJson> tagDataList;

    public TagJson() {
    }

    public TagJson(StatusJson status, List<TagDataJson> tagDataList) {
        this.status = status;
        this.tagDataList = tagDataList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<TagDataJson> getTagDataList() {
        return tagDataList;
    }

    public void setTagDataList(List<TagDataJson> tagDataList) {
        this.tagDataList = tagDataList;
    }
}
