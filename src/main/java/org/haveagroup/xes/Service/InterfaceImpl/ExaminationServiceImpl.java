package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Dal.Model.Examination_User;
import org.haveagroup.xes.Dal.Model.Paper;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.ExaminationRepo;
import org.haveagroup.xes.Dal.Repo.Examination_User_Repo;
import org.haveagroup.xes.Dal.Repo.PaperRepo;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.haveagroup.xes.Service.Interfaces.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    ExaminationRepo examinationRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    Examination_User_Repo examination_user_repo;
    @Autowired
    PaperRepo paperRepo;

    @Override
    public Examination createExamination(String examinationName, String creatorId, String beginTime, String endTime,String durationTime){
        try{
            Examination examination = new Examination();
            examination.setExaminationName(examinationName);
            examination.setCreatorId(creatorId);
            examination.setBeginTime(beginTime);
            examination.setEndTime(endTime);
            examination.setDurationTime(durationTime);
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
            List<Examination_User> examination_users = examination_user_repo.findAllByExaminationId(examinationId);
            for(Examination_User examination_user : examination_users){
                examination_user_repo.deleteById(examination_user.getExamination_user_id());
            }
            List<Paper> papers = paperRepo.findAllByExaminationId(examinationId);
            for(Paper paper : papers){
                paperRepo.deleteById(paper.getPaperId());
            }


            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Examination findOneByExaminationId(String examinationId){
        try{
            Examination oneByExaminationId = examinationRepo.findByExaminationId(examinationId);
            return oneByExaminationId;
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

    @Override
    public List<Examination> findAllByCreator(String creatorId){
        try{
            List<Examination> allByCreatorId = examinationRepo.findAllByCreatorId(creatorId);
            return allByCreatorId;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean editExaminationName(String examinationId,String examinationName){
        try{
            Examination examination = examinationRepo.findByExaminationId(examinationId);
            examination.setExaminationName(examinationName);
            examinationRepo.save(examination);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean editExaminationBeginTime(String examinationId,String beginTime){
        try{
            Examination examination = examinationRepo.findByExaminationId(examinationId);
            examination.setBeginTime(beginTime);
            examinationRepo.save(examination);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean editExaminationEndTime(String examinationId,String endTime){
        try{
            Examination examination = examinationRepo.findByExaminationId(examinationId);
            examination.setEndTime(endTime);
            examinationRepo.save(examination);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean editExaminationDurationTime(String examinationId,String durationTime){
        try{
            Examination examination = examinationRepo.findByExaminationId(examinationId);
            examination.setDurationTime(durationTime);
            examinationRepo.save(examination);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
