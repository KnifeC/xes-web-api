package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Service.Interfaces.PaperQuestionService;
import org.haveagroup.xes.Service.Interfaces.PaperService;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Web.ResponseJson.PaperJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PaperController {
    @Autowired
    PaperService paperService;
    @Autowired
    QuestionService questionService;
    @Autowired
    PaperQuestionService paperQuestionService;

    @GetMapping(value="showPaper/{paperId}")
    public PaperJson showPaper(@PathVariable("paperId") String paperId){
        List<String> showQuestionIds = paperQuestionService.findQuestionIdByPaperId(paperId);
        List<Question> showQuestions = new ArrayList<>();
        for(String questionId : showQuestionIds){
            showQuestions.add(questionService.findByQuestionId(questionId));
        }
        return new PaperJson(new StatusJson(Status.SUCCESS,"显示试卷","THIS"),paperId,showQuestionIds,showQuestions);
    }


    public void addQuestionToPaper(String paperId,String questionId){
        Question question = questionService.findByQuestionId(questionId);

    }

}
