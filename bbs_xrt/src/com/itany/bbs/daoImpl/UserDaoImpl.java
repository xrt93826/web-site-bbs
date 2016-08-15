package com.itany.bbs.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.itany.bbs.dao.UserDao;
import com.itany.bbs.entity.User;

/**
 * 
 * 论坛用户相关数据访问接口的实现
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月18日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Repository("userdao")
public class UserDaoImpl implements UserDao
{
    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private RowMapper<User> rowMapper;
    
    /**
     * 
     * 用于登录时验证用户：验证成功返回存放用户信息的User对象，否则返回null
     * <功能详细描述>
     * 
     * @param name 用户名
     * @param pass 密码
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public User isValidUser(String name, String pass)
    {
        // 通过姓名从数据库中查找该用户
        String sql = "SELECT * FROM USERS WHERE loginname = ? and loginpwd = ?";
        List<User> list = this.template.query(sql, new Object[] {name, pass}, rowMapper);
        // 如果list的size为0，说明没找到该用户则返回null,否则返回得到的对象
        return list.size() == 0 ? null : list.get(0);
    }
    
    /**
     * 
     * 添加用户，用于注册
     * <功能详细描述>
     * 
     * @param user 新注册用户的对象
     * @return 新注册用户id
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int addUser(User user)
    {
        KeyHolder kh = new GeneratedKeyHolder();
        final String sql = "INSERT INTO USERS(loginname,loginpwd,email,head,regtime) value(?,?,?,?,?)";
        final Object param[] = {user.getLoginname(), user.getLoginpwd(), user.getEmail(), user.getHead(), user.getRegtime()};
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
    
    /**
     * 
     * 验证用户名是否存在：如存在返回该用户id
     * <功能详细描述>
     * 
     * @param name 用户名
     * @return 大于0-存在，0-不存在
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int findIdByUserName(String name)
    {
        String sql = "SELECT * FROM USERS WHERE loginname = ?";
        List<User> list = this.template.query(sql, new Object[] {name}, rowMapper);
        return list.size() == 0 ? 0 : list.get(0).getUserid();
    }
    
    /**
     * 
     * 根据用户id查找用户对象：如找不到返回null
     * <功能详细描述>
     * 
     * @param userId 用户id
     * @return 用户对象
     * @see [类、类#方法、类#成员]
     */
    @Override
    public User findUser(int userId)
    {
        String sql = "SELECT * FROM USERS WHERE userid = ?";
        List<User> list = this.template.query(sql, new Object[] {userId}, rowMapper);
        return list.size() == 0 ? null : list.get(0);
    }
    
    /**
     * 根据用户名和Email获取用户信息，用于取回密码
     * 重写方法
     * @param name 用户名
     * @param email 
     * @return 返回改用户
     * @see com.itany.bbs.dao.UserDao#findUserByEmail(java.lang.String, java.lang.String)
     */
    @Override
    public User findUserByEmail(String name, String email)
    {
        String sql = "SELECT * FROM USERS WHERE loginname = ? AND email = ?";
        List<User> list = this.template.query(sql, new Object[] {name,email}, rowMapper);
        return list.size() == 0 ? null : list.get(0);
    }
    
    @Override
    public boolean addUserPoint(int userId, int addPoint)
    {
        return false;
    }
    
    @Override
    public boolean delUser(int userId)
    {
        String sql = "UPDATE USERS SET state = 0 WHERE userid = ?";
        return this.template.update(sql, userId) == 0 ? false : true;
    }
    
    @Override
    public boolean modifyUserState(int userId, int state)
    {
        String sql = "UPDATE USERS SET state = ? WHERE userid = ?";
        return this.template.update(sql, new Object[] {state,userId}) == 0 ? false : true;
    }
    
    @Override
    public List<User> findUsersOrder(String order, boolean isASC, boolean isPaginate, int pageSize, int pageNo)
    {
        String sql="SELECT * FROM USERS";
        List<User> list = template.query(sql, rowMapper);
        return list.size() == 0 ? null : list;
    }
    
    @Override
    public List<User> findUsersByName(String name, int start, int limit)
    {
        return null;
    }

    
    /**
     * 修改用户密码
     * 重写方法
     * @return
     * @see com.itany.bbs.dao.UserDao#modifyLoginPwd()
     */
    @Override
    public boolean modifyLoginPwd(User user)
    {
        String sql = "UPDATE USERS SET loginpwd = ? WHERE userid = ?";
        return this.template.update(sql, new Object[] {user.getLoginpwd(),user.getUserid()}) == 0 ? false : true;
    }

    /**
     * 
     * 重写方法
     * @param email
     * @return
     * @see com.itany.bbs.dao.UserDao#findIdByEmail(java.lang.String)
     */
    @Override
    public int findIdByEmail(String email)
    {
        String sql = "SELECT * FROM USERS WHERE email = ?";
        List<User> list = this.template.query(sql, new Object[] {email}, rowMapper);
        return list.size() == 0 ? 0 : list.get(0).getUserid();
    }
    
}
