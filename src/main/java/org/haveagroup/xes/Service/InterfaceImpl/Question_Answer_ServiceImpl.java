package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.Question_Answer;
import org.haveagroup.xes.Dal.Repo.Question_Answer_Repo;
import org.haveagroup.xes.Service.Interfaces.Question_Answer_Service;
import org.haveagroup.xes.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Question_Answer_ServiceImpl implements Question_Answer_Service {
    @Autowired
    Question_Answer_Repo question_answer_repo;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean answerQuestion(String examinationId, String paperId,String questionId, String userId,String answer){
        try{
            Question_Answer question_answer = new Question_Answer(examinationId,paperId,questionId,userId);
            question_answer.setAnswer(answer);
            question_answer_repo.save(question_answer);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editQuestion(String examinationId,String paperId, String questionId, String userId,String answer){
        try{
            Question_Answer question_answer = question_answer_repo.findByPaperIdAndQuestionIdAndUserId(paperId,questionId,userId);
            question_answer.setAnswer(answer);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Question_Answer> findAnswers(String paperId,String userId){
        try{
            List<Question_Answer> answers = question_answer_repo.findByPaperIdAndUserId(paperId,userId);
            return answers;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public Question_Answer findAnswer(String paperId, String questionId, String userId){
        try{
            String question_answer_id = question_answer_repo.findByPaperIdAndQuestionIdAndUserId(paperId,questionId,userId)
                    .getQuestion_answer_id();
            Question_Answer q_a = (Question_Answer)redisUtil.getObject(question_answer_id);
            if(null == q_a){
                q_a = question_answer_repo.findByPaperIdAndQuestionIdAndUserId(paperId,questionId,userId);
                redisUtil.setObject(question_answer_id,q_a);
            }
            return q_a;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List<Question_Answer> findAnswers(String paperId,String userId){
//        List<Question_Answer> answers = question_answer_repo.findByExaminationIdAndUserId(paperId,userId);
//        return answers;
//    }
}
