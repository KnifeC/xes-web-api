package org.haveagroup.xes.Service.Interfaces;


import org.haveagroup.xes.Dal.Model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAllByQuestionContentLike(String questionContent);

    Question findByQuestionId(String questionId);

    boolean addQuestionToBank(String QuestionId);
}
