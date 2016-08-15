package com.itany.bbs.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itany.bbs.dao.BoardDao;
import com.itany.bbs.dao.TopicDao;
import com.itany.bbs.dao.UserDao;
import com.itany.bbs.entity.Board;
import com.itany.bbs.entity.Topic;
import com.itany.bbs.entity.User;
import com.itany.bbs.pojo.BoardInfo;
import com.itany.bbs.pojo.TopicInfo;
import com.itany.bbs.service.BoardService;

/**
 * 
 * 版块业务逻辑实现
 * 
 * @author 熊睿滔
 * @version [V1.00, 2016年7月20日]
 * @see [相关类/方法]
 * @since V1.00
 */
@Service
public class BoardServiceImpl implements BoardService
{
    @Autowired
    private BoardDao boarddao;
    
    @Autowired
    private TopicDao topicdao;
    
    @Autowired
    private UserDao userdao;
    
    /**
     * 
     * 重写方法
     * 
     * @return 所有版块的Map对象
     * @see com.itany.bbs.service.BoardService#findAllBoard()
     */
    @Override
    public Map<Integer, List<Board>> findAllBoard()
    {
        return boarddao.findAllBoard();
    }
    
    /**
     * 
     * 重写方法
     * 
     * @param id 版块id
     * @return 查找到的Board对象
     * @see com.itany.bbs.service.BoardService#getBoardById(int)
     */
    @Override
    public Board getBoardById(int id)
    {
        return boarddao.getBoardById(id);
    }
    
    /**
     * 
     * 重写方法
     * 
     * @return 首页显示的所有版块信息
     * @see com.itany.bbs.service.BoardService#getAllBoardInfo()
     */
    @Override
    public List<BoardInfo> getAllBoardInfo()
    {
        Map<Integer, List<Board>> map0 = boarddao.findAllBoard();
        //迭代器遍历map
//        Iterator<Entry<Integer, List<Board>>> it = map0.entrySet().iterator();
//        
//        Entry<Integer, List<Board>> entry = null;
//        Integer parentId = null;
        List<Board> parentlist = null;
        List<Board> childlist = null;
        BoardInfo parentbi = null;
        BoardInfo childbi = null;
        List<BoardInfo> list = new ArrayList<BoardInfo>();
//        Map<Integer, List<BoardInfo>> map = new HashMap<Integer, List<BoardInfo>>();
        Integer count = null;
        Topic topic = null;
        TopicInfo ti = null;
        String name = null;
        //取得父版块
        parentlist = map0.get(0);
        for(Board parentBoard : parentlist){
            parentbi = new BoardInfo(parentBoard.getBoardId(),parentBoard.getParentId(), parentBoard.getBoardName(), null, null);
            list.add(parentbi);
            childlist = map0.get(parentBoard.getBoardId());
            for(Board childBoard : childlist){
                count = topicdao.getTopicCount(childBoard.getBoardId());
                topic = topicdao.findLastTopicByBoard(childBoard.getBoardId());
                if(null == topic){
                    ti = null;
                }
                else{
                    name = userdao.findUser(topic.getUserid()).getLoginname();
                    ti = new TopicInfo(topic.getTopicId(), topic.getTitle(), name, topic.getModifydate());
                }
                childbi = new BoardInfo(childBoard.getBoardId(),childBoard.getParentId(), childBoard.getBoardName(), count, ti);
                list.add(childbi);
            }
        }
        
        
//        while (it.hasNext())
//        {
//            list = new ArrayList<BoardInfo>();
//            entry = it.next();
//            parentId = entry.getKey();
//            list0 = entry.getValue();
//            
//            //存入父版块信息
//            if(parentId == 0){
//                for(Board one : list0){
//                    bi = new BoardInfo(one.getBoardId(), one.getBoardName(), null, null);
//                    list.add(bi);
//                    
//                }
//            }else{//存入子版块信息
//                for(Board one : list0){
//                    count = topicdao.getTopicCount(one.getBoardId());
//                    topic = topicdao.findLastTopicByBoard(one.getBoardId());
//                    if(null == topic){
//                        bi = new BoardInfo(one.getBoardId(),one.getBoardName(), count,null, null, null, null);
//                    }else{
//                        name = userdao.findUser(topic.getUserid()).getLoginname();
//                        bi = new BoardInfo(one.getBoardId(),one.getBoardName(), count, topic.getTopicId(),topic.getTitle(), name, topic.getModifydate());
//                    }
//                    
//                    
//                    list.add(bi);
//                }
//            }
//            map.put(parentId,list);
//        }
       
        return list;
        
    }


    @Override
    public List<Board> getBoards(int pid)
    {
        return boarddao.getBoards(pid);
    }

    @Override
    public boolean exists(String name)
    {
        return boarddao.exists(name);
    }

    @Override
    public int addParentBoard(String boardName, User use)
    {
        Board board = new Board(0, boardName, use.getUserid(), use.getUserid(), new Date(), 0, null, null);
        return boarddao.addBoard(board);
    }

    @Override
    public int addChildBoard(String boardName, int parentId, User use)
    {
        Board board = new Board(0, boardName, use.getUserid(), use.getUserid(), new Date(), parentId, null, null);
        return boarddao.addBoard(board);
    }
    
}
