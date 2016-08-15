package com.itany.bbs.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.itany.bbs.dao.BoardDao;
import com.itany.bbs.entity.Board;
import com.itany.bbs.entity.User;
import com.itany.bbs.rowmapper.BoardRowmapper;

/**
 * 
 * 论坛版块相关数据访问接口的实现
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月18日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Repository
public class BoardDaoImpl implements BoardDao
{
    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private BoardRowmapper rowMapper;
    
    /**
     * 查找所有版块：Map中以父版块id为key，子版块列表为value；一级版块的key固定为0
     * 
     * @return 所有版块的映射集合
     */
    @Override
    public Map<Integer, List<Board>> findAllBoard()
    {
        Map<Integer, List<Board>> map = new HashMap<Integer, List<Board>>();
        //查询所有父版块
        String sql0 = "SELECT * FROM BOARDS WHERE parentId = 0 and state = 1";
        List<Board> list0 = this.template.query(sql0, rowMapper);
        //将父版块放入map
        map.put(0,list0);
        //遍历父版块，查询每个父版块下的子版块
        for (Board one : list0)
        {
            //依次查找指定父版块中的子版块
            String sql = "SELECT * FROM BOARDS WHERE parentId = ? and state = 1";
            List<Board> list = this.template.query(sql, new Object[]{one.getBoardId()}, rowMapper);
            //将父版块的id和其子版块列表存入map
            map.put(one.getBoardId(), list);
            
        }
        return map;
    }
    
    /**
     * 根据版块id查找版块信息
     * @param id    版块id
     * @return      版块对象
     */
    @Override
    public Board getBoardById(int id)
    {
        String sql = "SELECT * FROM BOARDS WHERE boardId = ? and state = 1";
        List<Board> list = this.template.query(sql, new Object[] {id}, rowMapper);
        return list.size() == 0 ? null : list.get(0);
    }
    
    /**
     * 
     * 重写方法
     * @param board 需要增加的board对象
     * @return 增加成功返回新版块id，失败返回0
     * @see com.itany.bbs.dao.BoardDao#addBoard(com.itany.bbs.entity.Board)
     */
    @Override
    public int addBoard(Board board)
    {
        KeyHolder kh = new GeneratedKeyHolder();
        final String sql="INSERT INTO BOARDS(boardName,creatorId,masterId,createtime,parentId) value(?,?,?,?,?)";
        final Object param[] = {board.getBoardName(), board.getCreatorId(), board.getMasterId(), board.getCreatetime(), board.getParentId()};        
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
     * 根据父版块id查找子版块
     * 重写方法
     * @param pid 父版块id
     * @return 所有子版块集合
     * @see com.itany.bbs.dao.BoardDao#getBoards(int)
     */
    @Override
    public List<Board> getBoards(int pid)
    {
        String sql = "SELECT * FROM BOARDS WHERE parentId = ? and state = 1";
        List<Board> list = this.template.query(sql, new Object[]{pid}, rowMapper);
        return list.size() == 0 ? null : list;
    }
    
    /**
     * 验证版块是否存在
     * 重写方法
     * @param name
     * @return 存在返回true 不存在返回false
     * @see com.itany.bbs.dao.BoardDao#exists(java.lang.String)
     */
    @Override
    public boolean exists(String name)
    {
        String sql = "SELECT * FROM BOARDS WHERE boardName = ? and state = 1";
        List<Board> list = this.template.query(sql, new Object[] {name}, rowMapper);
        return list.size() != 0;
    }
    
    @Override
    public boolean modifyState(int id, int newState, int oldState)
    {
        return false;
    }
    
}
