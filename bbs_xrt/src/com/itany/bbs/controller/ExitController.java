package com.itany.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * 用于处理退出请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class ExitController
{
    @RequestMapping(value = "/exit")
    public String process(HttpSession session, HttpServletRequest request)
    { 
        if(null != session){
            session.invalidate();
        }
      //获取发起请求的页面完整路径,并重定向到该路径
        String url = request.getHeader("referer");
        return "redirect:"+url;
    }
}
