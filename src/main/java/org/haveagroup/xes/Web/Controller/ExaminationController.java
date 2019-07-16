package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.haveagroup.xes.Web.ResponseJson.ExaminationJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    @PostMapping(value="webapi/createExamination")
    public ExaminationJson createExamination(String examinationName){
        Examination examination = examinationService.createExamination(examinationName);
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"创建考试","THIS"),examination.getExaminationId(),examinationName);
    }
}
