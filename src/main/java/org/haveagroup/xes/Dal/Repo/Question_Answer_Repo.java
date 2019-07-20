package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Question_Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Question_Answer_Repo extends JpaRepository<Question_Answer,String> {
    Question_Answer findByExaminationIdAndQuestionIdAndUserId(String examinationId,String questionId,String userId);
    Question_Answer findByPaperIdAndQuestionIdAndUserId(String paperId,String questionId,String userId);
    List<Question_Answer> findByPaperIdAndUserId(String paperId,String userId);
    List<Question_Answer> findByExaminationIdAndUserId(String examinationId,String userId);
    //Question_Answer findByQuestion_answer_id(String question_answer_id);
}
