package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Question_Tag;
import org.haveagroup.xes.Dal.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Question_Tag_Repo extends JpaRepository<Question_Tag,String> {
    Question_Tag findByTagIdAndQuestionId(String tagId,String questionId);
    List<Question_Tag> findAllTagIdByQuestionId(String questionId);
    List<Question_Tag> findAllQuestionIdByTagId(String tagId);

}
