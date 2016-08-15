package com.itany.bbs.pojo;

import java.util.Date;

/**
 * 
 * 存放帖子页帖子以及回复内容的详细信息
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class TopicDetailInfo
{
    //发布人用户名
    private String username;
    //头像地址
    private String head;
    //注册时间
    private Date regtime;
    //回复帖id
    private Integer replyid;
    //帖子或回复的标题
    private String title;
    //帖子或回复的内容
    private String contents;
    //帖子或回复的发布日期
    private Date postdate;
    //帖子或回复的最新修改日期
    private Date modifydate;
    public Integer getReplyid()
    {
        return replyid;
    }
    public void setReplyid(Integer replyid)
    {
        this.replyid = replyid;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getHead()
    {
        return head;
    }
    public void setHead(String head)
    {
        this.head = head;
    }
    public Date getRegtime()
    {
        return regtime;
    }
    public void setRegtime(Date regtime)
    {
        this.regtime = regtime;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getContents()
    {
        return contents;
    }
    public void setContents(String contents)
    {
        this.contents = contents;
    }
    public Date getPostdate()
    {
        return postdate;
    }
    public void setPostdate(Date postdate)
    {
        this.postdate = postdate;
    }
    public Date getModifydate()
    {
        return modifydate;
    }
    public void setModifydate(Date modifydate)
    {
        this.modifydate = modifydate;
    }
    public TopicDetailInfo(String username, String head, Date regtime, Integer replyid,String title, String contents, Date postdate, Date modifydate)
    {
        this.username = username;
        this.head = head;
        this.regtime = regtime;
        this.replyid = replyid;
        this.title = title;
        this.contents = contents;
        this.postdate = postdate;
        this.modifydate = modifydate;
    }
    public TopicDetailInfo()
    {
        
    } 
    
    
}
