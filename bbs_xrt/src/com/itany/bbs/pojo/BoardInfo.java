package com.itany.bbs.pojo;



/**
 * 
 * 存放首页的版块信息的对象
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class BoardInfo
{
    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public Integer getBoardId()
    {
        return boardId;
    }

    public void setBoardId(Integer boardId)
    {
        this.boardId = boardId;
    }

    public String getBoardName()
    {
        return boardName;
    }

    public void setBoardName(String boardName)
    {
        this.boardName = boardName;
    }

    public Integer getTopicCount()
    {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount)
    {
        this.topicCount = topicCount;
    }

    public TopicInfo getTopicInfo()
    {
        return topicInfo;
    }

    public void setTopicInfo(TopicInfo topicInfo)
    {
        this.topicInfo = topicInfo;
    }

    //版块id 
    private Integer boardId;
    //父版块id
    private Integer parentId;
    //版块名
    private String boardName;
    //主题帖数量
    private Integer topicCount;
    //最后发表主题帖的对象
    private TopicInfo topicInfo;
    
    
    public BoardInfo(Integer boardId, Integer parentId, String boardName, Integer topicCount, TopicInfo topicInfo)
    {
        this.boardId = boardId;
        this.parentId = parentId;
        this.boardName = boardName;
        this.topicCount = topicCount;
        this.topicInfo = topicInfo;
    }

    public BoardInfo()
    {
        
    }
    
    
}
