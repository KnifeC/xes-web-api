package org.haveagroup.xes.Web.Controller;


import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Web.Forms.LoginForm;
import org.haveagroup.xes.Web.Forms.RegisterForm;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.UserService;
import org.haveagroup.xes.Util.StringUtil;
import org.haveagroup.xes.Web.ResponseJson.UserDataJson;
import org.haveagroup.xes.Web.ResponseJson.UserJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value="webapi/login")
    public UserJson userLogin(LoginForm loginForm, HttpServletResponse response){
        User user=userService.login(loginForm.getEmail(),loginForm.getPassword());
//        String cookieValue = user.getEmail()+"&&&"+loginForm.getPassword();
//        Cookie cookie=new Cookie("userInfo",cookieValue);
//        cookie.setMaxAge(7*24*60*60);
//        response.addCookie(cookie);
        if(user == null){
            return new UserJson(new StatusJson(Status.ERROR,"登陆失败","THIS"),new UserDataJson("",""));
        }
        return new UserJson(new StatusJson(Status.SUCCESS,"登录成功","index"),new UserDataJson(user));
    }

    @PostMapping(value="webapi/register")
    public StatusJson userRegister(RegisterForm registerForm){
        if(!StringUtil.isEquals(registerForm.getPassword(),registerForm.getRe_password())){
            return new StatusJson(Status.ERROR,"两次输入的密码不一致","THIS");
        }
        boolean register = userService.register(registerForm.getEmail(), registerForm.getUsername(), registerForm.getPassword());
        if(register==false) {
            return new StatusJson(Status.ERROR,"注册失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"注册成功","THIS");
    }

    @PostMapping("webapi/checkemail")
    public StatusJson checkEmail(String email){
        if(userService.isEmailUsed(email)){
            return new StatusJson(Status.ERROR,"邮箱已存在","THIS");
        }
        return new StatusJson(Status.SUCCESS,"邮箱可用","THIS");
    }
}
