package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.QuestionBank;
import java.util.List;

public interface QuestionBankService {
    QuestionBank createQuestionBank(String questionBankName,String ownerId,String visibility);
    boolean deleteQuestionBank(String questionBankId,String ownerId);

    QuestionBank editQuestionBankName(String questionBankId,String questionBankName);
    QuestionBank editVisibility(String questionBankId,String visibility);

    QuestionBank findOneByQuestionBankId(String questionBankId);
    QuestionBank findOneByQuestionBankIdAndVisibility(String questionBankId,String visibility);
    //QuestionBank findOneByQuestionBankIdAnd

    List<QuestionBank> findAllByQuestionBankNameLike(String questionBankName);

    List<QuestionBank> findAllByOwnerId(String ownerId);
    List<QuestionBank> findAllByOwnerIdAndVisibility(String ownerId);
}
