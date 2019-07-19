package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Repo.PaperRepo;
import org.haveagroup.xes.Service.Interfaces.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperRepo paperRepo;

    @Override
    public Paper createPaper(String examinationId, String examinationName){
        try{
            Paper paper = new Paper();
            paper.setExaminationId(examinationId);
            paper.setExaminationName(examinationName);
            paperRepo.save(paper);
            return paper;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }


}
