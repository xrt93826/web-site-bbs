package com.itany.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.entity.User;
import com.itany.bbs.service.UserService;
import com.util.DefaultVariables;

/**
 * 
 * 用于处理登录请求
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月19日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Controller
public class LoginController
{
    @Autowired
    private UserService service;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String process(
        @RequestParam(value = "loginname", required = true) String loginname, 
        @RequestParam(value = "loginpwd", required = true) String loginpwd,
        @RequestParam(value = "vcode", required = true) String vcode, 
        HttpSession session, HttpServletRequest request)
    { 
        //判断验证码是否正确
        if(!vcode.equalsIgnoreCase((String)session.getAttribute("code"))){
            request.setAttribute(DefaultVariables.ERRORMESSAGE, "验证码不正确");
            return "errorreport";
          //验证用户信息  
        }else if(service.verifyLoginInfo(loginname, loginpwd) == null){
            request.setAttribute(DefaultVariables.ERRORMESSAGE,"用户名已存在或者密码错误");
            return "errorreport";
          //登录成功，将用户信息放入session中
        }else{
            User user = service.verifyLoginInfo(loginname, loginpwd);
            if(user.getState()<1){
                
                request.setAttribute(DefaultVariables.ERRORMESSAGE,"您无权登录");
                return "errorreport";
            }
            else{
                session.setAttribute(DefaultVariables.USERINFO, user);
            }
        }
        //获取发起请求的页面完整路径,并重定向到该路径
        String url = request.getHeader("referer");
        return "redirect:"+url;
    }
}
