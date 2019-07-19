package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepo extends JpaRepository<Paper,String> {
    Paper findByPaperId(String paperId);
}
