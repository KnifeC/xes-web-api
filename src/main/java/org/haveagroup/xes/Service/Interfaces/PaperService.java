package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Paper;

public interface PaperService {
    Paper createPaper(String examinationId,String paperName);
    boolean deletePaper(String paperId);

    Paper findByPaperId(String paperId);

    String findExaminationIdByPaperId(String paperId);




}
