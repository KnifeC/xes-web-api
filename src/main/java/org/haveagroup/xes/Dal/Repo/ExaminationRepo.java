package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationRepo extends JpaRepository<Examination,String> {
    Examination findOneByExaminationId(String examinationId);
    List<Examination> findAllByExaminationNameLike(String examinationName);

}
