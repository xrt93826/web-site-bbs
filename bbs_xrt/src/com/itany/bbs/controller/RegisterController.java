package com.itany.bbs.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.service.UserService;
import com.util.DefaultVariables;

/**
 * 
 * 用于处理注册请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class RegisterController
{
    @Autowired
    private UserService service;
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String process(
        @RequestParam(value = "regname", required = true) String regname, 
        @RequestParam(value = "regpwd", required = true) String regpwd,
        @RequestParam(value = "regpwdr", required = true) String regpwdr,
        @RequestParam(value = "email", required = true) String email,
        @RequestParam(value = "head", required = true) String head,
        HttpSession session, HttpServletRequest request)
    {   
        service.doRegister(regname, regpwd, regpwdr, email, head);   
        return "redirect:regsuccess.html";
    }
    
    
    @RequestMapping(value = "/checkName", method = RequestMethod.GET)
    public void process2(
        @RequestParam(value = "regname", required = false) String regname, 
        HttpServletResponse response, HttpServletRequest request)
    {   
        String ret="false";
        try
        {
            if(regname.trim()!=null){
                regname=URLDecoder.decode(regname, "UTF-8");
                if(service.validateUserName(regname)!=0){
                    ret="true";
                }
            }
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(ret);
            out.flush();
            out.close();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    public void process3(
        @RequestParam(value = "email", required = false) String email, 
        HttpServletResponse response, HttpServletRequest request)
    {   
        String ret="false";
        try
        {
            if(email.trim()!=null){
                if(service.validateEmail(email)==0){
                    ret="true";
                }
            }
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(ret);
            out.flush();
            out.close();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
