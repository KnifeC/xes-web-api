package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Service.Interfaces.Question_Tag_Service;
import org.haveagroup.xes.Service.Interfaces.UserService;
import org.haveagroup.xes.Web.Forms.UploadForm;
import org.haveagroup.xes.Web.ResponseJson.QuestionDataJson;
import org.haveagroup.xes.Web.ResponseJson.QuestionJson;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    Question_Tag_Service question_tag_service;



    @PostMapping(value="webapi/uploadQuestion")
    public QuestionJson uploadQuestion(UploadForm uploadForm, HttpSession session){
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new QuestionJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),questionDataList);
        }
//        if(!session.getAttribute(SessionKey.USER_TYPE).equals("teacher")){
//            return new QuestionJson(new StatusJson(Status.ERROR,"没有权限","THIS"),questionDataList);
//        }
        String uploaderId = (String)session.getAttribute(SessionKey.USER_ID);
        String uploaderName = (String)session.getAttribute(SessionKey.USER_NAME);
        Question question = questionService.uploadQuestion(uploadForm.getQuestionContent(),uploadForm.getQuestionAnswer(),
                uploadForm.getQuestionType(),uploaderId);
        if(question==null){
            return new QuestionJson(new StatusJson(Status.ERROR,"上传失败","THIS"),questionDataList);
        }
        QuestionDataJson questionData = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent(),
                question.getQuestionAnswer(),question.getQuestionType(),uploaderId,uploaderName);
        questionDataList.add(questionData);
        return new QuestionJson(new StatusJson(Status.SUCCESS,"上传试题","THIS"),questionDataList);
    }

    @GetMapping(value="webapi/question/{questionId}")
    public QuestionJson searchQuestionByQuestionId(@PathVariable("questionId") String questionId){
        Question question = questionService.findByQuestionId(questionId);
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        if(question==null){
            return new QuestionJson(new StatusJson(Status.WARNING,"没有该Id对应试题","THIS"),questionDataList);
        }else{
            User uploader = userService.findByUserId(question.getUploaderId());
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent(),
                    question.getQuestionAnswer(),question.getQuestionType(),question.getUploaderId(),uploader.getUsername());
            questionDataList.add(questionDataJson);
            return new QuestionJson(new StatusJson(Status.SUCCESS,"显示该Id对应试题","THIS"),questionDataList);
        }
    }

    @GetMapping(value="webapi/search/{questionContent}")
    public QuestionJson searchQuestionByQuestionContent(@PathVariable("questionContent") String questionContent){
        List<Question> allByQuestionContent = questionService.findAllByQuestionContentLike(questionContent);
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        if(allByQuestionContent.size()==0){
            return new QuestionJson(new StatusJson(Status.WARNING,"没有符合关键字的试题","THIS"),questionDataList);
        }
        for(Question question : allByQuestionContent){
            User uploader = userService.findByUserId(question.getUploaderId());
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent(),
                    question.getUploaderId(),uploader.getUsername());
            questionDataList.add(questionDataJson);
        }
        return new QuestionJson(new StatusJson(Status.SUCCESS,"显示符合关键字的试题","THIS"),questionDataList);
    }

    @GetMapping(value="webapi/searchQuestion/byUploaderId/{uploaderId}")
    public QuestionJson findAllByOwnerId(@PathVariable("uploaderId") String uploaderId){
        List<Question> allByUploaderId = questionService.findAllByUploaderId(uploaderId);
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        if(allByUploaderId.size()==0) {
            return new QuestionJson(new StatusJson(Status.WARNING, "抱歉！您没有权限访问该资源或该资源已不存在！", "THIS"), questionDataList);
        }
        for(Question question : allByUploaderId){
            User uploader = userService.findByUserId(question.getUploaderId());
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent(),
                    question.getQuestionAnswer(),question.getQuestionType(),question.getUploaderId(),uploader.getUsername());
            questionDataList.add(questionDataJson);
        }
        return new QuestionJson(new StatusJson(Status.SUCCESS,"显示该创建者的所有题","THIS"),questionDataList);
    }

    @GetMapping(value="webapi/searchQuestion/byTag/{tagName}")
    public QuestionJson searchQuestionByTag(@PathVariable("tagName") String tagName,HttpSession session){
        List<Question> allByTag = question_tag_service.findByTagName(tagName);
        List<QuestionDataJson> questionDataList = new ArrayList<>();
        if(allByTag.size()==0){
            return new QuestionJson(new StatusJson(Status.WARNING,"没有符合标签的试题","THIS"),questionDataList);
        }
        for(Question question : allByTag){
            User uploader = userService.findByUserId(question.getUploaderId());
            QuestionDataJson questionDataJson = new QuestionDataJson(question.getQuestionId(),question.getQuestionContent(),
                    question.getUploaderId(),uploader.getUsername());
            questionDataList.add(questionDataJson);
        }
        return new QuestionJson(new StatusJson(Status.SUCCESS,"显示符合标签的试题","THIS"),questionDataList);
    }
}
