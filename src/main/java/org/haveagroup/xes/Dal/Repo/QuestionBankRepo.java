package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionBankRepo extends JpaRepository<QuestionBank,String> , JpaSpecificationExecutor<QuestionBank> {


//    @Query("select questionBank from QuestionBank questionBank where " +
//            "questionBank.questionBankId=:questionBankId and " +
//            "questionBank.visibility=:visibility")
//    QuestionBank findOneByQuestionBankIdAndVisibility(@Param("questionBankId") String questionBankId,
//                                         @Param("visibility") String visibility);

    QuestionBank findOneByQuestionBankId(String questionBankId);
    QuestionBank findOneByQuestionBankIdAndVisibility(String questionBankId,String visibility);
    QuestionBank findOneByQuestionBankIdAndAndOwnerIdAndVisibility(String questionBankId,String ownerId,String visibility);
    QuestionBank findOneByQuestionBankIdAndOwnerId(String questionBankId,String ownerId);

//    @Query("select questionBank from QuestionBank questionBank where " +
//            "questionBank.questionBankName like concat('%',:questionBankName,'%') and " +
//            "questionBank.visibility=:visibility")
//    List<QuestionBank> findAllByQuestionBankNameLike(@Param("questionBankName") String questionBankName,
//                                         @Param("visibility") String visibility);
    List<QuestionBank> findAllByQuestionBankNameLike(String questionBankName);

    List<QuestionBank> findAllByOwnerId(String ownerId);

    List<QuestionBank> findAllByOwnerIdAndVisibility(String ownerId,String visibility);



}
