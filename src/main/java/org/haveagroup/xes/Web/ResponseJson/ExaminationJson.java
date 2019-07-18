package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.Examination;

import java.util.List;

public class ExaminationJson {
    StatusJson status;
    List<ExaminationDataJson> examinationData;

    public ExaminationJson() {
    }

    public ExaminationJson(StatusJson status, List<ExaminationDataJson> examinationData) {
        this.status = status;
        this.examinationData = examinationData;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public List<ExaminationDataJson> getExaminationData() {
        return examinationData;
    }

    public void setExaminationData(List<ExaminationDataJson> examinationData) {
        this.examinationData = examinationData;
    }
}
