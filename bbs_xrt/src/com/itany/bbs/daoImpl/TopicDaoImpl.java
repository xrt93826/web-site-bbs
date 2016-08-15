package com.itany.bbs.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.itany.bbs.dao.TopicDao;
import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.rowmapper.TopicRowmapper;

/**
 * 
 * 论坛主题帖相关数据访问接口的实现
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月18日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Repository
public class TopicDaoImpl implements TopicDao
{
    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private TopicRowmapper rowMapper;
    
    /**
     * 根据版块id查找最新发布的主题帖：如果没有则返回null
     * 
     * @param boardId 版块id
     * @return
     */
    @Override
    public Topic findLastTopicByBoard(int boardId)
    {
        String sql = "SELECT * FROM TOPICS WHERE boardid = ? AND state = 1 ORDER BY postdate DESC LIMIT 0,1";
        List<Topic> list = this.template.query(sql, new Object[] {boardId}, rowMapper);
        return list.size() == 0 ? null : list.get(0);
    }
    
    /**
     * 根据版块id分页查询该版块的主题帖
     * 
     * @param boardId 版块id
     * @param page 分页对象
     * @return
     */
    @Override
    public List<Topic> findTopicByPage(int boardId, Paginate page)
    {
        String sql = "SELECT * FROM TOPICS WHERE boardid = ? AND state = 1 ORDER BY postdate DESC LIMIT ?,?";
        Integer start = (page.getPageNo() - 1) * page.getPageSize();
        List<Topic> list = this.template.query(sql, new Object[] {boardId, start, page.getPageSize()}, rowMapper);
        return list;
    }
    
    /**
     * 根据版块id获取该版块的帖子数
     * 
     * @param boardId 版块id
     * @return
     */
    @Override
    public int getTopicCount(int boardId)
    {
        String sql = "SELECT COUNT(*) FROM TOPICS WHERE boardid=? AND state = 1";
        Integer count = this.template.queryForObject(sql, new Object[] {boardId}, Integer.class);
        return count;
    }
    
    /**
     * 根据主题帖id获取主题帖信息
     * 
     * @param topicId 主题帖id
     * @return 主题帖对象
     */
    @Override
    public Topic findTopicById(int topicId)
    {
        String sql = "SELECT * FROM TOPICS WHERE topicId = ? AND state = 1";
        List<Topic> list = this.template.query(sql, new Object[] {topicId}, rowMapper);
        return list.size() == 0 ? null : list.get(0);
    }
    
    /**
     * 添加主题帖，用于用户发帖
     * 
     * @param t 主题帖对象
     * @return 发帖成功返回新帖id，失败返回0
     */
    @Override
    public int addTopic(Topic t)
    {
        KeyHolder kh = new GeneratedKeyHolder();
        final String sql = "INSERT INTO TOPICS(title,tcontents,treplycontents,userid,boardid,postdate,modifydate) value(?,?,?,?,?,?,?)";
        final Object param[] = {t.getTitle(), t.getTcontents(), t.getTreplycontents(), t.getUserid(), t.getBoardid(), t.getPostdate(), t.getModifydate()};
        // 执行sql语句，若增加帖子失败，则处理异常并返回0
        try
        {
            this.template.update(new PreparedStatementCreator()
            {
                
                @Override
                public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException
                {
                    PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    for (int i = 0; i < param.length; i++)
                    {
                        ps.setObject(i + 1, param[i]);
                    }
                    return ps;
                }
            }, kh);
            return ((Long)kh.getKey()).intValue();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * 修改主题帖
     * 
     * @param t 主题帖对象
     * @return true-修改成功；false-失败
     */
    @Override
    public boolean modifyTopic(Topic t)
    {
        String sql = "UPDATE TOPICS SET title = ?,tcontents = ?,modifydate = ? WHERE topicid = ?";
        // 执行update ，若得到0，说明修改失败，返回false
        return this.template.update(sql, new Object[] {t.getTitle(), t.getTcontents(), t.getModifydate(), t.getTopicId()}) == 0 ? false : true;
        
    }
    
    /**
     * 删除主题帖
     * 
     * @param topicId 主题帖id
     * @param userId 用户id
     * @return true-删除成功；false-失败
     */
    @Override
    public boolean deleteTopic(int topicId, int userId)
    {
        String sql = "UPDATE TOPICS SET state = 0 WHERE topicId = ? and userid = ?";
        // 执行update ，若得到0，说明删除失败，返回false
        return this.template.update(sql, new Object[] {topicId, userId}) == 0 ? false : true;
        
    }
    
}
