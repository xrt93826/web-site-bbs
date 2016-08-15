package com.util;

import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * 
 * 密码找回邮件发送
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月21日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class MailSender
{
    public static boolean sendMail(String mailaddr,String newpass,String username) {
        //  取回密码
        //  MD5加密：接受用户名和email，验证通过后随机产生密码，修改数据库，同时将新密码发送给用户邮箱
        
        try
        {
            //发送邮件步骤
            //1.创建SimpleEmail
            SimpleEmail email=new SimpleEmail();
            //2.设置邮件发送服务器*
            email.setHostName("smtp.itany.com");
            //3.设置收件人*、抄送、暗送等
            email.addTo(mailaddr,username);
            //4.设置发件人
            email.setFrom("teacher1@itany.com","Teacher Will");
            //5.设置发件人用户身份*
            email.setAuthentication("teacher1", "12345678");
            //6.设置邮件标题*
            email.setSubject("论坛密码找回");
            //7.设置邮件内容（加编码）
            email.setCharset("UTF-8");
            email.setMsg("新密码："+newpass);
            //8.设置邮件发送时间等
            email.setSentDate(new Date());
            //9.发送
            email.send();
          
        }
        catch (EmailException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
