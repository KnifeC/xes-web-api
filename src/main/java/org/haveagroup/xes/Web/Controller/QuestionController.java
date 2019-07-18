package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Web.ResponseJson.QuestionDataJson;
import org.haveagroup.xes.Web.ResponseJson.QuestionJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping(value="webapi/search/{questionContent}")
    public QuestionJson searchQuestionByQuestionContent(@PathVariable("questionContent") String questionContent){
        List<Question> allByQuestionContent = questionService.findAllByQuestionContentLike(questionContent);
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        if(allByQuestionContent.size()==0){
            return new QuestionJson(new StatusJson(Status.WARNING,"没有符合关键字的试题","THIS"),questionDataList);
        }else{
            for(Question question : allByQuestionContent){
                QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent());
                questionDataList.add(questionDataJson);
            }
            return new QuestionJson(new StatusJson(Status.SUCCESS,"显示符合关键字的试题","THIS"),questionDataList);
        }
    }

    @GetMapping(value="webapi/question/{questionId}")
    public QuestionJson searchQuestionByQuestionId(@PathVariable("questionId") String questionId){
        Question question = questionService.findByQuestionId(questionId);
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        if(question==null){
            return new QuestionJson(new StatusJson(Status.WARNING,"没有该Id对应试题","THIS"),questionDataList);
        }else{
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent());
            questionDataList.add(questionDataJson);
            return new QuestionJson(new StatusJson(Status.SUCCESS,"显示该Id对应试题","THIS"),questionDataList);
        }
    }
}
