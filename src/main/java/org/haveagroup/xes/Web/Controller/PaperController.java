package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Service.Interfaces.PaperQuestionService;
import org.haveagroup.xes.Service.Interfaces.PaperService;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Web.ResponseJson.PaperDataJson;
import org.haveagroup.xes.Web.ResponseJson.PaperJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value="webapi/createPaper")
    public PaperJson createPaper(String examinationId, String examinationName){
        Paper paper = paperService.createPaper(examinationId,examinationName);
        return new PaperJson(new StatusJson(Status.SUCCESS,"创建试卷","THIS"),new PaperDataJson(paper.getPaperId()));
    }

    @GetMapping(value="webapi/showPaper/{paperId}")
    public PaperJson showPaper(@PathVariable("paperId") String paperId){
        List<String> showQuestionIds = paperQuestionService.findQuestionIdByPaperId(paperId);
        List<Question> showQuestions = new ArrayList<>();
        for(String questionId : showQuestionIds){
            showQuestions.add(questionService.findByQuestionId(questionId));
        }
        return new PaperJson(new StatusJson(Status.SUCCESS,"显示试卷","THIS"),new PaperDataJson(paperId,showQuestionIds,showQuestions));
    }

    @PostMapping(value="webapi/addQuestion/toPaper/{questionId}")
    public PaperJson addQuestionToPaper(@PathVariable("questionId") String questionId,String paperId){
        List<String> addedQuestionIdList = paperQuestionService.findQuestionIdByPaperId(paperId);
        addedQuestionIdList.add(questionId);

        List<Question> addedQuestionList = new ArrayList<>();
        for(String addedQuestionId : addedQuestionIdList){
            Question addedQuestion = questionService.findByQuestionId(addedQuestionId);
            addedQuestionList.add(addedQuestion);
        }
        boolean addQuestionToPaper = paperQuestionService.addQuestionToPaper(paperId,questionId);
        return new PaperJson(new StatusJson(Status.SUCCESS,"向该试卷中添加试题","THIS"),new PaperDataJson(paperId,addedQuestionIdList,addedQuestionList));
    }
}
