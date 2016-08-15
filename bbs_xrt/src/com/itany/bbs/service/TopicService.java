package com.itany.bbs.service;

import java.util.List;

import com.itany.bbs.entity.Topic;
import com.itany.bbs.pojo.TopicDetailInfo;
import com.itany.bbs.pojo.TopicInfo;

/**
 * 
 * 帖子相关业务逻辑接口
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月20日]
 * @see [相关类/方法]
 * @since V1.00
 */
public interface TopicService
{
    /**
     * 
     * 根据版块id和页码，分页显示版块页的主题帖信息
     * 
     * @param boardId 版块id
     * @param pagenum 页码
     * @return 版块页的帖子信息的对象的集合
     * @see [类、类#方法、类#成员]
     */
    public List<TopicInfo> findTopicInfoByPage(int boardId, int pagenum);
    
    /**
     * 
     * 通过版块id，得到分页查询的最大页数
     * <功能详细描述>
     * 
     * @param boardId 版块id
     * @return 返回最大页数
     * @see [类、类#方法、类#成员]
     */
    public int getMaxPageNo(int boardId);
    
    /**
     * 
     * 根据主题帖id获取主题帖详细信息
     * 
     * @param topicId 主题帖id
     * @return 返回主题帖详细信息
     * @see [类、类#方法、类#成员]
     */
    public TopicDetailInfo getTopicDetail(int topicId);
    
    /**
     * 
     * 用于发帖
     * 
     * @param boardId 版块id
     * @param title 主题帖标题
     * @param tcontents 主题帖内容
     * @param userId 用户id
     * @return 发帖成功返回新帖id，失败返回0
     * @see [类、类#方法、类#成员]
     */
    public int addTopic(Integer boardId, String title, String tcontents, Integer userId);
    
    
    /**
     * 
     * 修改主题帖
     * 
     * @param topicId 主题帖id
     * @param title 主题帖标题
     * @param tcontents 主题帖内容
     * @return 成功返回true 失败false
     * @see [类、类#方法、类#成员]
     */
    public boolean modifyTopic(Integer topicId ,String title, String tcontents);
    
    /**
     * 根据主题帖id获取主题帖信息
     * @param topicId   主题帖id
     * @return          主题帖对象
     */
    public Topic findTopicById(int topicId);
    
    
    /**
     * 删除主题帖
     * @param topicId   主题帖id
     * @param userId    用户id
     * @return          true-删除成功；false-失败
     */
    public boolean deleteTopic(int topicId,int userId);
}
