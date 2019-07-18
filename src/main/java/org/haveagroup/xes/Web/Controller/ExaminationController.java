package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.haveagroup.xes.Service.UserService;
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
    @Autowired
    UserService userService;


    @GetMapping(value="webapi/searchByCreatorId")
    public ExaminationJson findAllByCreator(String creatorId){
        List<Examination> allByCreator = examinationService.findAllByCreator(creatorId);
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(allByCreator.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有符合关键字的考试","THIS"),examinationDataList);
        }else{
            for(Examination examination : allByCreator){
                User creator = userService.findByUserId(examination.getCreatorId());
                ExaminationDataJson examinationDataJson = new ExaminationDataJson(examination.getExaminationId(),
                        examination.getExaminationName(),examination.getCreatorId(),creator.getUsername());
                examinationDataList.add(examinationDataJson);
            }
            return new ExaminationJson(new StatusJson(Status.SUCCESS,"显示符合关键字的考试","THIS"),examinationDataList);
        }
    }

    @PostMapping(value="webapi/createExamination")
    public ExaminationJson createExamination(String examinationName, HttpSession session){
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new ExaminationJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),examinationDataList);
        }
        if(!session.getAttribute(SessionKey.USER_TYPE).equals("teacher")){
            return new ExaminationJson(new StatusJson(Status.ERROR,"没有权限","THIS"),examinationDataList);
        }
        String creatorId = (String)session.getAttribute(SessionKey.USER_ID);
        String creatorName = (String)session.getAttribute(SessionKey.USER_NAME);
        Examination examination = examinationService.createExamination(examinationName,creatorId);
        ExaminationDataJson examinationData = new ExaminationDataJson(examination.getExaminationId(),
                examination.getExaminationName(),creatorId,creatorName);
        examinationDataList.add(examinationData);
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"创建考试","THIS"),examinationDataList);
    }

    @DeleteMapping(value="webapi/deleteExamination/{examinationId}")
    public boolean deleteExamination(@PathVariable("examinationId") String examinationId){
        examinationService.deleteExamination(examinationId);
        return true;
    }

    @GetMapping(value="webapi/examination/{examinationId}")
    public ExaminationJson findOneByExaminationId(@PathVariable("examinationId") String examinationId){
        List<ExaminationDataJson> examinationByIdList = new ArrayList<>();
        Examination examination = examinationService.findOneByExaminationId(examinationId);
        if(examination==null){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有这门考试","THIS"),examinationByIdList);
        }else{
            User creator = userService.findByUserId(examination.getCreatorId());
            ExaminationDataJson examinationData = new ExaminationDataJson(examination.getExaminationId(),examination.getExaminationName(),
                    examination.getCreatorId(),creator.getUsername());
            examinationByIdList.add(examinationData);
            return new ExaminationJson(new StatusJson(Status.SUCCESS,"成功找到考试","THIS"),examinationByIdList);
        }
    }

    @GetMapping(value="webapi/searchExamination/{examinationName}")
    public ExaminationJson findAllByExaminationName(@PathVariable("examinationName") String examinationName){
        List<Examination> allByExaminationName = examinationService.findAllByExaminationNameLike(examinationName);
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(allByExaminationName.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有符合关键字的考试","THIS"),examinationDataList);
        }else{
            for(Examination examination : allByExaminationName){
                User creator = userService.findByUserId(examination.getCreatorId());
                ExaminationDataJson examinationDataJson = new ExaminationDataJson(examination.getExaminationId(),
                        examination.getExaminationName(),examination.getCreatorId(),creator.getUsername());
                examinationDataList.add(examinationDataJson);
            }
            return new ExaminationJson(new StatusJson(Status.SUCCESS,"显示符合关键字的考试","THIS"),examinationDataList);
        }
    }
}
