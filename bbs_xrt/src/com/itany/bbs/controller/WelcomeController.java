package com.itany.bbs.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;










import com.itany.bbs.pojo.BoardInfo;
import com.itany.bbs.service.BoardService;
import com.util.DefaultVariables;
import com.util.XMLCreator;
import com.util.XMLUtil;
/**
 * 
 * 处理首页的welcome请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Controller
public class WelcomeController
{
    @Autowired
    private BoardService service;
    
    @RequestMapping(value = "/welcome")
    public String process(HttpServletRequest request,HttpServletResponse response) throws IOException{
//        Map<Integer, List<BoardInfo>> map = service.getAllBoardInfo();
        List<BoardInfo> list = service.getAllBoardInfo();
//        request.setAttribute(DefaultVariables.ALLBOARDINFO, map);
        String str = "";
        if (list != null && list.size() > 0)
        {
            JSONArray arr = new JSONArray(list);
            str = arr.toString();
        }
        //Document doc=XMLUtil.generateXMLFromList(list, "Boards", "Board","parentId");
        //Document doc = XMLCreator.createXMLFromBoardList(list);
        response.setContentType("text/plain;charset=UTF-8");
        //XMLUtil.output(doc, response.getOutputStream());
        
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();
        return null;
    }
}
