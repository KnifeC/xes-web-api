package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Paper;

import java.util.List;

public interface PaperService {
    Paper createPaper(String examinationId,String paperName);
    boolean deletePaper(String paperId);

    Paper findByPaperId(String paperId);

    String findExaminationIdByPaperId(String paperId);

    List<Paper> findAllByExaminationId(String examinationId);




}
