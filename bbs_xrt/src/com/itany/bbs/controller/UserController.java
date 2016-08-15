package com.itany.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
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
 * 处理管理员页面的getUsers请求
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年8月2日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Controller
public class UserController
{
    @Autowired
    private UserService service;
    
    @RequestMapping(value = "/getUsers")
    public String getUsers(HttpServletRequest request, HttpServletResponse response, HttpSession session)
        throws IOException
    {
        User use = (User)session.getAttribute(DefaultVariables.USERINFO);
        if (use == null)
        {
            return null;
        }
        List<User> list = service.findUsersOrder(null, false, false, 0, 0);
        list.add(use);
        String str = "";
        if (list != null && list.size() > 0)
        {
            JSONArray arr = new JSONArray(list);
            str = arr.toString();
        }
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();
        return null;
    }
    
    @RequestMapping(value = "/modifyState")
    public String modifyState(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "state", required = false) Integer state, HttpServletRequest request,
        HttpServletResponse response, HttpSession session)
        throws Exception
    {
        String str = "false";
        User use = (User)session.getAttribute(DefaultVariables.USERINFO);
        if (!(id == null || state == null || use == null || use.getState() < 2) && id > 0 && state > 0 && state < 3)
        {
            if (service.modifyUserState(id, state))
            {
                str = "true";
            }
        }
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();
        return null;
    }
    
    @RequestMapping(value = "/deleteUser")
    public String deleteUser(
        @RequestParam(value = "id", required = false) Integer id,
        HttpServletRequest request,
        HttpServletResponse response, 
        HttpSession session)
        throws Exception
    {
        String str = "false";
        User use = (User)session.getAttribute(DefaultVariables.USERINFO);
        if (!(id == null || use == null || use.getState() < 2) && id > 0)
        {
            if (service.delUser(id))
            {
                str = "true";
            }
        }
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();
        return null;
    }
}
