package org.haveagroup.xes.Config;

import org.haveagroup.xes.Commom.SessionKey;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PermissionIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //登录验证
        Object usernameObj = session.getAttribute(SessionKey.USER_NAME);
        Object userIdObj = session.getAttribute(SessionKey.USER_ID);
        Object userTypeObj = session.getAttribute(SessionKey.USER_TYPE);
        if(usernameObj==null || userIdObj==null || userTypeObj==null){
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
