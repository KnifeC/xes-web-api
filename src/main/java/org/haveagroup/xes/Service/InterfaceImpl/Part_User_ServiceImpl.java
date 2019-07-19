package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Part;
import org.haveagroup.xes.Dal.Model.Part_User;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.PartRepo;
import org.haveagroup.xes.Dal.Repo.Part_User_Repo;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.haveagroup.xes.Service.Interfaces.Part_User_Service;
import org.springframework.beans.factory.annotation.Autowired;

public class Part_User_ServiceImpl implements Part_User_Service {

    @Autowired
    PartRepo partRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    Part_User_Repo part_user_repo;

    @Override
    public Part_User addUserToPart(String userId, String partId){
        try{
            Part_User p_u = new Part_User();
            p_u.setPartId(partId);
            p_u.setUserId(userId);
            part_user_repo.save(p_u);
            return p_u;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUserFromPart(String userId, String partId){
        try{
            User user = userRepo.findByUserId(userId);
            Part part = partRepo.findByPartId(partId);
            if(user==null||part==null){
                return false;
            }
            Part_User part_user = part_user_repo.findByUserIdAndPartId(userId,partId);
            part_user_repo.deleteById(part_user.getPart_user_id());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
