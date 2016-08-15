package com.itany.bbs.dao;

import java.util.List;

import com.itany.bbs.entity.Message;
import com.itany.bbs.entity.Paginate;

/**
 * 
 * 可选：论坛站内信相关数据访问接口
 * 
 * @author 杨卫兵
 * @version [V1.00, 2016年6月14日]
 * @since V1.00
 */
public interface MessageDao
{
    /**
     * 添加站内信，用于发送
     * @param msg
     * @return
     */
    boolean addMessage(Message msg);

    /**
     * 发件人读取站内信
     * @param uid
     * @param mid
     * @return
     */
    Message readMessageBySender(int uid, int mid);

    /**
     * 收件人读取站内信
     * @param uid
     * @param mid
     * @return
     */
    Message readMessageByReciever(int uid, int mid);

    /**
     * 发件箱分页查询
     * @param uid
     * @param page
     * @return
     */
    List<Message> readSendMessagesByPage(int uid, Paginate page);
    
    /**
     * 收件箱分页查询
     * @param uid
     * @param page
     * @return
     */
    List<Message> readRecieveMessagesByPage(int uid, Paginate page);
    
    /**
     * 发件人删除站内信
     * @param uid
     * @param mid
     * @return
     */
    boolean delMessageBySender(int uid, int mid);

    /**
     * 收件人删除站内信
     * @param uid
     * @param mid
     * @return
     */
    boolean delMessageByReciever(int uid, int mid);

    /**
     * 获取收到的站内信数量
     * @param uid   用户id
     * @return
     */
    int getMsgsCnt(int uid);
    
    /**
     * 获取收到的未读站内信数量
     * @param uid   用户id
     * @return
     */
    int getNewMsgsCnt(int uid);
    
    /**
     * 获取发送的站内信数量
     * @param uid   用户id
     * @return
     */
    int getSendMsgsCnt(int uid);
    
    /**
     * 修改收到的站内信状态：用于将未读站内信改为已读
     * @param uid
     * @param mid
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean modifyMessageState(int uid, int mid);
}
