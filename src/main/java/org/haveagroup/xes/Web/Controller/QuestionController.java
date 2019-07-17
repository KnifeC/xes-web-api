package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
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
        List<String> allIdByQuestionContent = new ArrayList<>();
        List<Question> allByQuestionContent = questionService.findAllByQuestionContentLike(questionContent);
        for(Question q : allByQuestionContent){
            allIdByQuestionContent.add(q.getQuestionId());
        }
        return new QuestionJson(new StatusJson(Status.SUCCESS,"显示符合关键字的试题","THIS"),allIdByQuestionContent,allByQuestionContent);
    }

    @GetMapping(value="webapi/question/{questionId}")
    public QuestionJson searchQuestionByQuestionId(@PathVariable("questionId") String questionId){
        Question question = questionService.findByQuestionId(questionId);
        List<String> oneQuestionId = new ArrayList<>();
        List<Question> oneQuestion = new ArrayList<>();
        oneQuestionId.add(question.getQuestionId());
        oneQuestion.add(question);
        return new QuestionJson(new StatusJson(Status.SUCCESS,"显示该Id对应试题","THIS"),oneQuestionId,oneQuestion);
    }
}
