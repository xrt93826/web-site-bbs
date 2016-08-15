package com.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * XML工具类，使用JDOM
 * 
 * @author 杨卫兵
 * @version [V1.00, 2016-6-5]
 * @since V1.00
 */
public class XMLUtil
{
    public static void main(String[] args)
    {
    }
    
    /**
     * 根据ResultSet生成一个XML的Document对象
     * 使用指定的rootName作为根元素的名称
     * 使用指定的subName作为每条记录所对应的子元素的名称
     * 如未提供idColName(==null or =="")则忽略；
     * 如提供该参数,则将与该参数同名的字段作为子元素的属性写入XML中
     * 
     * @param rs 结果集
     * @param rootName 根元素名称
     * @param subName 子元素名称(对应每条记录)
     * @param idColName Id属性列名称
     * @return XML Document对象
     */
    public static Document generateXMLFromResultSet(ResultSet rs, String rootName, String subName, String idColName)
    {
        ResultSetMetaData rsmd = null;
        Document doc = null;
        try
        {
            doc = new Document();
            rsmd = rs.getMetaData();
            Element root = new Element(rootName);
            doc.addContent(root);
            while (rs.next())
            {
                // 每遍历到一条记录生成一个子元素
                Element sub = new Element(subName);
                root.addContent(sub);
                // 遍历所有列
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    // 获取列名
                    String colName = rsmd.getColumnName(i);
                    // 获取当前记录的该字段值
                    String value = rs.getString(colName);// null
                    // 如果列名与参数idColName同名，设置为子元素的属性
                    if (colName.equalsIgnoreCase(idColName))
                    {
                        sub.setAttribute(idColName, value);
                    }
                    else
                    {// 否则作为孙元素
                        Element elm = new Element(colName);
                        elm.setText(value);
                        sub.addContent(elm);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return doc;
    }
    
    /**
     * 输出方法
     * 
     * @param doc XML Document对象
     * @param os 输出流
     * @return true--成功；false--失败
     */
    public static boolean output(Document doc, OutputStream os)
    {
        XMLOutputter out=null;
        try
        {
            out=new XMLOutputter();
            out.output(doc, os);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 根据List生成一个XML的Document对象
     * 使用指定的rootName作为根元素的名称
     * 使用指定的subName作为集合中每个数据所对应的子元素的名称
     * 如果未提供subName则使用该数据的简单类型名称作为子元素的名称
     * 如未提供idColName(==null or =="")则忽略；
     * 如提供该参数,则将与该参数同名的属性作为子元素的属性写入XML中
     * 
     * @param data List集合
     * @param rootName 根元素名称
     * @param subName 子元素名称(对应每条记录)
     * @param idName Id属性名称
     * @return XML Document对象
     */
    public static Document generateXMLFromList(List data, String rootName, String subName, String idName)
    {
        Document doc = null;
        try
        {
            doc = new Document();
            Element root = new Element(rootName);
            doc.addContent(root);
            // 遍历集合数据
            for (int i = 0; data != null && i < data.size(); i++)
            {
                // 获取数据
                Object obj = data.get(i);
                Element sub=createElement(obj, subName, idName);
                root.addContent(sub);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return doc;
    }
    
    /**
     * 根据JavaBean对象生成一个XML的Document对象
     * 使用指定的rootName作为根元素的名称
     * 如果未提供rootName则使用JavaBean对象的简单类型名称作为根元素的名称
     * 如未提供idColName(==null or =="")则忽略；
     * 如提供该参数,则将与该参数同名的属性作为子元素的属性写入XML中
     * 
     * @param obj JavaBean对象
     * @param rootName 根元素名称
     * @param idName Id属性名称
     * @return XML Document对象
     */
    public static Document generateXMLFromBean(Object obj, String rootName, String idName)
    {
        if (obj == null)
            return null;
        Document doc = null;
        try
        {
            doc = new Document();
            Element sub=createElement(obj, rootName, idName);
            doc.addContent(sub);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return doc;
    }
    
    private static Element createElement(Object obj, String elmName, String idName)
    {
        Element elm = null;
        try
        {
            Class clz = obj.getClass();
            // 获取元素名称
            String currElmName = elmName;
            // 如果未提供元素名，则使用类型的简单名
            if (currElmName == null || "".equals(currElmName))
            {
                currElmName = clz.getSimpleName();
            }
            //创建元素
            elm = new Element(currElmName);
            // 为元素添加属性或子元素
            Method[] mths = clz.getMethods();
            for (int j = 0; j < mths.length; j++)
            {
                Method mth = mths[j];
                String mthName = mth.getName();
                // 判断是否为getter
                if (mthName.startsWith("get") || mthName.startsWith("is"))
                {
                    String fldName = "";
                    if (mthName.startsWith("get") && !mthName.equals("getClass"))
                    {
                        fldName = mthName.substring(3);
                    }
                    else if (mthName.startsWith("is"))
                    {
                        fldName = mthName.substring(2);
                    }
                    if (!fldName.equals(""))
                    {
                        // 获取getter所对应的属性名
                        fldName = fldName.substring(0, 1).toLowerCase() + fldName.substring(1);
                        // 获取该属性的值，无论何类型，全部转为String
                        String value = mth.invoke(obj) + "";
                        // 判断是否作为属性
                        if (fldName.equalsIgnoreCase(idName))
                        {
                            elm.setAttribute(idName, value);
                        }
                        else
                        {// 否则作为孙元素
                            Element subElm = new Element(fldName);
                            subElm.setText(value);
                            elm.addContent(subElm);
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return elm;
    }
}
