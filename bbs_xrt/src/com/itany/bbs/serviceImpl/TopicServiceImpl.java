package com.itany.bbs.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itany.bbs.dao.ReplyDao;
import com.itany.bbs.dao.TopicDao;
import com.itany.bbs.dao.UserDao;
import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.entity.User;
import com.itany.bbs.pojo.TopicDetailInfo;
import com.itany.bbs.pojo.TopicInfo;
import com.itany.bbs.service.TopicService;
/**
 * 
 * 帖子相关业务逻辑实现
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Service
public class TopicServiceImpl implements TopicService
{
    
    @Autowired
    TopicDao topicdao;
    
    @Autowired
    ReplyDao replydao;
    
    @Autowired
    UserDao userdao;
    /**
     * 
     * 重写方法
     * @param boardId 版块id
     * @param pagenum 页码
     * @return 版块页的帖子信息的对象的集合
     * @see com.itany.bbs.service.TopicService#findTopicInfoByPage(int, com.itany.bbs.entity.Paginate)
     */
    @Override
    public List<TopicInfo> findTopicInfoByPage(int boardId, int pagenum)
    {
        //获取总记录数
        Integer records = topicdao.getTopicCount(boardId);
        //创建分页对象
        Paginate page = new Paginate();
        //设置每页8条
        page.setPageSize(8);
        page.setRecords(records);
        page.setPageNo(pagenum);
        //计算最大页数，并自动归整
        page.calc();
        List<Topic> list1 = topicdao.findTopicByPage(boardId, page);
        if(list1.size() == 0){
            return null;
        }
        List<TopicInfo> list = new ArrayList<TopicInfo>();
        TopicInfo ti = null;
        //遍历得到的topic对象，并获取需要的标题、作者名、回复数，存入list
        for(Topic one:list1){
            String title = one.getTitle();
            Integer userid = one.getUserid();
            Integer topicid = one.getTopicId();
            String topicUser = userdao.findUser(userid).getLoginname();
            Integer replyCount = replydao.getReplyCount(topicid);
            
            ti = new TopicInfo(topicid, title, topicUser, replyCount);
            list.add(ti);
        }
        return list;
    }
    
    
    /**
     * 
     * 重写方法
     * @param boardId 版块id
     * @return 最大页数
     * @see com.itany.bbs.service.TopicService#getMaxPageNo(int)
     */
    @Override
    public int getMaxPageNo(int boardId)
    {
      //获取总记录数
        Integer records = topicdao.getTopicCount(boardId);
        //创建分页对象
        Paginate page = new Paginate();
        //设置每页8条
        page.setPageSize(8);
        page.setRecords(records);
        //计算最大页数，并自动归整
        page.calc();
        return page.getMaxPageNo();
    }

    /**
     * 
     * 重写方法
     * @param topicId 主题帖id
     * @return 返回主题帖详细信息
     * @see com.itany.bbs.service.TopicService#getTopicDetail(int)
     */
    @Override
    public TopicDetailInfo getTopicDetail(int topicId)
    {
        //根据主题帖id获取找到主题帖，获得主题帖对象
        Topic topic = topicdao.findTopicById(topicId);
        if(topic == null){
            return null;
        }
        
        String title = topic.getTitle();
        
        String contents = topic.getTcontents();
        
        Date postdate = topic.getPostdate();
        
        Date modifydate = topic.getModifydate();
        //根据主题帖发帖人id，获得用户对象
        User user = userdao.findUser(topic.getUserid());
        
        if(user == null){
            return null;
        }
        
        TopicDetailInfo tdi = new TopicDetailInfo(user.getLoginname(), user.getHead(), user.getRegtime(),null, title, contents, postdate, modifydate);
        return tdi;
    }

    
    
    /**
     * 
     * 重写方法
     * @param boardId 版块id
     * @param title 主题帖标题
     * @param tcontents 主题帖内容
     * @param userId 用户id 
     * @return 发帖成功返回新帖id，失败返回0
     * @see com.itany.bbs.service.TopicService#addTopic(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
     */
    @Override
    public int addTopic(Integer boardId, String title, String tcontents, Integer userId)
    {
        Date date = new Date();
        Topic t = new Topic(0, title, tcontents, "", null, null, null, null, userId, boardId, date, date, null);
        return topicdao.addTopic(t);
    }

    /**
     * 根据主题帖id获取主题帖信息
     * 重写方法
     * @param topicId
     * @return
     * @see com.itany.bbs.service.TopicService#findTopicById(int)
     */
    @Override
    public Topic findTopicById(int topicId)
    {
        return topicdao.findTopicById(topicId);
    }


    /**
     * 修改主题帖
     * 重写方法
     * @param topicId 主题帖id
     * @param title 主题帖标题
     * @param tcontents 主题帖内容
     * @return 成功返回true 失败false
     * @see com.itany.bbs.service.TopicService#modifyTopic(java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public boolean modifyTopic(Integer topicId, String title, String tcontents)
    {
        Topic t = topicdao.findTopicById(topicId);
        if(t == null){
            return false;
        }
        t.setTitle(title);
        t.setTcontents(tcontents);
        Date date = new Date();
        t.setModifydate(date);
        return topicdao.modifyTopic(t);
        
    }

    /**
     * 
     * 重写方法
     * @param topicId
     * @param userId
     * @return
     * @see com.itany.bbs.service.TopicService#deleteTopic(int, int)
     */
    @Override
    public boolean deleteTopic(int topicId, int userId)
    {
        return topicdao.deleteTopic(topicId, userId);
    }
    
    
}
