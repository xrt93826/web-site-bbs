package com.itany.bbs.entity;

import java.util.Date;
/**
 * 
 * 用于存放回复信息的实体对象
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class Reply
{
    private Integer replyId;
    private String rtitle;
    private String rcontents;
    private Integer rface;
    private Integer userid;
    private Integer topicid;
    private Date postdate;
    private Date modifydate;
    private Integer state;
    
    public Integer getReplyId()
    {
        return replyId;
    }
    public void setReplyId(Integer replyId)
    {
        this.replyId = replyId;
    }
    public String getRtitle()
    {
        return rtitle;
    }
    public void setRtitle(String rtitle)
    {
        this.rtitle = rtitle;
    }
    public String getRcontents()
    {
        return rcontents;
    }
    public void setRcontents(String rcontents)
    {
        this.rcontents = rcontents;
    }
    public Integer getRface()
    {
        return rface;
    }
    public void setRface(Integer rface)
    {
        this.rface = rface;
    }
    public Integer getUserid()
    {
        return userid;
    }
    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }
    public Integer getTopicid()
    {
        return topicid;
    }
    public void setTopicid(Integer topicid)
    {
        this.topicid = topicid;
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
    
    public Reply(Integer replyId, String rtitle, String rcontents, Integer rface, Integer userid, Integer topicid, Date postdate, Date modifydate, Integer state)
    {
        super();
        this.replyId = replyId;
        this.rtitle = rtitle;
        this.rcontents = rcontents;
        this.rface = rface;
        this.userid = userid;
        this.topicid = topicid;
        this.postdate = postdate;
        this.modifydate = modifydate;
        this.state = state;
    }
    public Reply()
    {
     
    }
}
