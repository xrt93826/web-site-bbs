package com.itany.bbs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.itany.bbs.entity.Reply;

/**
 * 
 * Reply类的Rowmapper
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月19日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Component
public class ReplyRowmapper implements RowMapper<Reply>
{

    @Override
    public Reply mapRow(ResultSet rs, int arg1)
        throws SQLException
    {
        Reply one = new Reply();
        one.setReplyId(rs.getInt("replyId"));
        one.setRtitle(rs.getString("rtitle"));
        one.setRcontents(rs.getString("rcontents"));
        one.setRface(rs.getInt("rface"));
        one.setUserid(rs.getInt("userid"));
        one.setTopicid(rs.getInt("topicid"));
        one.setPostdate(rs.getTimestamp("postdate"));
        one.setModifydate(rs.getTimestamp("modifydate"));
        one.setState(rs.getInt("state"));
        return one;
    }
    
}
