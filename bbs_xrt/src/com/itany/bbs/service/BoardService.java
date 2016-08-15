package com.itany.bbs.service;

import java.util.List;
import java.util.Map;

import com.itany.bbs.entity.Board;
import com.itany.bbs.entity.User;
import com.itany.bbs.pojo.BoardInfo;

/**
 * 
 * 版块业务逻辑接口
 *  
 * @author  熊睿滔
 * @version  [V1.00, 2016年7月20日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface BoardService
{
    /**
     * 查找所有版块：Map中以父版块id为key，子版块列表为value；一级版块的key固定为0
     * @return 所有版块的映射集合
     */
    Map<Integer, List<Board>> findAllBoard();
    
    /**
     * 根据版块id查找版块信息
     * @param id    版块id
     * @return      版块对象
     */
    Board getBoardById(int id);
    
    /**
     * 
     * 获得首页需要显示的所有版块信息
     * 
     * @return 首页显示的所有版块信息
     * @see [类、类#方法、类#成员]
     */
    List<BoardInfo> getAllBoardInfo();
    
    int addParentBoard(String boardName,User use);
    
    int addChildBoard(String boardName,int parentId,User use);
    
    List<Board> getBoards(int pid);
    
    boolean exists(String name);
}
