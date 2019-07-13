package org.haveagroup.xes.Controller;


import org.haveagroup.xes.Controller.Forms.LoginForm;
import org.haveagroup.xes.Controller.Forms.RegisterForm;
import org.haveagroup.xes.Controller.ResponseJson.StatusJson;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Dal.Repo.UserRepo;
import org.haveagroup.xes.Service.UserService;
import org.haveagroup.xes.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @RequestMapping(value="userLogin",method= RequestMethod.POST)
    @ResponseBody
    public String userLogin(LoginForm loginForm, HttpServletResponse response){
        User user=userService.login(loginForm.getEmail(),loginForm.getPassword());
        Cookie cookie=new Cookie("Id",user.getUserId());
        Cookie cookie2=new Cookie("Name",user.getUsername());
        response.addCookie(cookie);
        response.addCookie(cookie2);
        return user.getUsername();
    }

    @RequestMapping(value="userRegister",method=RequestMethod.POST)
    @ResponseBody
    public StatusJson userRegister(RegisterForm registerForm, ModelMap modelMap){
        if(userService.isEmailUsed(registerForm.getEmail())){
            return null;
        }else if(!StringUtil.isEquals(registerForm.getPassword(),registerForm.getRe_password())){
            return null;
        }else{
            userService.register(registerForm.getEmail(),registerForm.getUsername(), registerForm.getPassword());
        }
        return new StatusJson("OK",registerForm.getUsername());
    }

}
