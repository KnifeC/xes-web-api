package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBankRepo extends JpaRepository<QuestionBank,String> {

    QuestionBank findOneByQuestionBankId(String questionBankId);
    List<QuestionBank> findAllByQuestionBankNameLike(String questionBankName);

}
