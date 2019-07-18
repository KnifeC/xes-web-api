package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.haveagroup.xes.Web.ResponseJson.ExaminationDataJson;
import org.haveagroup.xes.Web.ResponseJson.ExaminationJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    @PostMapping(value="webapi/createExamination")
    public ExaminationJson createExamination(String examinationName, HttpSession session){
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE).equals("teacher")){
            String examinationCreator = (String)session.getAttribute(SessionKey.USER_NAME);
            Examination examination = examinationService.createExamination(examinationName,examinationCreator);
            ExaminationDataJson examinationData = new ExaminationDataJson(examination.getExaminationId(),examination.getExaminationName(),examination.getExaminationCreator());
            examinationDataList.add(examinationData);
            return new ExaminationJson(new StatusJson(Status.SUCCESS,"创建考试","THIS"),examinationDataList);
        }else{
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有权限","THIS"),examinationDataList);
        }

    }

    @DeleteMapping(value="webapi/deleteExamination/{examinationId}")
    public boolean deleteExamination(@PathVariable("examinationId") String examinationId){
        examinationService.deleteExamination(examinationId);
        return true;
    }

    @GetMapping(value="webapi/searchOneExamination/{examinationId}")
    public ExaminationJson findOneByExaminationId(@PathVariable("examinationId") String examinationId){
        List<ExaminationDataJson> examinationByIdList = new ArrayList<>();
        Examination examination = examinationService.findOneByExaminationId(examinationId);
        if(examination==null){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有这门考试","THIS"),examinationByIdList);
        }else{
            ExaminationDataJson examinationData = new ExaminationDataJson(examination.getExaminationId(),examination.getExaminationName(),examination.getExaminationCreator());
            examinationByIdList.add(examinationData);
            return new ExaminationJson(new StatusJson(Status.SUCCESS,"成功找到考试","THIS"),examinationByIdList);
        }
    }

    @GetMapping(value="webapi/searchAllExamination/{examinationName}")
    public ExaminationJson findAllByExaminationName(@PathVariable("examinationName") String examinationName){
        List<Examination> allByExaminationName = examinationService.findAllByExaminationNameLike(examinationName);
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(allByExaminationName.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有符合关键字的考试","THIS"),examinationDataList);
        }else{
            for(Examination examination : allByExaminationName){
                ExaminationDataJson examinationDataJson = new ExaminationDataJson(examination.getExaminationId(),examination.getExaminationName(),examination.getExaminationCreator());
                examinationDataList.add(examinationDataJson);
            }
            return new ExaminationJson(new StatusJson(Status.SUCCESS,"显示符合关键字的考试","THIS"),examinationDataList);
        }
    }
}
