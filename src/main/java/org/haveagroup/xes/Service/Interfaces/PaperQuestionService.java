package org.haveagroup.xes.Service.Interfaces;

import java.util.List;

public interface PaperQuestionService {
    List<String> findQuestionIdByPaperId(String paperId);
    List<String> findPaperIdByQuestionId(String questionId);
    boolean addQuestionToPaper(String paperId,String questionId);
    boolean isQuestionUsed(String paperId,String QuestionId);
}
