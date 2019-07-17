package org.haveagroup.xes.Service.Interfaces;

public interface PaperQuestionCacheService {
    boolean addQuestionToPaper(String paperId,String questionId);
    boolean addQuestionToPaperCache(String paperId, String questionId);
}
