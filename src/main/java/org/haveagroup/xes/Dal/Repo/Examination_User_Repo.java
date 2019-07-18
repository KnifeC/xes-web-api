package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Examination_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Examination_User_Repo extends JpaRepository<Examination_User,String> {
    Examination_User findByUserIdAndExaminationId(String userId,String examinationId);
    List<Examination_User> findAllByUserId(String userId);
    List<Examination_User> findAllByExaminationId(String ExaminationId);
}
