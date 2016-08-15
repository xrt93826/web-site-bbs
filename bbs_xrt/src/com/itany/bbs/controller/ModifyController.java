package com.itany.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.entity.Board;
import com.itany.bbs.entity.Reply;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.service.BoardService;
import com.itany.bbs.service.ReplyService;
import com.itany.bbs.service.TopicService;
import com.util.DefaultVariables;

/**
 * 
 * 处理修改帖子请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class ModifyController
{
    @Autowired
    TopicService topicservice;
    
    @Autowired
    BoardService boardservice;
    
    @Autowired
    ReplyService replyservice;
    
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String process(
        @RequestParam(value = "boardid", required = true) Integer boardId,
        @RequestParam(value = "topicid", required = true) Integer topicId,
        @RequestParam(value = "replyid", required = false) Integer replyId,
        HttpSession session, HttpServletRequest request)
    {   //获取版块对象，发送给页面
        Board board = boardservice.getBoardById(boardId);
        
        request.setAttribute(DefaultVariables.BOARDINFO, board);
        //如果replyId为空，表示发送的是修改主题帖请求
        if(replyId == null)
        {
            Topic t = topicservice.findTopicById(topicId);
            
            request.setAttribute(DefaultVariables.TOPICANDREPLY, t);
            request.setAttribute(DefaultVariables.UPDATETYPE, 0);
            
            return "modify";
            
        }
        else
        {
            Reply r = replyservice.findReplyById(replyId);
            
            request.setAttribute(DefaultVariables.TOPICANDREPLY, r);
            request.setAttribute(DefaultVariables.UPDATETYPE, 1);
            return "modify";
        }
        
    }
    
    @RequestMapping(value = "/modifytopic", method = RequestMethod.POST)
    public String topicprocess(
        @RequestParam(value = "boardId", required = true) Integer boardId,
        @RequestParam(value = "topicId", required = true) Integer topicId,
        @RequestParam(value = "title", required = true) String title,
        @RequestParam(value = "tcontents", required = true) String tcontents,
        HttpSession session, HttpServletRequest request)
        {
            boolean state = topicservice.modifyTopic(topicId, title, tcontents);
            if(state == false){
                request.setAttribute(DefaultVariables.ERRORMESSAGE, "修改主题帖失败");
                return "errorreport";
            }
            return "redirect:topic?boardid=" + boardId +"&topicid="+topicId;
        }
    
    
    @RequestMapping(value = "/modifyreply", method = RequestMethod.POST)
    public String replyprocess(
        @RequestParam(value = "boardId", required = true) Integer boardId,
        @RequestParam(value = "topicId", required = true) Integer topicId,
        @RequestParam(value = "replyId", required = true) Integer replyId,
        @RequestParam(value = "rtitle", required = false) String rtitle,
        @RequestParam(value = "rcontents", required = true) String rcontents,
        HttpSession session, HttpServletRequest request)
        {
        
            boolean state = replyservice.modifyReply(replyId, rtitle, rcontents);
            if(state == false){
                request.setAttribute(DefaultVariables.ERRORMESSAGE, "修改回帖失败");
                return "errorreport";
            }
            return "redirect:topic?boardid=" + boardId +"&topicid="+topicId;
        }
}
