package com.itany.bbs.service;

import java.util.List;

import com.itany.bbs.entity.Reply;
import com.itany.bbs.pojo.TopicDetailInfo;

/**
 * 
 * 回复业务逻辑接口
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface ReplyService
{
    /**
     * 
     * 根据主题帖id分页查询回帖列表详细信息
     * 
     * @param topicId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TopicDetailInfo> getReplyDetail(int topicId,int pagenum,TopicDetailInfo tdi);
    
    /**
     * 
     * 根据主题帖id查询最大页数
     * 
     * @param topicId
     * @return 最大页数
     * @see [类、类#方法、类#成员]
     */
    public Integer getMaxPageNo(Integer topicId);
    
    /**
     * 
     * 回帖
     * 
     * @param topicId 主题帖id
     * @param rtitle 回复标题
     * @param rcontents 回复内容
     * @param userId 回复人id
     * @return 回帖成功返回新回帖id，失败返回0
     * @see [类、类#方法、类#成员]
     */
    public Integer addReply(Integer topicId,String rtitle,String rcontents,Integer userId);
    
    /**
     * 
     * 根据回复帖id获取回复帖信息
     * @param topicId   回复帖id
     * @return          回复帖对象
     * @see [类、类#方法、类#成员]
     */
    public Reply findReplyById(int replyId);
    
    
    /**
     * 
     * 修改回复帖
     * 
     * @param replyId 回复帖id
     * @param rtitle 回复帖标题
     * @param rcontents 回复帖内容
     * @return 成功返回true 失败false
     * @see [类、类#方法、类#成员]
     */
    public boolean modifyReply(Integer replyId ,String rtitle, String rcontents);
    
    
    
    /**
     * 删除回复帖
     * @param replyId   回复帖id
     * @param userId    用户id
     * @return          true-删除成功；false-失败
     */
    public boolean deleteReply(int replyId, int userId);
}
