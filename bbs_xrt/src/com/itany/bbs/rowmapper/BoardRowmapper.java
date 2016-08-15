package com.itany.bbs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.itany.bbs.entity.Board;
/**
 * 
 * Board类的Rowmapper
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Component
public class BoardRowmapper implements RowMapper<Board>
{

    @Override
    public Board mapRow(ResultSet rs, int arg1)
        throws SQLException
    {
        Board one = new Board();
        one.setBoardId(rs.getInt("boardId"));
        one.setBoardName(rs.getString("boardName"));
        one.setCreatorId(rs.getInt("creatorId"));
        one.setMasterId(rs.getInt("masterId"));
        one.setCreatetime(rs.getTimestamp("createtime"));
        one.setParentId(rs.getInt("parentId"));
        one.setPoint(rs.getInt("point"));
        one.setState(rs.getInt("state"));
        return one;
    }
    
}
