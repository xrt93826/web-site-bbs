package com.itany.bbs.dao;

import java.util.List;

import com.itany.bbs.entity.User;

/**
 * 
 * 论坛用户相关数据访问接口
 * 
 * @author 杨卫兵
 * @version [V1.00, 2016年6月14日]
 * @since V1.00
 */
public interface UserDao
{
    /**
     * 用于登录时验证用户：验证成功返回存放用户信息的User对象，否则返回null
     * @param name  用户名
     * @param pass  密码
     * @return  User对象或null
     */
    User isValidUser(String name,String pass);
    
    /**
     * 添加用户，用于注册
     * @param user
     * @return 新注册用户id
     */
    int addUser(User user);
    
    /**
     * 验证用户名是否存在：如存在返回该用户id
     * @param name 用户名
     * @return  大于0-存在，0-不存在
     */
    int findIdByUserName(String name);
    
    /**
     * 验证邮箱是否存在：如存在返回该用户id
     * @param email 用户名
     * @return  大于0-存在，0-不存在
     */
    int findIdByEmail(String email);
    
    /**
     * 根据用户id查找用户对象：如找不到返回null
     * @param userId  用户id
     * @return  用户对象
     */
    User findUser(int userId);
    
    /**
     * 可选：根据用户名和Email获取用户信息，用于取回密码
     */
    User findUserByEmail(String name,String email);
    
    /**
     * 可选：增加用户积分
     */
    boolean addUserPoint(int userId, int addPoint);
    
    /**
     * 可选：删除用户
     */
    boolean delUser(int userId);
    
    /**
     * 可选：修改用户状态
     */
    boolean modifyUserState(int userId, int state);
    
    /**
     * 可选：分页列表所有用户
     */
    List<User> findUsersOrder(String order, boolean isASC, boolean isPaginate, int pageSize, int pageNo);
    
    /**
     * 可选：根据用户名模糊查找用户
     */
    List<User> findUsersByName(String name, int start, int limit);
    
    
    /**
     * 
     * 修改用户密码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean modifyLoginPwd(User user);
}
