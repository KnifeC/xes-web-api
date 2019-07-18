package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Service.Interfaces.QuestionBankService;
import org.haveagroup.xes.Service.Interfaces.Question_QuestionBankService;
import org.haveagroup.xes.Web.ResponseJson.QuestionBankDataJson;
import org.haveagroup.xes.Web.ResponseJson.QuestionBankJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionBankController {
    @Autowired
    Question_QuestionBankService question_questionBankService;

    @Autowired
    QuestionBankService questionBankService;

    @PostMapping(value="webapi/createQuestionBank")
    public QuestionBankJson createQuestionBank(String questionBankName, HttpSession session){
        List<QuestionBankDataJson> questionBankDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new QuestionBankJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),questionBankDataList);
        }
        String ownerId = (String)session.getAttribute(SessionKey.USER_ID);
        String ownerName = (String)session.getAttribute(SessionKey.USER_NAME);
        QuestionBank questionBank = questionBankService.createQuestionBank(questionBankName,ownerId);
        QuestionBankDataJson questionBankData = new QuestionBankDataJson(questionBank.getQuestionBankId(),
                questionBank.getQuestionBankName(),ownerId,ownerName);
        questionBankDataList.add(questionBankData);
        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"创建考试","THIS"),questionBankDataList);
    }

//    @PostMapping(value="webapi/addToBank")
//    public QuestionBankJson addQuestionToQuestionBank(String questionBankId, String questionId){
//        question_questionBankService.addQuestionToQuestionBank(questionBankId,questionId);
//
//        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"添加试题到题库中","THIS"),new QuestionBankDataJson(questionBankId));
//    }


}
