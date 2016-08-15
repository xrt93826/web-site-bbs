package com.itany.bbs.entity;

/**
 * 用于处理分页信息的实体对象
 * 
 * @author 杨卫兵
 * @version [V1.00, 2016年6月14日]
 * @since V1.00
 */
public class Paginate
{
    //页号    
    private int pageNo;
    //每页记录数    
    private int pageSize;
    //最大页号
    private int maxPageNo;
    //总记录数
    private int records;
    
    public int getPageNo()
    {
        return pageNo;
    }
    
    public void setPageNo(int pageNo)
    {
        if (pageNo < 1)
            this.pageNo = 1;
        else
            this.pageNo = pageNo;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        if (pageSize < 3)
            this.pageSize = 3;
        else
            this.pageSize = pageSize;
    }
    
    public int getRecords()
    {
        return records;
    }
    
    public void setRecords(int records)
    {
        if (records < 0)
            this.records = 0;
        else
            this.records = records;
    }
    
    public int getMaxPageNo()
    {
        return maxPageNo;
    }
    
    public void calc()
    {
        this.maxPageNo = (records + pageSize - 1) / pageSize;
        if (pageNo > maxPageNo)
            pageNo = maxPageNo;
        if (pageNo < 1)
            this.pageNo = 1;
    }
}
