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
import com.itany.bbs.service.TopicService;
import com.util.DefaultVariables;

/**
 * 
 * 处理删除帖子请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class DeleteController
{
    @Autowired
    TopicService ts;
    
    @Autowired
    ReplyService rs;
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String process(
        @RequestParam(value = "boardid", required = true) Integer boardId,
        @RequestParam(value = "topicid", required = true) Integer topicId,
        @RequestParam(value = "replyid", required = false) Integer replyId,
        HttpSession session, HttpServletRequest request)
    {
        
        User user = (User)session.getAttribute(DefaultVariables.USERINFO);
        Integer userid = user.getUserid();
      //如果replyId为空，表示发送的是删除主题帖请求
        if(replyId == null)
        {
            ts.deleteTopic(topicId, userid);
            return "redirect:board?boardid=" + boardId;
        }
        else
        {
            rs.deleteReply(replyId, userid);
            return "redirect:topic?boardid=" + boardId +"&topicid="+topicId;
        }
    }
}
