package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Dal.Model.QuestionBank_Question;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.Interfaces.QuestionBankService;
import org.haveagroup.xes.Service.Interfaces.QuestionBank_Question_Service;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Service.Interfaces.UserService;
import org.haveagroup.xes.Util.StringUtil;
import org.haveagroup.xes.Web.ResponseJson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
public class QuestionBankController {
    @Autowired
    QuestionBank_Question_Service questionBank_question_service;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionBankService questionBankService;
    @Autowired
    UserService userService;

    @PostMapping(value="webapi/createQuestionBank")
    public QuestionBankJson createQuestionBank(String questionBankName,String visibility,HttpSession session){
        List<QuestionBankDataJson> questionBankDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new QuestionBankJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),questionBankDataList);
        }
        String ownerId = (String)session.getAttribute(SessionKey.USER_ID);
        String ownerName = (String)session.getAttribute(SessionKey.USER_NAME);
        QuestionBank questionBank = questionBankService.createQuestionBank(questionBankName,ownerId,visibility);
        QuestionBankDataJson questionBankData = new QuestionBankDataJson(questionBank.getQuestionBankId(),
                questionBank.getQuestionBankName(),ownerId,ownerName,visibility);
        questionBankDataList.add(questionBankData);
        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"创建题库","THIS"),questionBankDataList);
    }

    @DeleteMapping(value="webapi/deleteQuestionBank")
    public StatusJson deleteQuestionBank(String questionBankId,HttpSession session){
        String ownerId = questionBankService.findOneByQuestionBankId(questionBankId).getOwnerId();
        if(!StringUtil.isEquals((String)session.getAttribute(SessionKey.USER_ID),ownerId)){
            return new StatusJson(Status.ERROR,"用户信息不正确","THIS");
        }
        if(questionBankService.deleteQuestionBank(questionBankId,ownerId)){
            return new StatusJson(Status.ERROR,"删除失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"删除成功","THIS");
    }

    @GetMapping(value="webapi/questionBank/{questionBankId}")
    public QuestionBankJson findOneByQuestionBankId(@PathVariable("questionBankId") String questionBankId,HttpSession session){
        List<QuestionBankDataJson> questionBankByIdList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new QuestionBankJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),questionBankByIdList);
        }
        String userId = (String)session.getAttribute(SessionKey.USER_ID);
        QuestionBank questionBank = questionBankService.findOneByQuestionBankId(questionBankId);
        if(questionBank==null){
            return new QuestionBankJson(new StatusJson(Status.ERROR,"抱歉！您没有权限访问该资源或该资源已不存在！","THIS"),questionBankByIdList);
        }
        if(!StringUtil.isEquals(questionBank.getOwnerId(),userId)&&!StringUtil.isEquals(questionBank.getVisibility(),"公开")){
            return new QuestionBankJson(new StatusJson(Status.ERROR,"抱歉！您没有权限访问该资源或该资源已不存在！","THIS"),questionBankByIdList);
        }
//        if(!StringUtil.isEquals(questionBank.getVisibility(),"公开")){
//            return new QuestionBankJson(new StatusJson(Status.ERROR,"抱歉！您没有权限访问该资源或该资源已不存在！","THIS"),questionBankByIdList);
//        }
        User owner = userService.findByUserId(questionBank.getOwnerId());
        QuestionBankDataJson examinationData = new QuestionBankDataJson(questionBank.getQuestionBankId(),questionBank.getQuestionBankName(),
                questionBank.getOwnerId(),owner.getUsername(),questionBank.getVisibility());
        questionBankByIdList.add(examinationData);
        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"成功找到题库","THIS"),questionBankByIdList);
    }

    @GetMapping(value="webapi/searchQuestionBank/byName/{questionBankName}")
    public QuestionBankJson findAllByQuestionBankName(@PathVariable("questionBankName") String questionBankName,HttpSession session){
        List<QuestionBank> allByQuestionBankName = questionBankService.findAllByQuestionBankNameLike(questionBankName);
        List<QuestionBankDataJson> questionBankDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new QuestionBankJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),questionBankDataList);
        }
        if(allByQuestionBankName.size()==0) {
            return new QuestionBankJson(new StatusJson(Status.WARNING, "抱歉！您没有权限访问该资源或该资源已不存在！", "THIS"), questionBankDataList);
        }
        String userId = (String)session.getAttribute(SessionKey.USER_ID);
        for(QuestionBank questionBank : allByQuestionBankName){
            if(!StringUtil.isEquals(questionBank.getOwnerId(),userId)&&!StringUtil.isEquals(questionBank.getVisibility(),"公开")){
                continue;
            }
            User owner = userService.findByUserId(questionBank.getOwnerId());
            QuestionBankDataJson questionBankDataJson = new QuestionBankDataJson(questionBank.getQuestionBankId(),
                    questionBank.getQuestionBankName(),questionBank.getOwnerId(),owner.getUsername(),questionBank.getVisibility());
            questionBankDataList.add(questionBankDataJson);
        }
        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"显示符合关键字的题库","THIS"),questionBankDataList);
    }

    @GetMapping(value="webapi/searchQuestionBank/byUserId")
    public QuestionBankJson findAllByUserId(HttpSession session){
        List<QuestionBankDataJson> questionBankDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new QuestionBankJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),questionBankDataList);
        }
        String userId = (String)session.getAttribute(SessionKey.USER_ID);
        List<QuestionBank> allByOwnerId = questionBankService.findAllByOwnerId(userId);
        for(QuestionBank questionBank : allByOwnerId){
            User owner = userService.findByUserId(questionBank.getOwnerId());
            QuestionBankDataJson questionBankDataJson = new QuestionBankDataJson(questionBank.getQuestionBankId(),
                    questionBank.getQuestionBankName(),questionBank.getOwnerId(),owner.getUsername(),questionBank.getVisibility());
            questionBankDataList.add(questionBankDataJson);
        }
        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"显示自己的题库","THIS"),questionBankDataList);
    }

    @GetMapping(value="webapi/searchQuestionBank/byOwnerId/{ownerId}")
    public QuestionBankJson findAllByOwnerId(@PathVariable("ownerId") String ownerId){
        List<QuestionBank> allByOwnerId = questionBankService.findAllByOwnerIdAndVisibility(ownerId);
        List<QuestionBankDataJson> questionBankDataList = new ArrayList<>();
        if(allByOwnerId.size()==0) {
            return new QuestionBankJson(new StatusJson(Status.WARNING, "抱歉！您没有权限访问该资源或该资源已不存在！", "THIS"), questionBankDataList);
        }
        for(QuestionBank questionBank : allByOwnerId){
            User owner = userService.findByUserId(questionBank.getOwnerId());
            QuestionBankDataJson questionBankDataJson = new QuestionBankDataJson(questionBank.getQuestionBankId(),
                    questionBank.getQuestionBankName(),questionBank.getOwnerId(),owner.getUsername(),questionBank.getVisibility());
            questionBankDataList.add(questionBankDataJson);
        }
        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"显示该创建者的所有题库","THIS"),questionBankDataList);
    }

    @PostMapping(value="webapi/editBankInfo")
    public StatusJson editQuestionBankInfo(String questionBankId,String questionBankName,String visibility){
        if(questionBankService.editQuestionBankName(questionBankId,questionBankName)==null){
            return new StatusJson(Status.SUCCESS,"修改名称失败","THIS");
        }
        if(questionBankService.editVisibility(questionBankId,visibility)==null){
            return new StatusJson(Status.SUCCESS,"修改可见性失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"修改成功","THIS");
    }

    @GetMapping(value="webapi/showQuestion/fromBank/{questionBankId}")
    public QuestionBank_Question_Json showQuestion(@PathVariable("questionBankId") String questionBankId){
        List<QuestionBankDataJson> questionBankDataList = new ArrayList<>();
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        QuestionBank questionBank = questionBankService.findOneByQuestionBankId(questionBankId);
        if(questionBank==null){
            return new QuestionBank_Question_Json(new StatusJson(Status.ERROR,"没有这个题库！","THIS"),questionBankDataList,questionDataList);
        }
        QuestionBankDataJson questionBankDataJson = new QuestionBankDataJson(questionBank.getQuestionBankId(),questionBank.getQuestionBankName(),
                questionBank.getOwnerId(),userService.findByUserId(questionBank.getOwnerId()).getUsername(),
                questionBank.getVisibility());
        questionBankDataList.add(questionBankDataJson);
        List<Question> allQuestionFromQuestionBank = questionBank_question_service.findAllQuestionByQuestionBankId(questionBankId);
        if(allQuestionFromQuestionBank.size()==0){
            return new QuestionBank_Question_Json(new StatusJson(Status.WARNING,"这个题库没题！","THIS"),questionBankDataList,questionDataList);

        }
        for(Question question : allQuestionFromQuestionBank){
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent(),
                    question.getQuestionAnswer(),question.getQuestionType(),
                    question.getUploaderId(),userService.findByUserId(question.getUploaderId()).getUsername());
            questionDataList.add(questionDataJson);
        }
        return new QuestionBank_Question_Json(new StatusJson(Status.SUCCESS,"该题库的题！","THIS"),questionBankDataList,questionDataList);
    }


    @PostMapping(value="webapi/addQuestion/toBank")
    public StatusJson addQuestionToQuestionBank(String questionId, String questionBankId,HttpSession session){
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new StatusJson(Status.ERROR,"没有登陆啊","THIS");
        }
        if(!StringUtil.isEquals(questionBankService.findOneByQuestionBankId(questionBankId).getOwnerId(),(String)session.getAttribute(SessionKey.USER_ID))){
            return new StatusJson(Status.ERROR,"用户不正确","THIS");
        }
        Question question = questionService.findByQuestionId(questionId);

        if(question==null){
            return new StatusJson(Status.ERROR,"没有这个题","THIS");
        }
        QuestionBank_Question qb_q = questionBank_question_service.addQuestionToQuestionBank(questionId,questionBankId);

        if(qb_q==null){
            return new StatusJson(Status.ERROR,"添加失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"添加成功","THIS");
    }

    @PostMapping(value="webapi/deleteQuestion/fromBank")
    public StatusJson deleteQuestionFromQuestionBank(String questionId,String questionBankId){
        if(!questionBank_question_service.deleteQuestionFromQuestionBank(questionId,questionBankId)){
            return new StatusJson(Status.SUCCESS,"删除失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"删除成功","THIS");
    }
}
