package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,String> {
    
}
