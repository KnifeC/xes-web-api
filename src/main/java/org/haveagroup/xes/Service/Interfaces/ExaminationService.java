package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Examination;

import java.util.List;

public interface ExaminationService {

    Examination createExamination(String examinationName,String creatorId);
    boolean deleteExamination(String examinationId);
    Examination findOneByExaminationId(String examinationId);
    List<Examination> findAllByExaminationNameLike(String examinationName);
    List<Examination> findAllByCreator(String creatorId);






}