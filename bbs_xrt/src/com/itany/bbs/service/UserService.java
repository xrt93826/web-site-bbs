package com.itany.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itany.bbs.entity.User;


/**
 * 
 * 登录的业务逻辑接口
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
@Service
public interface UserService
{
    /**
     * 
     * 验证用户输入的登录名密码的正确性
     * 
     * @param loginname 用户输入的登录名
     * @param loginpwd 用户输入的登录密码
     * @return 返回null或者User对象
     * @see [类、类#方法、类#成员]
     */
    public User verifyLoginInfo(String loginname,String loginpwd);
    
    /**
     * 
     * 实现用户注册
     * 
     * @param regname 注册名
     * @param regpwd 注册密码
     * @param regpwdr 重复注册密码
     * @param email 邮箱
     * @param head 头像
     * @return 返回非0则注册成功，返回0则表示用户名已被注册
     * @see [类、类#方法、类#成员]
     */
    public int doRegister(String regname,String regpwd,String regpwdr,String email,String head);
    
    /**
     * 
     * 核对信息，发送找回密码邮件
     * 
     * @param loginname 登录名
     * @param email email
     * @return 信息核对成功返回true
     * @see [类、类#方法、类#成员]
     */
    public boolean findLoginPwd(String loginname,String email);
    
    /**
     * 
     * 验证用户名是否存在
     * 
     * @param name
     * @return 不存在返回0，存在返回id
     * @see [类、类#方法、类#成员]
     */
    public int validateUserName(String name);
    
    /**
     * 验证邮箱是否存在
     * 
     * 
     * @param email
     * @return 不存在返回0，存在返回id
     * @see [类、类#方法、类#成员]
     */
    public int validateEmail(String email);
    
    public boolean delUser(int userId);
    
    public boolean modifyUserState(int userId, int state);
    
    public List<User> findUsersOrder(String order, boolean isASC, boolean isPaginate, int pageSize, int pageNo);
}
