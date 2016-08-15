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

import com.itany.bbs.dao.ReplyDao;
import com.itany.bbs.entity.Paginate;
import com.itany.bbs.entity.Reply;
import com.itany.bbs.rowmapper.ReplyRowmapper;

/**
 * 
 * 论坛回复帖相关数据访问接口的实现
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月19日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Repository
public class ReplyDaoImpl implements ReplyDao
{
    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private ReplyRowmapper rowMapper;
    
    /**
     * 根据主题帖id获取回复数量
     * 
     * @param topicId
     * @return
     */
    @Override
    public int getReplyCount(int topicId)
    {
        String sql = "SELECT COUNT(*) FROM REPLYS WHERE topicid=? AND state = 1";
        Integer count = this.template.queryForObject(sql, new Object[] {topicId}, Integer.class);
        return count;
    }
    
    /**
     * 根据主题帖id分页查询回帖列表
     * 
     * @param topicId 主题帖id
     * @param page 分页对象
     * @return
     */
    @Override
    public List<Reply> findReplyByPage(int topicId, Paginate page)
    {
        String sql = "SELECT * FROM REPLYS WHERE topicid = ? AND state = 1 LIMIT ?,?";
        Integer start = (page.getPageNo() - 1) * page.getPageSize();
        List<Reply> list = this.template.query(sql, new Object[] {topicId, start, page.getPageSize()}, rowMapper);
        return list;
    }
    
    /**
     * 修改回复帖
     * 
     * @param t 回复帖对象
     * @return true-修改成功；false-失败
     */
    @Override
    public boolean modifyReply(Reply r)
    {
        String sql = "UPDATE REPLYS SET rtitle = ?,rcontents = ?,modifydate = ? WHERE replyId = ?";
        // 执行update ，若得到0，说明修改失败，返回false
        return this.template.update(sql, new Object[] {r.getRtitle(), r.getRcontents(), r.getModifydate(), r.getReplyId()}) == 0 ? false : true;
    }
    
    /**
     * 删除回复帖
     * 
     * @param replyId 回复帖id
     * @param userId 用户id
     * @return true-删除成功；false-失败
     */
    @Override
    public boolean deleteReply(int replyId, int userId)
    {
        String sql = "UPDATE REPLYS SET state = 0 WHERE replyId = ? and userid = ?";
        // 执行update ，若得到0，说明删除失败，返回false
        return this.template.update(sql, new Object[] {replyId, userId}) == 0 ? false : true;
    }
    
    /**
     * 添加回复帖，用于用户回帖
     * 
     * @param t 回复帖对象
     * @return 回帖成功返回新回帖id，失败返回0
     */
    @Override
    public int addReply(Reply r)
    {
        KeyHolder kh = new GeneratedKeyHolder();
        final String sql = "INSERT INTO REPLYS(rtitle,rcontents,userid,topicid,postdate,modifydate) value(?,?,?,?,?,?)";
        final Object param[] = {r.getRtitle(), r.getRcontents(), r.getUserid(), r.getTopicid(), r.getPostdate(), r.getModifydate()};
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
     * 根据回复帖id获取回复帖信息
     * 
     * @param topicId 回复帖id
     * @return 回复帖对象
     */
    @Override
    public Reply findReplyById(int replyId)
    {
        String sql = "SELECT * FROM REPLYS WHERE replyId = ? AND state = 1";
        List<Reply> list = this.template.query(sql, new Object[] {replyId}, rowMapper);
        return list.size() == 0 ? null : list.get(0);
    }
    
}
