package com.util;

import java.util.Random;

/**
 * 
 * 用于产生随机密码
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月21日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class PwdMaker
{
    public static String getRandomString(int length)
    { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
