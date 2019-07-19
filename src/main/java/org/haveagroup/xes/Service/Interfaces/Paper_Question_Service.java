package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Model.Paper_Question;
import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public interface Paper_Question_Service {
    Paper_Question findByPaperIdAndQuestionId(String paperId, String questionId);
    List<Paper> findAllPaperByQuestionId(String questionId);
    List<Question> findAllQuestionByPaperId(String paperId);
    Paper_Question addQuestionToPaper(String questionId,String paperId);
    boolean deleteQuestionFromPaper(String questionId,String paperId);
    boolean isQuestionUsed(String paperId,String questionId);
}
