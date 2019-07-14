package org.haveagroup.xes.Service;


import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public void register(String email,String username,String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userPassword = bCryptPasswordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(userPassword);
        //user.setRegTime(new Date());
        userRepo.save(user);
    }




    @Override
    public User login(String email,String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try {
            User user = userRepo.findByEmail(email);
            if (!bCryptPasswordEncoder.matches(password,user.getPassword())){
                return null;
            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
