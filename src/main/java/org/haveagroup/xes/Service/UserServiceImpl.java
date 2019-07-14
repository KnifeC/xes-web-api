package org.haveagroup.xes.Service;


import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public boolean changeType(User user,String type){
        try{
            user.setType(type);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean register(String email,String username,String password){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try{
//            String userPassword = bCryptPasswordEncoder.encode(password);
            String userPassword = password;
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(userPassword);
            //user.setRegTime(new Date());
            userRepo.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }




    @Override
    public User login(String email,String password){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try {
            User user = userRepo.findByEmailAndPassword(email,password);
//            if (!bCryptPasswordEncoder.matches(password,user.getPassword())){
//                return null;
//            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
