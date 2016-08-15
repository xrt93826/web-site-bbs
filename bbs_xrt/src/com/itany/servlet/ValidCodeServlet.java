package com.itany.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 验证码图片生成工具类
 * 
 * @author 杨卫兵
 * @version [V1.00, 2016-6-5]
 * @since V1.00
 */
public class ValidCodeServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        BufferedImage image = 
            new BufferedImage(100, 36, BufferedImage.TYPE_INT_RGB);
        Graphics gra = image.getGraphics();
        gra.setColor(Color.yellow); // 设置背景色
        gra.fillRect(2, 2, 96, 32); // 绘制边框
        //4位，[0-9a-zA-Z]
        String code="";
        Random rnd=new Random();
        for (int i = 0; i < 4; i++)
        {
            int n=rnd.nextInt(62);
            if(n<10){//数字
                code+=n;
            }
            else if(n<36){//小写字母
                code+=(char)(n-10+'a');
            }
            else{//大写字母
                code+=(char)(n-36+'A');
            }
        }
        //存放到session作用域
        HttpSession se=request.getSession();
        se.setAttribute("code", code);
        
        //设置字体
        gra.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,24));
        //循环输出每一个字符，颜色随机产生
        for (int i = 0; i < 4; i++)
        {
            gra.setColor(new Color(
                rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256)));
            
            gra.drawString(code.substring(i,i+1), 15+i*20, 25);
        }
        //添加干扰线
        for (int i = 0; i < 5; i++)
        {
            gra.setColor(new Color(
                rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256)));
            //使用Graphics2D加粗线宽
            Graphics2D g2d=(Graphics2D)gra;
            g2d.setStroke(
                new BasicStroke(rnd.nextFloat()+rnd.nextInt(4)));
            gra.drawLine(rnd.nextInt(100),rnd.nextInt(36),
                rnd.nextInt(100),rnd.nextInt(36));
        }

        response.setContentType("image/jpeg");
        OutputStream out=response.getOutputStream(); 
        JPEGImageEncoder encoder = 
            JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
        out.close();
    }
    
}
