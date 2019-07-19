package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.QuestionBank_Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBank_Question_Repo extends JpaRepository<QuestionBank_Question,String> {
    QuestionBank_Question findByQuestionBankIdAndQuestionId(String questionBankId, String questionId);
    List<QuestionBank_Question> findAllQuestionBankIdByQuestionId(String questionId);
    List<QuestionBank_Question> findAllQuestionIdByQuestionBankId(String questionBankId);
}
