package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Paper_Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperQuestionRepo extends JpaRepository<Paper_Question,String> {
    List<Paper_Question> findQuestionIdByPaperId(String paperId);
    List<Paper_Question> findPaperIdByQuestionId(String QuestionId);
}
