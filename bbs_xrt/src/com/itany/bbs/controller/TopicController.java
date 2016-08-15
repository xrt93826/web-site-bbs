package com.itany.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.entity.Board;
import com.itany.bbs.pojo.TopicDetailInfo;
import com.itany.bbs.service.BoardService;
import com.itany.bbs.service.ReplyService;
import com.itany.bbs.service.TopicService;
import com.util.DefaultVariables;

/**
 * 
 * 用于处理看帖请求
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月21日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Controller
public class TopicController
{
    @Autowired
    private BoardService boardservice;
    
    @Autowired
    TopicService topicservice;
    
    @Autowired
    ReplyService replyservice;
    
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public String process(@RequestParam(value = "boardid", required = true) Integer boardId, @RequestParam(value = "topicid", required = true) Integer topicId,
        @RequestParam(value = "pagenum", required = false) Integer pagenum, HttpSession session, HttpServletRequest request)
    {
        Board board = boardservice.getBoardById(boardId);
        
        request.setAttribute(DefaultVariables.BOARDINFO, board);
        
        if (null == pagenum)
        {
            pagenum = 1;
        }
        TopicDetailInfo tdi = topicservice.getTopicDetail(topicId);
        List<TopicDetailInfo> list = null;
        if (null != replyservice.getReplyDetail(topicId, pagenum, tdi))
        {
            list = replyservice.getReplyDetail(topicId, pagenum, tdi);
        }
        
        request.setAttribute(DefaultVariables.TOPICANDREPLY, list);
        
        int maxPageNo = replyservice.getMaxPageNo(topicId);
        
        request.setAttribute(DefaultVariables.MAXPAGENO, maxPageNo);
        
        request.setAttribute(DefaultVariables.TOPICID, topicId);
        
        return "topic";
    }
}
