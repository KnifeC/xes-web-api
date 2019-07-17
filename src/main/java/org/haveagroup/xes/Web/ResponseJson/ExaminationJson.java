package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.Examination;

import java.util.List;

public class ExaminationJson {
    StatusJson status;

    List<String> examinationIdList;
    List<Examination> examinationList;

    public ExaminationJson(StatusJson status, List<String> examinationIdList, List<Examination> examinationList) {
        this.status = status;
        this.examinationIdList = examinationIdList;
        this.examinationList = examinationList;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<String> getExaminationIdList() {
        return examinationIdList;
    }

    public void setExaminationIdList(List<String> examinationIdList) {
        this.examinationIdList = examinationIdList;
    }

    public List<Examination> getExaminationList() {
        return examinationList;
    }

    public void setExaminationList(List<Examination> examinationList) {
        this.examinationList = examinationList;
    }
}
