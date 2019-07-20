package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Model.Paper_Question;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.Question_Answer;
import org.haveagroup.xes.Service.Interfaces.*;
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
    @Autowired
    Question_Answer_Service question_answer_service;

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
        List<PaperDataJson> paperDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new PaperJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),paperDataList);
        }
        Paper paper = paperService.findByPaperId(paperId);
        if(paper==null){
            return new PaperJson(new StatusJson(Status.ERROR,"没有这个试卷","THIS"),paperDataList);
        }
        PaperDataJson paperDataJson = new PaperDataJson(paper.getPaperId(),paper.getPaperName(),
                paper.getExaminationId(),paper.getExaminationName());
        paperDataList.add(paperDataJson);
        return new PaperJson(new StatusJson(Status.SUCCESS,"试卷","THIS"),paperDataList);
    }

    @GetMapping(value="webapi/searchPaper/byExaminationId")
    public PaperJson findAllByExaminationId(String examinationId,HttpSession session){
        List<PaperDataJson> paperDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new PaperJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),paperDataList);
        }
        List<Paper> allByExaminationId =paperService.findAllByExaminationId(examinationId);
        if(allByExaminationId.size()==0){
            return new PaperJson(new StatusJson(Status.WARNING,"该考试没有试卷","THIS"),paperDataList);
        }
        for(Paper paper : allByExaminationId){
            PaperDataJson paperDataJson = new PaperDataJson(paper.getPaperId(),paper.getPaperName(),
                    paper.getExaminationId(),paper.getExaminationName());
            paperDataList.add(paperDataJson);
        }
        return new PaperJson(new StatusJson(Status.SUCCESS,"试卷基础信息","THIS"),paperDataList);
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

    @GetMapping(value="webapi/showActivePaper")
    public Paper_Answer_Json showActivePaper(String paperId,String userId,HttpSession session){
        List<PaperDataJson> paperDataList = new ArrayList<>();
        List<AnswerDataJson> answerDataList = new ArrayList<>();
        if(!StringUtil.isEquals((String)session.getAttribute(SessionKey.USER_ID),userId)){
            return new Paper_Answer_Json(new StatusJson(Status.ERROR,"用户信息不正确","THIS"),paperDataList,answerDataList);
        }
        Paper paper = paperService.findByPaperId(paperId);
        if(paper==null){
            return new Paper_Answer_Json(new StatusJson(Status.ERROR,"没有这张试卷","THIS"),paperDataList,answerDataList);
        }
        PaperDataJson paperDataJson = new PaperDataJson(paper.getPaperId(),paper.getPaperName(),
                paper.getExaminationId(),paper.getExaminationName());
        paperDataList.add(paperDataJson);
        List<Question_Answer> question_answers = question_answer_service.findAnswers(paperId,userId);
        for(Question_Answer question_answer : question_answers){
            Question question = questionService.findByQuestionId(question_answer.getQuestionId());
            AnswerDataJson answerDataJson = new AnswerDataJson(question_answer.getExaminationId(),question_answer.getPaperId(),
                    question_answer.getQuestionId(),question.getQuestionContent(),question_answer.getAnswer());
            answerDataList.add(answerDataJson);
        }
        return new Paper_Answer_Json(new StatusJson(Status.SUCCESS,"显示已作答答案","THIS"),paperDataList,answerDataList);
    }

    @PostMapping(value="webapi/addQuestion/toPaper")
    public StatusJson addQuestionToPaper(String paperId,String questionId){
        Question question = questionService.findByQuestionId(questionId);
        if(question==null){
            return new StatusJson(Status.ERROR,"没有这个题","THIS");
        }
        System.out.println(paper_question_service.isQuestionUsed(paperId,questionId));
        if(paper_question_service.isQuestionUsed(paperId,questionId)){
            return new StatusJson(Status.ERROR,"试卷中已有了这个题","THIS");
        }
        Paper_Question p_q = paper_question_service.addQuestionToPaper(questionId,paperId);
        if(p_q==null){
            return new StatusJson(Status.ERROR,"添加失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"添加成功","THIS");
    }

    @PostMapping(value="webapi/answerQuestion")
    public StatusJson answerQuestion(String examinationId,String paperId,String questionId,String userId,String answer,HttpSession session){
        //List<AnswerDataJson> answerList = new ArrayList<>();
        if(!StringUtil.isEquals((String)session.getAttribute(SessionKey.USER_ID),userId)){
            return new StatusJson(Status.ERROR,"用户信息不正确","THIS");
        }
        if(!question_answer_service.answerQuestion(examinationId,paperId,questionId,userId,answer)){
            return new StatusJson(Status.ERROR,"回答失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"回答成功","THIS");

    }
    @PostMapping(value="webapi/editAnswer")
    public StatusJson editAnswer(String examinationId,String paperId,String questionId,String userId,String answer,HttpSession session){
        if(StringUtil.isEquals((String)session.getAttribute(SessionKey.USER_ID),userId)){
            return new StatusJson(Status.ERROR,"用户信息不正确","THIS");
        }
        if(!question_answer_service.editQuestion(examinationId,paperId,questionId,userId,answer)){
            return new StatusJson(Status.ERROR,"修改失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"修改成功","THIS");
    }
}
