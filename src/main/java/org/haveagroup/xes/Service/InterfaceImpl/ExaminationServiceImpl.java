package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Dal.Repo.ExaminationRepo;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    ExaminationRepo examinationRepo;

    @Override
    public Examination createExamination(String examinationName){
        try{
            Examination examination = new Examination();
            examination.setExaminationName(examinationName);
            examinationRepo.save(examination);
            return examination;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Examination> findAllByExaminationNameLike(String examinationName){
        try{
            List<Examination> allByExaminationNameLike = examinationRepo.findAllByExaminationNameLike("%"+examinationName+"%");
            return allByExaminationNameLike;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
