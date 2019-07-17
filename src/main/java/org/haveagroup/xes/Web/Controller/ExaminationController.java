package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.haveagroup.xes.Web.ResponseJson.ExaminationJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    @PostMapping(value="webapi/createExamination")
    public ExaminationJson createExamination(String examinationName){
        Examination examination = examinationService.createExamination(examinationName);
        List<String> oneExaminationId = new ArrayList<>();
        List<Examination> oneExamination = new ArrayList<>();
        oneExaminationId.add(examination.getExaminationId());
        oneExamination.add(examination);
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"创建考试","THIS"),oneExaminationId,oneExamination);
    }

    @DeleteMapping(value="webapi/deleteExamination/{examinationId}")
    public boolean deleteExamination(@PathVariable("examinationId") String examinationId){
        examinationService.deleteExamination(examinationId);
        return true;
    }

    @GetMapping(value="webapi/searchOneExamination/{examinationId}")
    public ExaminationJson findOneByExaminationId(@PathVariable("examinationId") String examinationId){
        Examination examination = examinationService.findOneByExaminationId(examinationId);
        List<String> oneExaminationId = new ArrayList<>();
        List<Examination> oneExamination = new ArrayList<>();
        oneExaminationId.add(examination.getExaminationId());
        oneExamination.add(examination);
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"创建考试","THIS"),oneExaminationId,oneExamination);
    }

    @GetMapping(value="webapi/searchAllExamination/{examinationName}")
    public ExaminationJson findAllByExaminationName(@PathVariable("examinationName") String examinationName){
        List<String> allIdByExaminationName = new ArrayList<>();
        List<Examination> allByExaminationName = examinationService.findAllByExaminationNameLike(examinationName);
        for(Examination q : allByExaminationName){
            allIdByExaminationName.add(q.getExaminationId());
        }
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"显示符合关键字的试题","THIS"),allIdByExaminationName,allByExaminationName);
    }
}
