package com.itany.bbs.dao;

import java.util.List;
import java.util.Map;

import com.itany.bbs.entity.Board;

/**
 * 
 * 论坛版块相关数据访问接口
 * 
 * @author 杨卫兵
 * @version [V1.00, 2016年6月14日]
 * @since V1.00
 */
public interface BoardDao
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
     * 可选：添加版块
     */
    int addBoard(Board board);
    
    /**
     * 可选：根据父版块id查找子版块
     */
    List<Board> getBoards(int pid);
    
    /**
     * 可选：查找指定版块名是否存在
     */
    boolean exists(String name);
    
    /**
     * 可选：修改版块状态
     */
    boolean modifyState(int id, int newState, int oldState);
    
    /**
     * 可选：修改版块信息
     */
    boolean modifyBoard(Board board);
    
    /**
     * 可选：判断指定id的版块是否为一级版块
     */
    boolean isFirst(int id);
}
