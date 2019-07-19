package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Dal.Model.QuestionBank_Question;
import org.haveagroup.xes.Dal.Repo.QuestionBankRepo;
import org.haveagroup.xes.Dal.Repo.QuestionBank_Question_Repo;
import org.haveagroup.xes.Dal.Repo.QuestionRepo;
import org.haveagroup.xes.Service.Interfaces.QuestionBank_Question_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionBank_Question_ServiceImpl implements QuestionBank_Question_Service {
    @Autowired
    QuestionBank_Question_Repo questionBank_question_repo;
    @Autowired
    QuestionBankRepo questionBankRepo;
    @Autowired
    QuestionRepo questionRepo;

    @Override
    public QuestionBank_Question findByQuestionBankIdAndQuestionId(String questionBankId, String questionId){
        try{
            return questionBank_question_repo.findByQuestionBankIdAndQuestionId(questionBankId,questionId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<QuestionBank> findAllQuestionBankByQuestionId(String questionId){
        try{
            List<QuestionBank> questionBanks = new ArrayList<>();
            List<QuestionBank_Question> qb_q = questionBank_question_repo.findAllQuestionBankIdByQuestionId(questionId);
            for(QuestionBank_Question questionBank_question : qb_q){
                questionBanks.add(questionBankRepo.findOneByQuestionBankId(questionBank_question.getQuestionBankId()));
            }
            return questionBanks;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Question> findAllQuestionByQuestionBankId(String questionBankId){
        try{
            List<Question> questions = new ArrayList<>();
            List<QuestionBank_Question> qb_q = questionBank_question_repo.findAllQuestionIdByQuestionBankId(questionBankId);
            for(QuestionBank_Question questionBank_question : qb_q){
                questions.add(questionRepo.findByQuestionId(questionBank_question.getQuestionId()));
            }
            return questions;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public QuestionBank_Question addQuestionToQuestionBank(String questionId,String questionBankId){
        try{
            QuestionBank_Question questionBank_question = new QuestionBank_Question();
            questionBank_question.setQuestionId(questionId);
            questionBank_question.setQuestionBankId(questionBankId);
            questionBank_question_repo.save(questionBank_question);
            return questionBank_question;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean deleteQuestionFromQuestionBank(String questionId,String questionBankId){
        try{
            String deleteId = questionBank_question_repo.findByQuestionBankIdAndQuestionId(questionBankId,questionId)
                    .getQuestionBank_question_id();
            questionBank_question_repo.deleteById(deleteId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
