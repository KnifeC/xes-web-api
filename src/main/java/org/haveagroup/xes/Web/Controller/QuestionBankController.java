package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Service.Interfaces.QuestionBankService;
import org.haveagroup.xes.Service.Interfaces.Question_QuestionBankService;
import org.haveagroup.xes.Web.ResponseJson.QuestionBankDataJson;
import org.haveagroup.xes.Web.ResponseJson.QuestionBankJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionBankController {
    @Autowired
    Question_QuestionBankService question_questionBankService;

//    @Autowired
//    QuestionBankService questionBankService;

    @PostMapping(value="webapi/addToBank")
    public QuestionBankJson addQuestionToQuestionBank(String questionBankId, String questionId){
        question_questionBankService.addQuestionToQuestionBank(questionBankId,questionId);

        return new QuestionBankJson(new StatusJson(Status.SUCCESS,"添加试题到题库中","THIS"),new QuestionBankDataJson(questionBankId));
    }


}
