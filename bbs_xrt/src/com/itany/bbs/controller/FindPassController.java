package com.itany.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.service.UserService;
import com.util.DefaultVariables;


@Controller
public class FindPassController
{
    
    @Autowired
    UserService service;
    
    @RequestMapping(value = "/findpwd" , method = RequestMethod.POST)
    public String process(
        @RequestParam(value = "loginname", required = true) String loginname,
        @RequestParam(value = "email", required = true) String email,
        HttpSession session, HttpServletRequest request)
    { 
        boolean state = service.findLoginPwd(loginname, email);
        
        if(state == false){
            request.setAttribute(DefaultVariables.ERRORMESSAGE,"找回失败");
            return "errorreport";
        }
        
        
        return "redirect:emailsuccess.html";
    }
}
