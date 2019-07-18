package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Dal.Model.Examination_User;
import org.haveagroup.xes.Dal.Model.User;

import java.util.List;

public interface Examination_User_Service {
    Examination_User findByUserIdAndExaminationId(String userId, String examinationId);
    List<Examination> findAllExaminationByUserId(String userId);
    List<User> findAllUserByExaminationId(String ExaminationId);
    Examination_User addUserToExamination(String userId,String examinationId);
}
