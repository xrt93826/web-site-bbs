$(function(){
	var tb=$("#tb").empty();
	$.getJSON("welcome",function(data){
		for(var i=0;i<data.length;i++){
			var item=data[i];
			var parentId=item.parentId;
			if(parentId==0){
				var tr=$("<tr>").addClass('topic_title info').appendTo(tb);
				var parentBoardName=item.boardName;
				$("<td>").attr("colspan","4").html(parentBoardName).appendTo(tr);
			}
			else{
				var tr=$("<tr>").appendTo(tb);
				$("<td>").html($("<img>").attr("src","imgs/board.gif")).appendTo(tr);
				var childBoardId=item.boardId;
				var childBoardName=item.boardName;
				$("<td>").html($("<a>").attr("href","board?boardid="+childBoardId).html(childBoardName)).appendTo(tr);
				var topicCount=item.topicCount;
				$("<td>").html(topicCount).appendTo(tr);
				var td=$("<td>").addClass('lastpost').appendTo(tr);
				var topicInfo=item.topicInfo;
				if(topicInfo==null){
					$("<div>").append($("<span>")).append($("<span>").html("无内容")).appendTo(td);
				}
				else{
					var boardId=item.boardId;
					var topicId=topicInfo.topicId;
					$("<div>").html($("<a>").attr("href","topic?boardid="+boardId+"&topicid="+topicId).html(topicInfo.topicTitle)).appendTo(td);
					var topicUser=topicInfo.topicUser;
					var modifyTime=topicInfo.modifyTime;
					$("<div>").append($("<span>").html(topicUser)).append($("<span>").attr("id","modifytime").html(" [ "+modifyTime.substring(0,19)+" ] ")).appendTo(td);
				}
			}
		}
	});
	
});