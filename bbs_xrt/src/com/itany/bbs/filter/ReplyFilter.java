package com.itany.bbs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DefaultVariables;

/**
 * 
 * 回复帖子页过滤器
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class ReplyFilter implements Filter
{
    
    @Override
    public void destroy()
    {
        
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        // 判断用户是否登录
        if (request.getSession(true).getAttribute(DefaultVariables.USERINFO) == null)
        {
            request.setAttribute(DefaultVariables.ERRORMESSAGE, "您未登录，请先登录再回复帖子");
            request.getRequestDispatcher("./errorreport.jsp").forward(request, response);
        }
        else
        {
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void init(FilterConfig arg0)
        throws ServletException
    {
        
    }
    
}
