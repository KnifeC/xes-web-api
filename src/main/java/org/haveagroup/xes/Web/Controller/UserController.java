package org.haveagroup.xes.Web.Controller;

import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.User;
import org.haveagroup.xes.Service.Interfaces.UserService;
import org.haveagroup.xes.Util.StringUtil;
import org.haveagroup.xes.Web.Forms.LoginForm;
import org.haveagroup.xes.Web.Forms.EditForm;
import org.haveagroup.xes.Web.Forms.RegisterForm;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.haveagroup.xes.Web.ResponseJson.UserDataJson;
import org.haveagroup.xes.Web.ResponseJson.UserJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    //@RequestBody Map<String, Object> payload,
    @PostMapping(value="webapi/login")
    public UserJson userLogin(LoginForm loginForm, HttpServletResponse response, HttpSession session){
        logger.info("看这里啊！！！！！！！&&&"+loginForm.getEmail()+"&&&"+loginForm.getPassword());
        User user=userService.login(loginForm.getEmail(),loginForm.getPassword());
        if(user == null){
            return new UserJson(new StatusJson(Status.ERROR,"登陆失败","THIS"),new UserDataJson());
        }
        String cookieValue = user.getEmail()+"&&&"+loginForm.getPassword();
        Cookie cookie=new Cookie("token",cookieValue);
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        session.setAttribute(SessionKey.USER_ID,user.getUserId());
        session.setAttribute(SessionKey.USER_TYPE,user.getType());
        session.setAttribute(SessionKey.USER_NAME,user.getUsername());
        session.setAttribute(SessionKey.USER_EMAIL,user.getEmail());
        return new UserJson(new StatusJson(Status.SUCCESS,"登录成功","index"),new UserDataJson(user));
    }

    @PostMapping(value="webapi/register")
    public StatusJson userRegister(RegisterForm registerForm ){
        if(userService.isEmailUsed(registerForm.getEmail())){
            return new StatusJson(Status.ERROR,"该邮箱已被注册","THIS");
        }
        if(!StringUtil.isEquals(registerForm.getPassword(),registerForm.getRe_password())){
            return new StatusJson(Status.ERROR,"两次输入的密码不一致","THIS");
        }
        boolean register = userService.register(registerForm.getEmail(), registerForm.getUsername(), registerForm.getPassword());
        if(!register) {
            return new StatusJson(Status.ERROR,"注册失败","THIS");
        }
        return new StatusJson(Status.SUCCESS,"注册成功","THIS");
    }

    @GetMapping(value="webapi/logout")
    public StatusJson logout(HttpSession session){
        session.invalidate();
        return new StatusJson(Status.SUCCESS,"登出成功","THIS");
    }

    @PostMapping(value="webapi/editInfo")
    public UserJson editInfo(EditForm editForm,HttpSession session){

        User user = userService.editInfo(editForm.getUserId(),editForm.getUsername(),editForm.getPassword());

        if(user == null){
            return new UserJson(new StatusJson(Status.ERROR,"修改失败","THIS"),new UserDataJson(user));
        }else{
            session.setAttribute(SessionKey.USER_ID,user.getUserId());
            session.setAttribute(SessionKey.USER_TYPE,user.getType());
            session.setAttribute(SessionKey.USER_NAME,user.getUsername());
            session.setAttribute(SessionKey.USER_EMAIL,user.getEmail());
            return new UserJson(new StatusJson(Status.SUCCESS,"修改成功","THIS"),new UserDataJson(user));
        }
    }

    @PostMapping(value="webapi/changeType")
    public StatusJson changeType(String userId,EditForm editForm){
        if(userService.changeType(userId,editForm.getType())){
            return new StatusJson(Status.SUCCESS,"修改成功","THIS");
        }else{
            return new StatusJson(Status.ERROR,"修改失败","THIS");
        }
    }

    @PostMapping("webapi/checkemail")
    public StatusJson checkEmail(String email){
        if(userService.isEmailUsed(email)){
            return new StatusJson(Status.ERROR,"邮箱已存在","THIS");
        }
        return new StatusJson(Status.SUCCESS,"邮箱可用","THIS");
    }

    @GetMapping("webapi/gettoken")
    public UserJson getUserInfo(HttpSession session){
        try {
            String userId = session.getAttribute(SessionKey.USER_ID).toString();
            String userType = session.getAttribute(SessionKey.USER_TYPE).toString();
            String userName = session.getAttribute(SessionKey.USER_NAME).toString();
            String userEmail = session.getAttribute(SessionKey.USER_EMAIL).toString();
            logger.info("USERNAME:"+userName);
            return new UserJson(new StatusJson(Status.SUCCESS,"",""),new UserDataJson(userId,userName,userEmail,userType));
        }catch(Exception e){
            //TODO:验证cookie
            logger.error("你还未登录呢");
            return new UserJson(new StatusJson(Status.ERROR,"",""),new UserDataJson());
        }
    }


}
