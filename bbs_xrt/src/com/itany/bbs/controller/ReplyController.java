package com.itany.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itany.bbs.entity.Board;
import com.itany.bbs.service.BoardService;
import com.util.DefaultVariables;

/**
 * 
 * 处理跳转至回帖页的请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class ReplyController
{
    @Autowired
    BoardService service;
    
    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public String process(
        @RequestParam(value = "boardid", required = true) Integer boardId,
        HttpSession session, HttpServletRequest request)
    { 
        Board board = service.getBoardById(boardId);
        
        request.setAttribute(DefaultVariables.BOARDINFO, board);
        
        return "reply";
        
    }
}
