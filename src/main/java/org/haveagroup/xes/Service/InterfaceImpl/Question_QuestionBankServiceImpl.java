package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question_QuestionBank;
import org.haveagroup.xes.Service.Interfaces.Question_QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Question_QuestionBankServiceImpl implements Question_QuestionBankService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void addQuestionToQuestionBank(String questionBankId,String questionId){
        Question_QuestionBank question_questionbank = new Question_QuestionBank();
        question_questionbank.setQuestionBankId(questionBankId);
        question_questionbank.setQuestionId(questionId);
        mongoTemplate.save(question_questionbank);
    }

//    @Override
//    public Question_QuestionBank findByQuestion_QuestionBankId(String question_questionBankId){
//        Question_QuestionBank
//        return ;
//    }
//
//    @Override
//    public List<String> findByQuestionBankId(String questionBankId){
//        Query query = Query.query(Criteria.where("questionBankId").is(questionBankId));
//        List<String>  = mongoTemplate.find(query, Question_QuestionBank.class);
//    }
}
