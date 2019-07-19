package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Repo.QuestionRepo;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.haveagroup.xes.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Question uploadQuestion(String questionContent,String questionAnswer,String questionType,String uploaderId){
        try{
            Question question = new Question();
            question.setQuestionContent(questionContent);
            question.setQuestionAnswer(questionAnswer);
            question.setQuestionType(questionType);
            question.setUploaderId(uploaderId);
            questionRepo.save(question);
            return question;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Question> findAllByQuestionContentLike(String questionContent){
        try{
            List<Question> allByQuestionContent = questionRepo.findAllByQuestionContentLike("%"+questionContent+"%");
            return allByQuestionContent;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Question findByQuestionId(String questionId){
        try{
            Question question = (Question)redisUtil.getObject(questionId);
            if(null == question){
                question = questionRepo.findByQuestionId(questionId);
                redisUtil.setObject(questionId,question);
            }
            return question;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List<Question> findCacheByQuestionContentLike(String questionContent){
//        try{
//            List<Question> allByQuestionContent = redisUtil.getQuestionList(questionContent);
//
//            allByQuestionContent = questionRepo.findAllByQuestionContentLike("%"+questionContent+"%");
//            return allByQuestionContent;
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
}
