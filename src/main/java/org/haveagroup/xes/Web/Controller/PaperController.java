package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Model.Paper_Question;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.haveagroup.xes.Service.Interfaces.PaperService;
import org.haveagroup.xes.Service.Interfaces.Paper_Question_Service;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Util.StringUtil;
import org.haveagroup.xes.Web.ResponseJson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PaperController {
    @Autowired
    PaperService paperService;
    @Autowired
    QuestionService questionService;
    @Autowired
    ExaminationService examinationService;
    @Autowired
    Paper_Question_Service paper_question_service;

    @PostMapping(value="webapi/createPaper")
    public PaperJson createPaper(String examinationId, String paperName, HttpSession session){
        List<PaperDataJson> paperDataList = new ArrayList<>();
        if(!StringUtil.isEquals((String)session.getAttribute(SessionKey.USER_ID),examinationService.findOneByExaminationId(examinationId).getCreatorId())){
            return new PaperJson(new StatusJson(Status.WARNING,"用户信息不正确","THIS"),paperDataList);
        }
        Paper paper = paperService.createPaper(examinationId,paperName);
        PaperDataJson paperData = new PaperDataJson(paper.getPaperId(),paper.getPaperName(),
                paper.getExaminationId(),paper.getExaminationName());
        paperDataList.add(paperData);
        return new PaperJson(new StatusJson(Status.SUCCESS,"创建试卷","THIS"),paperDataList);
    }

    @DeleteMapping(value="webapi/deletePaper")
    public StatusJson deletePaper(String paperId,HttpSession session){
        paperService.findExaminationIdByPaperId(paperId);
        String creatorId = examinationService.findOneByExaminationId(paperId).getCreatorId();
        if(!StringUtil.isEquals((String)session.getAttribute(SessionKey.USER_ID),creatorId)){
            return new StatusJson(Status.ERROR,"用户信息不正确","THIS");
        }
        if(paperService.deletePaper(paperId)){
            return new StatusJson(Status.ERROR,"删除失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"删除成功","THIS");
    }

    @GetMapping(value="webapi/paper/{paperId}")
    public PaperJson findOneByPaperId(@PathVariable("paperId") String paperId,HttpSession session){
        List<PaperDataJson> paperByIdList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new PaperJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),paperByIdList);
        }
        Paper paper = paperService.findByPaperId(paperId);
        if(paper==null){
            return new PaperJson(new StatusJson(Status.ERROR,"没有这个试卷","THIS"),paperByIdList);

        }
        PaperDataJson paperDataJson = new PaperDataJson(paper.getPaperId(),paper.getPaperName(),
                paper.getExaminationId(),paper.getExaminationName());
        paperByIdList.add(paperDataJson);
        return new PaperJson(new StatusJson(Status.SUCCESS,"试卷","THIS"),paperByIdList);
    }

    @GetMapping(value="webapi/showQuestion/fromPaper/{paperId}")
    public Paper_Question_Json showPaper(@PathVariable("paperId") String paperId){
        List<PaperDataJson> paperDataList = new ArrayList<>();
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        Paper paper = paperService.findByPaperId(paperId);
        if(paper==null){
            return new Paper_Question_Json(new StatusJson(Status.ERROR,"没有这张试卷","THIS"),paperDataList,questionDataList);
        }
        PaperDataJson paperDataJson = new PaperDataJson(paper.getPaperId(),paper.getPaperName(),
                paper.getExaminationId(),paper.getExaminationName());
        paperDataList.add(paperDataJson);
        List<Question> showQuestions = paper_question_service.findAllQuestionByPaperId(paperId);
        if(showQuestions.size()==0){
            return new Paper_Question_Json(new StatusJson(Status.ERROR,"这张卷纸没题","THIS"),paperDataList,questionDataList);
        }
        for(Question question : showQuestions){
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionContent(),question.getQuestionType());
            questionDataList.add(questionDataJson);
        }
        return new Paper_Question_Json(new StatusJson(Status.ERROR,"这张卷纸的题","THIS"),paperDataList,questionDataList);
    }

    @PostMapping(value="webapi/addQuestion/toPaper")
    public StatusJson addQuestionToPaper(String paperId,String questionId){
        Question question = questionService.findByQuestionId(questionId);
        if(question==null){
            return new StatusJson(Status.ERROR,"没有这个题","THIS");
        }
        if(paper_question_service.isQuestionUsed(paperId,questionId)){
            return new StatusJson(Status.ERROR,"试卷中已有了这个题","THIS");
        }
        Paper_Question p_q = paper_question_service.addQuestionToPaper(paperId,questionId);
        if(p_q==null){
            return new StatusJson(Status.ERROR,"添加失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"添加成功","THIS");
    }


}
