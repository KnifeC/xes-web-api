package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,String> {

    List<Question> findAllByQuestionContentLike(String questionContent);
    Question findByQuestionId(String questionId);

    //@Query(value = "select questionId,questionContent from Question q where q.questionContent like CONCAT('%',:questionContent,'%')")
    //List<Question> findByQuestionContentLike(@Param("questionContent") String questionContent);

    //List<Question> findByTag

}
