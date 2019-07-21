package org.haveagroup.xes.Service.InterfaceImpl;


import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.haveagroup.xes.Service.Interfaces.UserService;
import org.haveagroup.xes.Util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.haveagroup.xes.Util.CryptoUtil.DEFAULT_SECRET_KEY;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CryptoUtil cryptoUtil;


    @Override
    public boolean isEmailUsed(String email){
        if(userRepo.findByEmail(email)!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean register(String email,String username,String password){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try{
//            String userPassword = bCryptPasswordEncoder.encode(password);
            //password = cryptoUtil.encode(DEFAULT_SECRET_KEY,password);
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
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
        //TODO 加密
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try {
            //password = cryptoUtil.encode(DEFAULT_SECRET_KEY,password);
            System.out.println(email +"    "+password);
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

    @Override
    public User editInfo(String userId,String username,String password){
        try{
            User user = userRepo.findByUserId(userId);
            user.setUsername(username);
            user.setPassword(password);
            userRepo.save(user);
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean changeType(String userId,String type){
        try{
            User user = userRepo.findByUserId(userId);
            user.setType(type);
            userRepo.save(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editUsername(String userId,String username){
        try{
            User user = userRepo.findByUserId(userId);
            user.setUsername(username);
            userRepo.save(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editPassword(String userId,String password){
        try{
            //password = cryptoUtil.encode(DEFAULT_SECRET_KEY,password);
            User user = userRepo.findByUserId(userId);
            user.setPassword(password);
            userRepo.save(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findByUserId(String userId){
        try{
            return userRepo.findByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByEmail(String email){
        try{
            return userRepo.findByEmail(email);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
