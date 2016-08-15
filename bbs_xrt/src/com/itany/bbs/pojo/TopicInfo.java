package com.itany.bbs.pojo;

import java.util.Date;

/**
 * 
 * 存放版块页的帖子信息的对象
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class TopicInfo
{
    public Date getModifyTime()
    {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime)
    {
        this.modifyTime = modifyTime;
    }

    //主题帖id
    private Integer topicId;
    //主题帖标题
    private String topicTitle;
    //主题帖作者
    private String topicUser;
    //发表时间
    private Date modifyTime;
    //帖子回复数
    private Integer replyCount;
    
    public Integer getTopicId()
    {
        return topicId;
    }

    public void setTopicId(Integer topicId)
    {
        this.topicId = topicId;
    }

    public String getTopicTitle()
    {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle)
    {
        this.topicTitle = topicTitle;
    }

    public String getTopicUser()
    {
        return topicUser;
    }

    public void setTopicUser(String topicUser)
    {
        this.topicUser = topicUser;
    }

    public Integer getReplyCount()
    {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount)
    {
        this.replyCount = replyCount;
    }

    public TopicInfo()
    {
       
    }

    public TopicInfo(Integer topicId, String topicTitle, String topicUser, Integer replyCount)
    {
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.topicUser = topicUser;
        this.replyCount = replyCount;
    }

    public TopicInfo(Integer topicId, String topicTitle, String topicUser, Date modifyTime, Integer replyCount)
    {
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.topicUser = topicUser;
        this.modifyTime = modifyTime;
        this.replyCount = replyCount;
    }

    public TopicInfo(Integer topicId, String topicTitle, String topicUser, Date modifyTime)
    {
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.topicUser = topicUser;
        this.modifyTime = modifyTime;
    }
   
    
    
}
