package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Paper_Question_Cache;
import org.haveagroup.xes.Dal.Repo.PaperQuestionCacheRepo;
import org.haveagroup.xes.Service.Interfaces.PaperQuestionCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "paper_question_cache")
public class PaperQuestionCacheServiceImpl implements PaperQuestionCacheService {

    @Autowired
    PaperQuestionCacheRepo paperQuestionCacheRepo;

    @Override
    public boolean addQuestionToPaper(String paperId,String questionId){
        try{
            Paper_Question_Cache paper_question_cache = new Paper_Question_Cache();
            paper_question_cache.setPaperCacheId(paperId);
            paper_question_cache.setQuestionCacheId(questionId);
            paperQuestionCacheRepo.save(paper_question_cache);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @CachePut()
    public boolean addQuestionToPaperCache(String paperId,String questionId){
        Paper_Question_Cache paper_question_cache = new Paper_Question_Cache();

        return true;
    }
}
