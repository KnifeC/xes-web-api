package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Model.Paper_Question;
import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Repo.PaperRepo;
import org.haveagroup.xes.Dal.Repo.Paper_Question_Repo;
import org.haveagroup.xes.Dal.Repo.QuestionRepo;
import org.haveagroup.xes.Service.Interfaces.Paper_Question_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Paper_Question_ServiceImpl implements Paper_Question_Service {
    @Autowired
    Paper_Question_Repo paper_question_repo;
    @Autowired
    PaperRepo paperRepo;
    @Autowired
    QuestionRepo questionRepo;

    @Override
    public Paper_Question findByPaperIdAndQuestionId(String paperId, String questionId){
        try{
            return paper_question_repo.findByPaperIdAndAndQuestionId(paperId,questionId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Paper> findAllPaperByQuestionId(String questionId){
        try{
            List<Paper> papers = new ArrayList<>();
            List<Paper_Question> p_q = paper_question_repo.findAllPaperIdByQuestionId(questionId);
            for(Paper_Question paper_question : p_q){
                papers.add(paperRepo.findByPaperId(paper_question.getPaperId()));
            }
            return papers;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Question> findAllQuestionByPaperId(String paperId){
        try{
            List<Question> questions = new ArrayList<>();
            List<Paper_Question> p_q = paper_question_repo.findAllQuestionIdByPaperId(paperId);
            for(Paper_Question paper_question : p_q){
                questions.add(questionRepo.findByQuestionId(paper_question.getQuestionId()));
            }
            return questions;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Paper_Question addQuestionToPaper(String questionId,String paperId){
        try{
            Paper_Question paper_question = new Paper_Question();
            paper_question.setQuestionId(questionId);
            paper_question.setPaperId(paperId);
            paper_question_repo.save(paper_question);
            return paper_question;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteQuestionFromPaper(String questionId,String paperId){
        try{
            String deleteId = paper_question_repo.findByPaperIdAndAndQuestionId(paperId,questionId)
                    .getPaper_question_Id();
            paper_question_repo.deleteById(deleteId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isQuestionUsed(String paperId,String questionId){
        Paper_Question paper_question = paper_question_repo.findByPaperIdAndAndQuestionId(paperId,questionId);
        return paper_question!=null;
    }
}
