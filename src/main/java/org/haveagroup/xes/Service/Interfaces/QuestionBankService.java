package org.haveagroup.xes.Service.Interfaces;


import org.haveagroup.xes.Dal.Model.QuestionBank;

import java.util.List;

public interface QuestionBankService {
    QuestionBank createQuestionBank(String questionBankName);
    boolean deleteQuestionBank(String questionBankId);
    QuestionBank findOneByQuestionBankId(String questionBankId);
    List<QuestionBank> findAllByQuestionBankNameLike(String questionBankName);


}
