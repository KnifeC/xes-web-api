package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Repo.ExaminationRepo;
import org.haveagroup.xes.Dal.Repo.PaperRepo;
import org.haveagroup.xes.Service.Interfaces.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperRepo paperRepo;
    @Autowired
    ExaminationRepo examinationRepo;

    @Override
    public Paper createPaper(String examinationId, String paperName){
        try{
            Paper paper = new Paper();
            paper.setExaminationId(examinationId);
            paper.setExaminationName(examinationRepo.findByExaminationId(examinationId).getExaminationName());
            paper.setPaperName(paperName);
            paperRepo.save(paper);
            return paper;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Paper> findAllByExaminationId(String examinationId){
        try{
            List<Paper> papers = paperRepo.findAllByExaminationId(examinationId);
            return papers;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean deletePaper(String paperId){
        try{
            if(paperRepo.findByPaperId(paperId)==null){
                return false;
            }
            paperRepo.deleteById(paperId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String findExaminationIdByPaperId(String paperId){
        try{
            return paperRepo.findByPaperId(paperId).getExaminationId();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Paper findByPaperId(String paperId){
        try{
            return paperRepo.findByPaperId(paperId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}