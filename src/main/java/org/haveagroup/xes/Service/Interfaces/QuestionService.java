package org.haveagroup.xes.Service.Interfaces;


import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public interface QuestionService {

    Question findByQuestionId(String questionId);
    Question uploadQuestion(String questionContent,String questionAnswer,String questionType,String uploaderId);
    List<Question> findAllByQuestionContentLike(String questionContent);
    boolean deleteQuestion(String questionId);

    List<Question> findAllByUploaderId(String uploaderId);



//    List<Question> findCacheByQuestionContentLike(String questionContent);
}
