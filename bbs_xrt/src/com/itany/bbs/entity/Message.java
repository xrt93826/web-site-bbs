package com.itany.bbs.entity;

import java.util.Date;
/**
 * 
 * 用于存放短消息数据的实体对象
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class Message
{
    private Integer mid;
    private String mtitle;
    private String mcontents;
    private Integer fromid;
    private Integer toid;
    private Integer isToDel;
    private Integer isFromDel;
    private Date sendDate;
    private Integer state;
    public Integer getMid()
    {
        return mid;
    }
    public void setMid(Integer mid)
    {
        this.mid = mid;
    }
    public String getMtitle()
    {
        return mtitle;
    }
    public void setMtitle(String mtitle)
    {
        this.mtitle = mtitle;
    }
    public String getMcontents()
    {
        return mcontents;
    }
    public void setMcontents(String mcontents)
    {
        this.mcontents = mcontents;
    }
    public Integer getFromid()
    {
        return fromid;
    }
    public void setFromid(Integer fromid)
    {
        this.fromid = fromid;
    }
    public Integer getToid()
    {
        return toid;
    }
    public void setToid(Integer toid)
    {
        this.toid = toid;
    }
    public Integer getIsToDel()
    {
        return isToDel;
    }
    public void setIsToDel(Integer isToDel)
    {
        this.isToDel = isToDel;
    }
    public Integer getIsFromDel()
    {
        return isFromDel;
    }
    public void setIsFromDel(Integer isFromDel)
    {
        this.isFromDel = isFromDel;
    }
    public Date getSendDate()
    {
        return sendDate;
    }
    public void setSendDate(Date sendDate)
    {
        this.sendDate = sendDate;
    }
    public Integer getState()
    {
        return state;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }
    public Message(Integer mid, String mtitle, String mcontents, Integer fromid, Integer toid, Integer isToDel, Integer isFromDel, Date sendDate, Integer state)
    {
        this.mid = mid;
        this.mtitle = mtitle;
        this.mcontents = mcontents;
        this.fromid = fromid;
        this.toid = toid;
        this.isToDel = isToDel;
        this.isFromDel = isFromDel;
        this.sendDate = sendDate;
        this.state = state;
    }
    public Message()
    {
    
    }
    
}
