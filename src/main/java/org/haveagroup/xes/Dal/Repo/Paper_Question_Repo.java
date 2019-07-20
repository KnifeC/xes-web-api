package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Paper_Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Paper_Question_Repo extends JpaRepository<Paper_Question,String>{
    Paper_Question findByPaperIdAndQuestionId(String paperId,String QuestionId);
    List<Paper_Question> findAllQuestionIdByPaperId(String paperId);
    List<Paper_Question> findAllPaperIdByQuestionId(String Question);
}
