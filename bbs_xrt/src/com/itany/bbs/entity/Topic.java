package com.itany.bbs.entity;

import java.util.Date;
/**
 * 
 * 用于存放话题信息的实体对象
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class Topic
{
    private Integer topicId;
    private String title;
    private String tcontents;
    private String treplycontents;
    private Integer tface;
    private Integer isreply;
    private Integer readpoint;
    private Integer accesspoint;
    private Integer userid;
    private Integer boardid;
    private Date postdate;
    private Date modifydate;
    private Integer state;
    public Integer getTopicId()
    {
        return topicId;
    }
    public void setTopicId(Integer topicId)
    {
        this.topicId = topicId;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTcontents()
    {
        return tcontents;
    }
    public void setTcontents(String tcontents)
    {
        this.tcontents = tcontents;
    }
    public String getTreplycontents()
    {
        return treplycontents;
    }
    public void setTreplycontents(String treplycontents)
    {
        this.treplycontents = treplycontents;
    }
    public Integer getTface()
    {
        return tface;
    }
    public void setTface(Integer tface)
    {
        this.tface = tface;
    }
    public Integer getIsreply()
    {
        return isreply;
    }
    public void setIsreply(Integer isreply)
    {
        this.isreply = isreply;
    }
    public Integer getReadpoint()
    {
        return readpoint;
    }
    public void setReadpoint(Integer readpoint)
    {
        this.readpoint = readpoint;
    }
    public Integer getAccesspoint()
    {
        return accesspoint;
    }
    public void setAccesspoint(Integer accesspoint)
    {
        this.accesspoint = accesspoint;
    }
    public Integer getUserid()
    {
        return userid;
    }
    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }
    public Integer getBoardid()
    {
        return boardid;
    }
    public void setBoardid(Integer boardid)
    {
        this.boardid = boardid;
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
    public Integer getState()
    {
        return state;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }
    public Topic(
        Integer topicId,
        String title,
        String tcontents,
        String treplycontents,
        Integer tface,
        Integer isreply,
        Integer readpoint,
        Integer accesspoint,
        Integer userid,
        Integer boardid,
        Date postdate,
        Date modifydate,
        Integer state)
    {
        this.topicId = topicId;
        this.title = title;
        this.tcontents = tcontents;
        this.treplycontents = treplycontents;
        this.tface = tface;
        this.isreply = isreply;
        this.readpoint = readpoint;
        this.accesspoint = accesspoint;
        this.userid = userid;
        this.boardid = boardid;
        this.postdate = postdate;
        this.modifydate = modifydate;
        this.state = state;
    }
    public Topic()
    {
    
    }
    
}
