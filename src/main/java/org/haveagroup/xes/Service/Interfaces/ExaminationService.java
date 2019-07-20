package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Examination;

import java.util.Date;
import java.util.List;

public interface ExaminationService {

    Examination createExamination(String examinationName, String creatorId, String beginTime,String endTime);
    boolean deleteExamination(String examinationId);
    Examination findOneByExaminationId(String examinationId);
    List<Examination> findAllByExaminationNameLike(String examinationName);
    List<Examination> findAllByCreator(String creatorId);
    boolean editExaminationName(String examinationId,String examinationName);
    boolean editExaminationBeginTime(String examinationId,String beginTime);
    boolean editExaminationEndTime(String examinationId,String endTime);

}