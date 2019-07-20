package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Dal.Model.QuestionBank_Question;
import org.haveagroup.xes.Dal.Repo.QuestionBankRepo;
import org.haveagroup.xes.Dal.Repo.QuestionBank_Question_Repo;
import org.haveagroup.xes.Service.Interfaces.QuestionBankService;
import org.haveagroup.xes.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    QuestionBankRepo questionBankRepo;
    @Autowired
    QuestionBank_Question_Repo questionBank_question_repo;

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
            if(!StringUtil.isEquals(questionBankRepo.findOneByQuestionBankId(questionBankId).getOwnerId(),ownerId)){
                return false;
            }
            questionBankRepo.deleteById(questionBankId);
            List<QuestionBank_Question> questionBank_questions = questionBank_question_repo.findAllQuestionIdByQuestionBankId(questionBankId);
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
    public QuestionBank editQuestionBankName(String questionBankId,String questionBankName){
        try{
            QuestionBank questionBank = questionBankRepo.findOneByQuestionBankId(questionBankId);
            questionBank.setQuestionBankName(questionBankName);
            questionBankRepo.save(questionBank);
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
            questionBankRepo.save(questionBank);
            return questionBank;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public QuestionBank findOneByQuestionBankId(String questionBankId){
        try{
            QuestionBank oneByQuestionBankId = questionBankRepo.findOneByQuestionBankId(questionBankId);
            return oneByQuestionBankId;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public QuestionBank findOneByQuestionBankIdAndVisibility(String questionBankId,String visibility){
        try{
            QuestionBank OneByQuestionBankIdAndVisibility = questionBankRepo.findOneByQuestionBankIdAndVisibility(questionBankId,visibility);
            return OneByQuestionBankIdAndVisibility;
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

    @Override
    public List<QuestionBank> findAllByOwnerId(String ownerId){
        try{
            List<QuestionBank> allByOwnerId = questionBankRepo.findAllByOwnerId(ownerId);
            return allByOwnerId;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<QuestionBank> findAllByOwnerIdAndVisibility(String ownerId){
        try{
            List<QuestionBank> allByOwnerIdAndVisibility = questionBankRepo.findAllByOwnerIdAndVisibility(ownerId,"公开");
            return allByOwnerIdAndVisibility;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
