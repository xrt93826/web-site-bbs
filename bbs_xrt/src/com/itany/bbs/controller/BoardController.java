package com.itany.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.itany.bbs.entity.Board;
import com.itany.bbs.entity.User;
import com.itany.bbs.pojo.TopicInfo;
import com.itany.bbs.service.BoardService;
import com.itany.bbs.service.TopicService;
import com.util.DefaultVariables;


/**
 * 
 * 用于处理查看版块的请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class BoardController
{
    @Autowired
    private BoardService service;
    
    @Autowired
    private TopicService topicservice;
    
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String process(
        @RequestParam(value = "boardid", required = true) Integer boardId,
        @RequestParam(value = "pagenum", required = false) Integer pagenum,
        HttpSession session, HttpServletRequest request)
    { 
        Board board = service.getBoardById(boardId);
        
        request.setAttribute(DefaultVariables.BOARDINFO, board);
        
        if(null == pagenum){
            pagenum = 1;
        }
        List<TopicInfo> list = null;
        if(null != topicservice.findTopicInfoByPage(boardId, pagenum)){
            list = topicservice.findTopicInfoByPage(boardId, pagenum);
        }
        request.setAttribute(DefaultVariables.ALLTOPICINFO, list);
        int maxPageNo = topicservice.getMaxPageNo(boardId);
        
        request.setAttribute(DefaultVariables.MAXPAGENO, maxPageNo);
        
        return "board";
    }
    
    
    /**
     * 
     * 验证版块名是否存在控制器
     * 
     * @param boardName
     * @param session
     * @param request
     * @param response
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/valiName", method = RequestMethod.GET)
    public String valiName(
        @RequestParam(value = "boardName", required = true) String boardName,
        HttpSession session, HttpServletRequest request,HttpServletResponse response)
    { 
        String ret="false";
        try
        {
            if(boardName.trim()!=null){
                boardName=URLDecoder.decode(boardName, "UTF-8");
                if(service.exists(boardName)){
                    ret = "true";
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
        return null;
    }
    
    /**
     * 
     * 添加版块控制器
     * 
     * @param boardId
     * @param pagenum
     * @param session
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/addBoard", method = RequestMethod.POST)
    public String addBoard(
        @RequestParam(value = "boardName", required = true) String boardName,
        @RequestParam(value = "level", required = true) Integer level,
        @RequestParam(value = "parentId", required = false) Integer parentId,
        HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException
    { 
        String ret="false";
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User use = (User)session.getAttribute(DefaultVariables.USERINFO);
        if (use != null && use.getState() == 3)
        {
            int status = 0;
            if(level==1){
                status = service.addParentBoard(boardName, use);
            }else if(level==2){
                status = service.addChildBoard(boardName, parentId, use);
            }
            if(status!=0){
                ret="true"; 
            }
        }
        out.print(ret);
        out.flush();
        out.close();
        return null;
    }
    
    /**
     * 
     * 获取所有一级版块
     * 
     * @param session
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getParentBoard")
    public String getParentBoard(
        HttpServletResponse response, HttpServletRequest request) throws IOException
    { 
        List<Board> list = service.getBoards(0);
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
}
