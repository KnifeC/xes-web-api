package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.ExaminationRepo;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    ExaminationRepo examinationRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public Examination createExamination(String examinationName,String creatorId){
        try{
            Examination examination = new Examination();
            examination.setExaminationName(examinationName);
            examination.setCreatorId(creatorId);
            examinationRepo.save(examination);
            return examination;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExamination(String examinationId){
        try{
            examinationRepo.deleteById(examinationId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Examination findOneByExaminationId(String examinationId){
        try{
            Examination OneByExaminationId = examinationRepo.findOneByExaminationId(examinationId);
            return OneByExaminationId;
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
