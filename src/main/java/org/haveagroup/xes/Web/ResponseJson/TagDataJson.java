package org.haveagroup.xes.Web.ResponseJson;

public class TagDataJson {
    String tagId;
    String tagName;

    public TagDataJson() {
    }

    public TagDataJson(String tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
