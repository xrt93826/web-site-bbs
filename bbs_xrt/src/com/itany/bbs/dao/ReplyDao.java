package com.itany.bbs.dao;

import java.util.List;

import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Reply;

/**
 * 
 * 论坛回复帖相关数据访问接口
 * 
 * @author 杨卫兵
 * @version [V1.00, 2016年6月14日]
 * @since V1.00
 */
public interface ReplyDao
{
    /**
     * 根据主题帖id获取回复数量
     * @param topicId
     * @return
     */
    int getReplyCount(int topicId);

    /**
     * 根据主题帖id分页查询回帖列表
     * @param topicId   主题帖id
     * @param page      分页对象
     * @return
     */
    List<Reply> findReplyByPage(int topicId, Paginate page);

    /**
     * 修改回复帖
     * @param t     回复帖对象
     * @return      true-修改成功；false-失败      
     */
    boolean modifyReply(Reply r);
    
    /**
     * 删除回复帖
     * @param replyId   回复帖id
     * @param userId    用户id
     * @return          true-删除成功；false-失败
     */
    boolean deleteReply(int replyId, int userId);
    
    /**
     * 添加回复帖，用于用户回帖
     * @param t     回复帖对象
     * @return      回帖成功返回新回帖id，失败返回0
     */
    int addReply(Reply r);
    
    /**
     * 根据回复帖id获取回复帖信息
     * @param topicId   回复帖id
     * @return          回复帖对象
     */
    Reply findReplyById(int replyId);
}
