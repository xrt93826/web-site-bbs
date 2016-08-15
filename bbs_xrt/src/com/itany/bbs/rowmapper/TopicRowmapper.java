package com.itany.bbs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.itany.bbs.entity.Topic;

/**
 * 
 * Topic的Rowmapper
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Component
public class TopicRowmapper implements RowMapper<Topic>
{

    @Override
    public Topic mapRow(ResultSet rs, int arg1)
        throws SQLException
    {
        Topic one = new Topic();
        one.setTopicId(rs.getInt("topicId"));
        one.setTitle(rs.getString("title"));
        one.setTcontents(rs.getString("tcontents"));
        one.setTreplycontents(rs.getString("treplycontents"));
        one.setTface(rs.getInt("tface"));
        one.setIsreply(rs.getInt("isreply"));
        one.setReadpoint(rs.getInt("readpoint"));
        one.setAccesspoint(rs.getInt("accesspoint"));
        one.setUserid(rs.getInt("userid"));
        one.setBoardid(rs.getInt("boardid"));
        one.setPostdate(rs.getTimestamp("postdate"));
        one.setModifydate(rs.getTimestamp("modifydate"));
        one.setState(rs.getInt("state"));
        return one;
    }
    
}
