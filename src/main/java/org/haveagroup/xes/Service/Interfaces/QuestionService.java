package org.haveagroup.xes.Service.Interfaces;


import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public interface QuestionService {

    Question uploadQuestion(String questionContent,String questionAnswer,String questionType,String uploaderId);

    List<Question> findAllByQuestionContentLike(String questionContent);

//    List<Question> findCacheByQuestionContentLike(String questionContent);

    Question findByQuestionId(String questionId);

    Question findCacheByQuestionId(String questionId);

    //boolean addQuestionToBank(String QuestionId);
}
