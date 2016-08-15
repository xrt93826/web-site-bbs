package com.itany.bbs.entity;

import java.util.Date;
/**
 * 
 * 用于存放版块信息的实体对象
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class Board
{
    private Integer boardId;
    private String boardName;
    private Integer creatorId;
    private Integer masterId;
    private Date createtime;
    private Integer parentId;
    private Integer point;
    private Integer state;
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
    public Integer getCreatorId()
    {
        return creatorId;
    }
    public void setCreatorId(Integer creatorId)
    {
        this.creatorId = creatorId;
    }
    public Integer getMasterId()
    {
        return masterId;
    }
    public void setMasterId(Integer masterId)
    {
        this.masterId = masterId;
    }
    public Date getCreatetime()
    {
        return createtime;
    }
    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }
    public Integer getParentId()
    {
        return parentId;
    }
    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }
    public Integer getPoint()
    {
        return point;
    }
    public void setPoint(Integer point)
    {
        this.point = point;
    }
    public Integer getState()
    {
        return state;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }
    public Board(Integer boardId, String boardName, Integer creatorId, Integer masterId, Date createtime, Integer parentId, Integer point, Integer state)
    {
        this.boardId = boardId;
        this.boardName = boardName;
        this.creatorId = creatorId;
        this.masterId = masterId;
        this.createtime = createtime;
        this.parentId = parentId;
        this.point = point;
        this.state = state;
    }
    public Board()
    {
        
    }
    
}
