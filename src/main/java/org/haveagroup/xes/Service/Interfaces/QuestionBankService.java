package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.QuestionBank;
import java.util.List;

public interface QuestionBankService {
    QuestionBank createQuestionBank(String questionBankName,String ownerId);
    boolean deleteQuestionBank(String questionBankId,String ownerId);
    //QuestionBank findOneByQuestionBankId(String questionBankId);
    //List<QuestionBank> findAllByQuestionBankNameLike(String questionBankName);


}
