package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.haveagroup.xes.Service.Interfaces.Examination_User_Service;
import org.haveagroup.xes.Service.Interfaces.UserService;
import org.haveagroup.xes.Util.StringUtil;
import org.haveagroup.xes.Web.Forms.EditExaminationForm;
import org.haveagroup.xes.Web.ResponseJson.*;
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
    @Autowired
    Examination_User_Service examination_user_service;


    @GetMapping(value="webapi/examinationByCreator/{creatorId}")
    public ExaminationJson findAllByCreator(@PathVariable("creatorId") String creatorId){
        List<Examination> allByCreator = examinationService.findAllByCreator(creatorId);
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(allByCreator.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"该用户没有发布考试","THIS"),examinationDataList);
        }else{
            for(Examination examination : allByCreator){
                User creator = userService.findByUserId(examination.getCreatorId());
                ExaminationDataJson examinationDataJson = new ExaminationDataJson(examination.getExaminationId(),
                        examination.getExaminationName(),examination.getCreatorId(),creator.getUsername(),
                        examination.getBeginTime(),examination.getEndTime(),
                        examination.getExaminationStatus(),examination.getDurationTime());
                examinationDataList.add(examinationDataJson);
            }
            return new ExaminationJson(new StatusJson(Status.SUCCESS,"该用户发布的考试","THIS"),examinationDataList);
        }
    }

    @PostMapping(value="webapi/createExamination")
    public ExaminationJson createExamination(String examinationName, String beginTime,String endTime,String durationTime, HttpSession session){
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new ExaminationJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),examinationDataList);
        }
        if(!session.getAttribute(SessionKey.USER_TYPE).equals("teacher")){
            return new ExaminationJson(new StatusJson(Status.ERROR,"没有权限","THIS"),examinationDataList);
        }
        String creatorId = (String)session.getAttribute(SessionKey.USER_ID);
        String creatorName = (String)session.getAttribute(SessionKey.USER_NAME);
        Examination examination = examinationService.createExamination(examinationName,creatorId,beginTime,endTime,durationTime);

        ExaminationDataJson examinationData = new ExaminationDataJson(examination.getExaminationId(),
                examination.getExaminationName(),creatorId,creatorName,beginTime,endTime,
                examination.getExaminationStatus(),examination.getDurationTime());
        examinationDataList.add(examinationData);
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"创建考试","THIS"),examinationDataList);
    }

    @DeleteMapping(value="webapi/deleteExamination/{examinationId}")
    public boolean deleteExamination(@PathVariable("examinationId") String examinationId){
        examinationService.deleteExamination(examinationId);
        return true;
    }

    @PostMapping(value="webapi/editExaminationInfo")
    public StatusJson editExaminationInfo(String examinationId, EditExaminationForm editExaminationForm,HttpSession session){
        if(!StringUtil.isEquals((String)session.getAttribute(SessionKey.USER_ID),examinationService.findOneByExaminationId(examinationId).getCreatorId())){
            return new StatusJson(Status.ERROR,"你不是创建者","THIS");
        }
        if(!examinationService.editExaminationName(examinationId,editExaminationForm.getExaminationName())){
            return new StatusJson(Status.ERROR,"修改名称失败","THIS");
        }
        if(!examinationService.editExaminationBeginTime(examinationId,editExaminationForm.getBeginTime())){
            return new StatusJson(Status.ERROR,"修改开始时间失败","THIS");
        }
        if(!examinationService.editExaminationEndTime(examinationId,editExaminationForm.getEndTime())){
            return new StatusJson(Status.ERROR,"修改结束时间失败","THIS");
        }
        if(!examinationService.editExaminationDurationTime(examinationId,editExaminationForm.getDurationTime())){
            return new StatusJson(Status.ERROR,"修改持续时间失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"修改信息成功","THIS");
    }

    @GetMapping(value="webapi/searchExamination/byId/{examinationId}")
    public ExaminationJson findOneByExaminationId(@PathVariable("examinationId") String examinationId,HttpSession session){
        List<ExaminationDataJson> examinationByIdList = new ArrayList<>();
        Examination examination = examinationService.findOneByExaminationId(examinationId);
        String userId = (String)session.getAttribute(SessionKey.USER_ID);
        if(examination==null){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有这门考试","THIS"),examinationByIdList);
        }
        if(examination_user_service.findByUserIdAndExaminationId(userId,examinationId)==null
        &&!StringUtil.isEquals(examinationService.findOneByExaminationId(examinationId).getCreatorId(),(String)session.getAttribute(SessionKey.USER_ID))){
            return new ExaminationJson(new StatusJson(Status.WARNING,"你没有参加这门考试","THIS"),examinationByIdList);
        }
        User creator = userService.findByUserId(examination.getCreatorId());
        ExaminationDataJson examinationData = new ExaminationDataJson(examination.getExaminationId(),examination.getExaminationName(),
                examination.getCreatorId(),creator.getUsername(),examination.getBeginTime(),examination.getEndTime(),
                examination.getExaminationStatus(),examination.getDurationTime());
        examinationByIdList.add(examinationData);
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"成功找到考试","THIS"),examinationByIdList);

    }

    @GetMapping(value="webapi/searchExamination/byName/{examinationName}")
    public ExaminationJson findAllByExaminationName(@PathVariable("examinationName") String examinationName,HttpSession session){
        List<Examination> allByExaminationName = examinationService.findAllByExaminationNameLike(examinationName);
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new ExaminationJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),examinationDataList);
        }
        String userId = (String)session.getAttribute(SessionKey.USER_ID);
        if(allByExaminationName.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有符合关键字的考试","THIS"),examinationDataList);
        }
        for(Examination examination : allByExaminationName){
            if(examination_user_service.findByUserIdAndExaminationId(userId,examination.getExaminationId())==null){
                continue;
            }
            User creator = userService.findByUserId(examination.getCreatorId());
            ExaminationDataJson examinationDataJson = new ExaminationDataJson(examination.getExaminationId(),
                    examination.getExaminationName(),examination.getCreatorId(),creator.getUsername(),
                    examination.getBeginTime(),examination.getEndTime(),
                    examination.getExaminationStatus(),examination.getDurationTime());
            examinationDataList.add(examinationDataJson);
        }
        if(examinationDataList.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有符合关键字的考试","THIS"),examinationDataList);
        }
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"显示符合关键字的考试","THIS"),examinationDataList);
    }

    @GetMapping(value="webapi/searchExamination/byUserId/{userId}")
    public ExaminationJson findAllExaminationByUserId(@PathVariable("userId") String userId,HttpSession session){
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new ExaminationJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),examinationDataList);
        }
        List<Examination> allExaminationByUserId = examination_user_service.findAllExaminationByUserId(userId);
        if(allExaminationByUserId.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有该用户参与的考试","THIS"),examinationDataList);
        }
        for(Examination examination : allExaminationByUserId){
            User creator = userService.findByUserId(examination.getCreatorId());
            ExaminationDataJson examinationDataJson = new ExaminationDataJson(examination.getExaminationId(),
                    examination.getExaminationName(),examination.getCreatorId(),creator.getUsername(),
                    examination.getBeginTime(),examination.getEndTime(),
                    examination.getExaminationStatus(),examination.getDurationTime());
            examinationDataList.add(examinationDataJson);
        }
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"显示该用户参与的考试","THIS"),examinationDataList);
    }

    @GetMapping(value="webapi/searchExamination/byActiveUser")
    public ExaminationJson findAllByUserId(HttpSession session){
        List<ExaminationDataJson> examinationDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new ExaminationJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),examinationDataList);
        }
        String userId = (String)session.getAttribute(SessionKey.USER_ID);
        List<Examination> allExaminationByUserId = examination_user_service.findAllExaminationByUserId(userId);
        if(allExaminationByUserId.size()==0){
            return new ExaminationJson(new StatusJson(Status.WARNING,"没有该用户参与的考试","THIS"),examinationDataList);
        }
        for(Examination examination : allExaminationByUserId){
            User creator = userService.findByUserId(examination.getCreatorId());
            ExaminationDataJson examinationDataJson = new ExaminationDataJson(examination.getExaminationId(),
                    examination.getExaminationName(), examination.getCreatorId(), creator.getUsername(),
                    examination.getBeginTime(),examination.getEndTime(),
                    examination.getExaminationStatus(),examination.getDurationTime());
            examinationDataList.add(examinationDataJson);
        }
        return new ExaminationJson(new StatusJson(Status.SUCCESS,"显示该用户参与的考试","THIS"),examinationDataList);
    }

    @PostMapping(value="webapi/addUserToExamination")
    public Examination_User_Json addUserToExamination(String userId,String examinationId){
        examination_user_service.addUserToExamination(userId,examinationId);
        return new Examination_User_Json(new StatusJson(Status.SUCCESS,"添加用户至考试成功","THIS"),
                userId,userService.findByUserId(userId).getUsername(),
                examinationId,examinationService.findOneByExaminationId(examinationId).getExaminationName());
    }
}
