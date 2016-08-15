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
 * 首页过滤器
 * 拦截首页，如果请求中没有版块信息，则发送welcome请求
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class WelcomeFilter implements Filter
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
        if(req.getAttribute(DefaultVariables.ALLBOARDINFO) == null){
            
            response.sendRedirect(request.getContextPath()+"/welcome");
            
        }
        else{
            chain.doFilter(req, response);
        }
    }
    
    @Override
    public void init(FilterConfig conf)
        throws ServletException
    {
        
    }
    
}
