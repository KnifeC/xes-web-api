package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.haveagroup.xes.Dal.Model.QuestionBank_Question;

import java.util.List;

public interface QuestionBank_Question_Service {
    QuestionBank_Question findByQuestionBankIdAndQuestionId(String questionBankId, String questionId);
    List<QuestionBank> findAllQuestionBankByQuestionId(String questionId);
    List<Question> findAllQuestionByQuestionBankId(String questionBankId);
    QuestionBank_Question addQuestionToQuestionBank(String questionId,String questionBankId);
    boolean deleteQuestionFromQuestionBank(String questionId,String questionBankId);
}
