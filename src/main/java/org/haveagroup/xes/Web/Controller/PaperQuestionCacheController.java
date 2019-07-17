package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Service.Interfaces.PaperQuestionCacheService;
import org.haveagroup.xes.Service.Interfaces.PaperService;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaperQuestionCacheController {
    @Autowired
    PaperQuestionCacheService paperQuestionCacheService;
    @Autowired
    PaperService paperService;
    @Autowired
    QuestionService questionService;

    @PostMapping(value="webapi/addQuestionToPaperCache/{questionId}")
    public boolean addQuestionToPaperCache(@PathVariable("questionId") String questionId, String paperId){

        return true;
    }



}
