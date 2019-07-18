package org.haveagroup.xes.Service.InterfaceImpl;


import org.haveagroup.xes.Dal.Model.Examination;
import org.haveagroup.xes.Dal.Model.Examination_User;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.ExaminationRepo;
import org.haveagroup.xes.Dal.Repo.Examination_User_Repo;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.haveagroup.xes.Service.Interfaces.Examination_User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Examination_User_ServiceImpl implements Examination_User_Service {
    @Autowired
    Examination_User_Repo examination_user_repo;
    @Autowired
    ExaminationRepo examinationRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public Examination_User findByUserIdAndExaminationId(String userId,String examinationId){
        try{
            Examination_User e_u = examination_user_repo.findByUserIdAndExaminationId(userId,examinationId);
            return e_u;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Examination> findAllExaminationByUserId(String userId){
        List<Examination> examinations = new ArrayList<>();
        List<Examination_User> e_u = examination_user_repo.findAllByUserId(userId);
        for(Examination_User examination_user : e_u){
            examinations.add(examinationRepo.findOneByExaminationId(examination_user.getExaminationId()));
        }
        return examinations;
    }

    @Override
    public List<User> findAllUserByExaminationId(String examinationId){
        List<User> users = new ArrayList<>();
        List<Examination_User> e_u = examination_user_repo.findAllByExaminationId(examinationId);
        for(Examination_User examination_user : e_u){
            users.add(userRepo.findByUserId(examination_user.getExaminationId()));
        }
        return users;
    }

    @Override
    public Examination_User addUserToExamination(String userId,String examinationId){
        try{
            Examination_User examination_user = new Examination_User();
            examination_user.setUserId(userId);
            examination_user.setExaminationId(examinationId);
            examination_user_repo.save(examination_user);
            return examination_user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
