package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Paper_Question;
import org.haveagroup.xes.Dal.Repo.PaperQuestionRepo;
import org.haveagroup.xes.Service.Interfaces.PaperQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperQuestionServiceImpl implements PaperQuestionService {
    @Autowired
    PaperQuestionRepo paperQuestionRepo;

    @Override
    public List<String> findQuestionIdByPaperId(String paperId){
        List<Paper_Question> paper_question_list = paperQuestionRepo.findQuestionIdByPaperId(paperId);
        List<String> questionIds = new ArrayList<>();
        for(Paper_Question p_q : paper_question_list){
            questionIds.add(p_q.getQuestionId());
        }
        return questionIds;
    }

    @Override
    public List<String> findPaperIdByQuestionId(String questionId){
        List<Paper_Question> paper_question_list = paperQuestionRepo.findPaperIdByQuestionId(questionId);
        List<String> paperIds = new ArrayList<>();
        for(Paper_Question p_q : paper_question_list){
            paperIds.add(p_q.getPaperId());
        }
        return paperIds;
    }

    @Override
    public boolean addQuestionToPaper(String paperId,String questionId){
        try{
            Paper_Question paper_question = new Paper_Question();
            paper_question.setPaperId(paperId);
            paper_question.setQuestionId(questionId);
            paperQuestionRepo.save(paper_question);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isQuestionUsed(String paperId,String questionId){
        try{
            Paper_Question paper_question = paperQuestionRepo.findOneByPaperIdAndQuestionId(paperId,questionId);
            if(paper_question!=null){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
