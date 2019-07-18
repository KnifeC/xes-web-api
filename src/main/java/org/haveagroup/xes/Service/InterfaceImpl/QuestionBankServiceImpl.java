package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Dal.Repo.QuestionBankRepo;
import org.haveagroup.xes.Service.Interfaces.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    QuestionBankRepo questionBankRepo;

    @Override
    public QuestionBank createQuestionBank(String questionBankName, String ownerId){
        try{
            QuestionBank questionBank = new QuestionBank();
            questionBank.setQuestionBankName(questionBankName);
            questionBank.setOwnerId(ownerId);
            questionBankRepo.save(questionBank);
            return questionBank;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteQuestionBank(String questionBankId,String ownerId){
        try{
            questionBankRepo.deleteById(questionBankId);

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
