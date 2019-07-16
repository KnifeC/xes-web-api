package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Repo.QuestionRepo;
import org.haveagroup.xes.Service.Interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;

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
            Question question = questionRepo.findByQuestionId(questionId);
            return question;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
