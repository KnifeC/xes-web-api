package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionBank_Question;
import org.haveagroup.xes.Dal.Repo.QuestionBank_Question_Repo;
import org.haveagroup.xes.Dal.Repo.QuestionRepo;
import org.haveagroup.xes.Service.Interfaces.QuestionBank_Question_Service;
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
    QuestionBank_Question_Repo questionBank_question_repo;

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
    public boolean deleteQuestion(String questionId){
        try{
            questionRepo.deleteById(questionId);
            List<QuestionBank_Question> questionBank_questions = questionBank_question_repo.findAllQuestionBankIdByQuestionId(questionId);
            if(questionBank_questions.size()==0){
                return false;
            }
            for(QuestionBank_Question questionBank_question : questionBank_questions){
                questionBank_question_repo.deleteById(questionBank_question.getQuestionBank_question_id());
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
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

    @Override
    public List<Question> findAllByUploaderId(String uploaderId){
        try{
            List<Question> questionList = questionRepo.findAllByUploaderId(uploaderId);
            return questionList;
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
