package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping(value="/webapi/search")
    public List<Question> searchQuestionByQuestionContent(String questionContent){
        List<Question> allByQuestionContent = questionService.findAllByQuestionContentLike(questionContent);
        for(Question q : allByQuestionContent){
            System.out.println(q.getQuestionContent());
        }
        return allByQuestionContent;
    }
}
