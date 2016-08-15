package com.util;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

import com.itany.bbs.pojo.BoardInfo;
import com.itany.bbs.pojo.TopicInfo;

public class XMLCreator
{
    public static Document createXMLFromBoardList(List list){
        Document doc=null;
        Element root=null;
        Element board=null;
        Element sub=null;
        Element topicInfo=null;
        BoardInfo bi = null;
        TopicInfo ti = null;
        
        try
        {
            doc=new Document();
            root=new Element("Boards");
            doc.addContent(root);
            
            for(int i=0;i<list.size();i++){
                board=new Element("Board");
                root.addContent(board);
                bi = (BoardInfo)list.get(i);
                board.setAttribute("parentId", bi.getParentId().toString());
                sub = new Element("boardId");
                sub.setText(bi.getBoardId().toString());
                board.addContent(sub);
                sub = new Element("topicCount");
                if(null != bi.getTopicCount()){
                    sub.setText(bi.getTopicCount().toString());
                }
                board.addContent(sub);
                sub = new Element("boardName");
                sub.setText(bi.getBoardName());
                board.addContent(sub);
                topicInfo = new Element("topicInfo");
                board.addContent(topicInfo);
                
                ti = bi.getTopicInfo();
                if(null == ti){
                    sub = new Element("topicId");
                    sub.setText(" ");
                    topicInfo.addContent(sub);
                    sub = new Element("topicTitle");
                    sub.setText(" ");
                    topicInfo.addContent(sub);
                    sub = new Element("topicUser");
                    sub.setText(" ");
                    topicInfo.addContent(sub);
                    sub = new Element("modifyTime");
                    sub.setText(" ");
                    topicInfo.addContent(sub);
                }else{
                    sub = new Element("topicId");
                    sub.setText(ti.getTopicId().toString());
                    topicInfo.addContent(sub);
                    sub = new Element("topicTitle");
                    sub.setText(ti.getTopicTitle());
                    topicInfo.addContent(sub);
                    sub = new Element("topicUser");
                    sub.setText(ti.getTopicUser());
                    topicInfo.addContent(sub);
                    sub = new Element("modifyTime");
                    sub.setText(ti.getModifyTime().toString());
                    topicInfo.addContent(sub);
                }
            }
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
        
        
        
        
        return null;
    }
}
