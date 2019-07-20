package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Question_Answer;

import java.util.List;

public interface Question_Answer_Service {
    List<Question_Answer> findAnswers(String paperId, String userId);
    //List<Question_Answer> findAnswers(String examinationId, String userId);
    boolean answerQuestion(String examinationId,String paperId, String questionId, String userId,String answer);
    boolean editQuestion(String examinationId,String paperId, String questionId, String userId,String answer);
}
