package org.haveagroup.xes.Service;


import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public boolean isEmailUsed(String email){
        if(userRepo.findByEmail(email)!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void register(String email,String username,String password){
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        //user.setRegTime(new Date());
        userRepo.save(user);
    }




    @Override
    public User login(String email,String password){
        try {
            User user = userRepo.findByEmailAndPassword(email, password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
