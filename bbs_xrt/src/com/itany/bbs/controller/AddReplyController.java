package com.itany.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.entity.User;
import com.itany.bbs.service.ReplyService;

import com.util.DefaultVariables;

/**
 * 
 * 处理发回复提交请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class AddReplyController
{
    @Autowired
    ReplyService service;
    
    @RequestMapping(value = "/addreply", method = RequestMethod.POST)
    public String process(
        @RequestParam(value = "topicId", required = true) Integer topicId,
        @RequestParam(value = "boardId", required = true) Integer boardId,
        @RequestParam(value = "rtitle", required = true) String rtitle,
        @RequestParam(value = "rcontents", required = true) String rcontents, 
        HttpSession session, HttpServletRequest request)
    {
        
        User user = (User)session.getAttribute(DefaultVariables.USERINFO);
        
        Integer state = service.addReply(topicId, rtitle, rcontents, user.getUserid());
        if(state == 0){
            request.setAttribute(DefaultVariables.ERRORMESSAGE, "回帖失败");
            return "errorreport";
        }
        return "redirect:topic?boardid=" + boardId +"&topicid="+topicId;
    }
}
