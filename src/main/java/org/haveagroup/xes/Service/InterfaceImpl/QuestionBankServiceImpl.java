package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Dal.Repo.QuestionBankRepo;
import org.haveagroup.xes.Service.Interfaces.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    QuestionBankRepo questionBankRepo;

    @Override
    public QuestionBank createQuestionBank(String questionBankName,String ownerId,String visibility){
        try{
            QuestionBank questionBank = new QuestionBank();
            questionBank.setQuestionBankName(questionBankName);
            questionBank.setOwnerId(ownerId);
            questionBank.setVisibility(visibility);
            questionBankRepo.save(questionBank);
            return questionBank;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteQuestionBank(String questionBankId,String ownerId){
        try{
            questionBankRepo.deleteById(questionBankId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public QuestionBank editQuestionBankName(String questionBankId,String questionBankName){
        try{
            QuestionBank questionBank = questionBankRepo.findOneByQuestionBankId(questionBankId);
            questionBank.setQuestionBankName(questionBankName);
            return questionBank;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public QuestionBank editVisibility(String questionBankId,String visibility){
        try{
            QuestionBank questionBank = questionBankRepo.findOneByQuestionBankId(questionBankId);
            questionBank.setVisibility(visibility);
            return questionBank;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public QuestionBank findOneByQuestionBankId(String examinationId){
        try{
            QuestionBank oneByQuestionBankId = questionBankRepo.findOneByQuestionBankId(examinationId);
            return oneByQuestionBankId;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<QuestionBank> findAllByQuestionBankNameLike(String questionBankName){
        try{
            List<QuestionBank> allByQuestionBankNameLike = questionBankRepo.findAllByQuestionBankNameLike("%"+questionBankName+"%");
            return allByQuestionBankNameLike;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
