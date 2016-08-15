package com.itany.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.entity.User;
import com.itany.bbs.service.TopicService;
import com.util.DefaultVariables;

/**
 * 
 * 处理发帖提交请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class AddTopicController
{
    @Autowired
    TopicService service;
    
    @RequestMapping(value = "/addtopic", method = RequestMethod.POST)
    public String process(
        @RequestParam(value = "boardId", required = true) Integer boardId,
        @RequestParam(value = "title", required = true) String title,
        @RequestParam(value = "tcontents", required = true) String tcontents,
        HttpSession session, HttpServletRequest request)
    {   
        
        User user = (User)session.getAttribute(DefaultVariables.USERINFO);
        
        Integer state = service.addTopic(boardId, title, tcontents, user.getUserid());
        
        if(state == 0){
            request.setAttribute(DefaultVariables.ERRORMESSAGE, "发帖失败");
            return "errorreport";
        }
        return "redirect:board?boardid="+boardId;
    }
}
