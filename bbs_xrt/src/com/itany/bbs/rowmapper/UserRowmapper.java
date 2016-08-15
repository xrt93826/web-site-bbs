package com.itany.bbs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.itany.bbs.entity.User;
/**
 * 
 * User类的Rowmapper
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Component
public class UserRowmapper implements RowMapper<User>
{

    @Override
    public User mapRow(ResultSet rs, int arg1)
        throws SQLException
    {
        User one = new User();
        one.setUserid(rs.getInt("userid"));
        one.setLoginname(rs.getString("loginname"));
        one.setLoginpwd(rs.getString("loginpwd"));
        one.setEmail(rs.getString("email"));
        one.setHead(rs.getString("head"));
        one.setRegtime(rs.getDate("regtime"));
        one.setState(rs.getInt("state"));
        one.setPoint(rs.getInt("point"));
        return one;
    }
    
}
