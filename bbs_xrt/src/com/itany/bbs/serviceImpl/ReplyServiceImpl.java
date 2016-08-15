package com.itany.bbs.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itany.bbs.dao.ReplyDao;
import com.itany.bbs.dao.UserDao;
import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Reply;
import com.itany.bbs.entity.User;
import com.itany.bbs.pojo.TopicDetailInfo;
import com.itany.bbs.service.ReplyService;

/**
 * 
 * 回复业务逻辑实现
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Service
public class ReplyServiceImpl implements ReplyService
{
    @Autowired
    ReplyDao replydao;
    
    @Autowired
    UserDao userdao;
    
    /**
     * 
     * 重写方法
     * @param topicId 主题帖id
     * @return 回复贴详细信息的集合
     * @see com.itany.bbs.service.ReplyService#getReplyDetail(int)
     */
    @Override
    public List<TopicDetailInfo> getReplyDetail(int topicId,int pagenum,TopicDetailInfo tdi)
    {
        List<TopicDetailInfo> list = new ArrayList<TopicDetailInfo>();
        //获取总记录数
        Integer records = replydao.getReplyCount(topicId);
        //创建分页对象
        Paginate page = new Paginate();
        
        if(pagenum == 1){
            list.add(tdi);
          //设置每页3条
            page.setPageSize(3);
            
        }else{
          //第二页开始设置每页4条
            page.setPageSize(4);
        }
        page.setRecords(records);
        page.setPageNo(pagenum);
//        //计算最大页数，并自动归整
//        page.calc();
        List<Reply> list1 = replydao.findReplyByPage(topicId, page);
        if(list1.size() == 0){
            return list;
        }
        
        TopicDetailInfo tdi0 = null;
        //遍历得到的reply对象，并获取需要的信息，存入list
        for(Reply one:list1){
            
            String title = one.getRtitle();
            String contents = one.getRcontents();
            
            Date postdate = one.getPostdate();
            
            Date modifydate = one.getModifydate();
            //根据主题帖发帖人id，获得用户对象
            User user = userdao.findUser(one.getUserid());
            tdi0 = new TopicDetailInfo(user.getLoginname(), user.getHead(), user.getRegtime(),one.getReplyId(), title, contents, postdate, modifydate);
            
            list.add(tdi0);
        }
        return list;
        
    }
    
    
    /**
     * 
     * 重写方法
     * @param topicId 主题帖id
     * @return 最大页数
     * @see com.itany.bbs.service.ReplyService#getMaxPageNo(java.lang.Integer)
     */
    @Override
    public Integer getMaxPageNo(Integer topicId)
    {
        //获取总记录数
        Integer records = replydao.getReplyCount(topicId);
        
        Integer pagemax = (records + 4) / 4;
        
        
        return pagemax;
    }


    /**
     * 
     * 重写方法
     * @param topicId 主题帖id
     * @param rtitle 回复标题
     * @param rcontents 回复内容
     * @param userId 回复人id
     * @return 回帖成功返回新回帖id，失败返回0
     * @see com.itany.bbs.service.ReplyService#addReply(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
     */
    @Override
    public Integer addReply(Integer topicId, String rtitle, String rcontents, Integer userId)
    {
        
        Date date = new Date();
        Reply r = new Reply(0, rtitle, rcontents, null, userId, topicId, date, date, null);
        
        
        return replydao.addReply(r);
    }

    /**
     * 
     * 重写方法
     * 根据回复帖id获取回复帖信息
     * @param topicId   回复帖id
     * @return          回复帖对象
     * @see com.itany.bbs.service.ReplyService#findReplyById(int)
     */
    @Override
    public Reply findReplyById(int replyId)
    {
        return replydao.findReplyById(replyId);
    }

    /**
     * 修改回复帖
     * 重写方法
     * @param replyId 回复帖id
     * @param rtitle 回复帖标题
     * @param rcontents 回复帖内容
     * @return 成功返回true 失败false
     * @see com.itany.bbs.service.ReplyService#modifyReply(java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public boolean modifyReply(Integer replyId, String rtitle, String rcontents)
    {
        
        Reply r = replydao.findReplyById(replyId);
        if(null == r){
            return false;
        }
        r.setRtitle(rtitle);
        r.setRcontents(rcontents);
        Date date = new Date();
        r.setModifydate(date);
        
        return replydao.modifyReply(r);
    }

    
    /**
     * 
     * 重写方法
     * @param replyId
     * @param userId
     * @return
     * @see com.itany.bbs.service.ReplyService#deleteReply(int, int)
     */
    @Override
    public boolean deleteReply(int replyId, int userId)
    {
        return replydao.deleteReply(replyId, userId);
    }
    
}
