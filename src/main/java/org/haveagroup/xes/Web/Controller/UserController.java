package org.haveagroup.xes.Web.Controller;


import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.UserService;
import org.haveagroup.xes.Util.StringUtil;
import org.haveagroup.xes.Web.Forms.LoginForm;
import org.haveagroup.xes.Web.Forms.RegisterForm;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.haveagroup.xes.Web.ResponseJson.UserDataJson;
import org.haveagroup.xes.Web.ResponseJson.UserJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    //@RequestBody Map<String, Object> payload,
    @PostMapping(value="webapi/login")
    public UserJson userLogin(LoginForm loginForm,  HttpServletResponse response, HttpSession session){
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("看这里啊！！！！！！！&&&"+loginForm.getEmail()+"&&&"+loginForm.getPassword());
//        logger.info("看这里啊22222222&&&"+payload.get("email")+"&&&"+payload.get("password"));

        User user=userService.login(loginForm.getEmail(),loginForm.getPassword());
        if(user == null){
            return new UserJson(new StatusJson(Status.ERROR,"登陆失败","THIS"),new UserDataJson("",""));
        }
        String cookieValue = user.getEmail()+"&&&"+loginForm.getPassword();
        Cookie cookie=new Cookie("userInfo",cookieValue);
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        session.setAttribute(SessionKey.USER_ID,user.getUserId());
        session.setAttribute(SessionKey.USER_TYPE,user.getType());
        session.setAttribute(SessionKey.USER_NAME,user.getUsername());
        return new UserJson(new StatusJson(Status.SUCCESS,"登录成功","index"),new UserDataJson(user));
    }

    @PostMapping(value="webapi/register")
    public StatusJson userRegister(RegisterForm registerForm, ModelMap modelMap){
        if(userService.isEmailUsed(registerForm.getEmail())){
            return new StatusJson(Status.ERROR,"该邮箱已被注册","THIS");
        }
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
