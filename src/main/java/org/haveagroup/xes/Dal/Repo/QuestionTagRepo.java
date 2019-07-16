package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Question_Tag;
import org.haveagroup.xes.Dal.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionTagRepo extends JpaRepository<Question_Tag,String> {
    List<Question> findAllByTag(String tag);
}
