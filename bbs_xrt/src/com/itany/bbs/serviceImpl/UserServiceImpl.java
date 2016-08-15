package com.itany.bbs.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itany.bbs.dao.UserDao;
import com.itany.bbs.entity.User;
import com.itany.bbs.service.UserService;
import com.util.MD5Util;
import com.util.MailSender;
import com.util.PwdMaker;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao dao;
    
    /**
     * 验证用户输入的登录名密码的正确性
     * 重写方法
     * @param loginname 用户输入的登录名
     * @param loginpwd 用户输入的登录密码
     * @return 返回null或者User对象
     * @see com.itany.bbs.service.UserService#verifyLoginInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public User verifyLoginInfo(String loginname, String loginpwd)
    {
        String password = MD5Util.getMD5(loginpwd);
        if(dao.findIdByUserName(loginname)==0 || dao.isValidUser(loginname, password) == null){
            return null;
          //登录成功，返回User对象
        }else{
            return dao.isValidUser(loginname, password);
        }
    }

    /**
     * 实现用户注册
     * 重写方法
     * @param regname 注册名
     * @param regpwd 注册密码
     * @param regpwdr 重复注册密码
     * @param email 邮箱
     * @param head 头像
     * @return 返回非0则注册成功，返回0则表示用户名已被注册
     * @see com.itany.bbs.service.UserService#doRegister(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int doRegister(String regname, String regpwd, String regpwdr, String email, String head)
    {
        Date regtime = new Date();
        String headaddr = "imgs/head/"+head+".gif";
        String password = MD5Util.getMD5(regpwd);
        User user = new User(0, regname, password, email, headaddr, regtime, 0, 0);
        return dao.addUser(user);
    }

    
    
    /**
     * 
     * 核对信息，发送找回密码邮件
     * 
     * @param loginname 登录名
     * @param email email
     * @return 信息核对成功返回true
     * @see com.itany.bbs.service.UserService#findLoginPwd(java.lang.String, java.lang.String)
     */
    @Override
    public boolean findLoginPwd(String loginname, String email)
    {
        User user = dao.findUserByEmail(loginname, email);
        if(null == user){
            return false;
        }
        
        String newpass = PwdMaker.getRandomString(6);
        
        String md5 = MD5Util.getMD5(newpass);
        
        user.setLoginpwd(md5);
        
        dao.modifyLoginPwd(user);
        
        return MailSender.sendMail(email, newpass, loginname);
         
    }

    
    /**
     * 验证用户是否存在
     * 重写方法
     * @param name
     * @return 不存在返回0，存在返回id
     * @see com.itany.bbs.service.UserService#validateUserName(java.lang.String)
     */
    @Override
    public int validateUserName(String name)
    {
        return dao.findIdByUserName(name);
    }

    
    /**
     * 验证邮箱是否存在
     * 重写方法
     * @param email
     * @return 不存在返回0，存在返回id
     * @see com.itany.bbs.service.UserService#validateEmail(java.lang.String)
     */
    @Override
    public int validateEmail(String email)
    {
        return dao.findIdByEmail(email);
    }

    @Override
    public boolean delUser(int userId)
    {
        return dao.delUser(userId);
    }

    @Override
    public boolean modifyUserState(int userId, int state)
    {
        return dao.modifyUserState(userId, state);
    }

    @Override
    public List<User> findUsersOrder(String order, boolean isASC, boolean isPaginate, int pageSize, int pageNo)
    {
        return dao.findUsersOrder(order, isASC, isPaginate, pageSize, pageNo);
    }
    
}
