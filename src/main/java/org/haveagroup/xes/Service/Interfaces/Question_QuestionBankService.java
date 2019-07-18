package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Question_QuestionBank;

import java.util.List;

public interface Question_QuestionBankService {
    void addQuestionToQuestionBank(String questionId,String questionBankId);
//    Question_QuestionBank findByQuestion_QuestionBankId(String question_questionBankId);
//    List<String> findByQuestionBankId(String questionBankId);
}
