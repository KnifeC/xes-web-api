package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.Interfaces.QuestionBankService;
import org.haveagroup.xes.Service.Interfaces.QuestionBank_Question_Service;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Service.UserService;
import org.haveagroup.xes.Util.StringUtil;
import org.haveagroup.xes.Web.ResponseJson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new StatusJson(Status.ERROR,"修改失败","THIS");
    }

    @GetMapping(value="webapi/showQuestion/fromBank/{questionBankId}")
    public QuestionBank_Question_Json showQuestion(@PathVariable("questionBankId") String questionBankId){
        QuestionBank questionBank = questionBankService.findOneByQuestionBankId(questionBankId);
        List<QuestionBankDataJson> questionBankDataList = new ArrayList<>();
        QuestionBankDataJson questionBankDataJson = new QuestionBankDataJson(questionBank.getQuestionBankId(),questionBank.getQuestionBankName(),
                questionBank.getOwnerId(),userService.findByUserId(questionBank.getOwnerId()).getUsername(),
                questionBank.getVisibility());
        questionBankDataList.add(questionBankDataJson);

        List<Question> allQuestionFromQuestionBank = questionBank_question_service.findAllQuestionByQuestionBankId(questionBankId);
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        for(Question question : allQuestionFromQuestionBank){
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent(),
                    question.getQuestionAnswer(),question.getQuestionType(),
                    question.getUploaderId(),userService.findByUserId(question.getUploaderId()).getUsername());
            questionDataList.add(questionDataJson);
        }
        return new QuestionBank_Question_Json(new StatusJson(Status.SUCCESS,"该题库的题","THIS"),questionBankDataList,questionDataList);
    }


//    @PostMapping(value="webapi/addQuestion/toBank")
//    public QuestionBankJson addQuestionToQuestionBank(String questionBankId, String questionId){
//        question_questionBankService.addQuestionToQuestionBank(questionBankId,questionId);
//        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"添加试题到题库中","THIS"),new QuestionBankDataJson());
//    }
}
