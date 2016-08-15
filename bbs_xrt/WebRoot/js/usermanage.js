$(function(){
	showUsers();
	
});

function showUsers(){
	var tb=$("#tb").empty();
	$.getJSON("getUsers",function(data){
		switch(data[data.length-1].state){
		case 1:
			return;
			break;
		case 2:
			fillTable(data,2);
			break;
		case 3:
			fillTable(data,3);
			break;
		default:
			return;
		}	
	});
}

function fillTable(data,state){
	for(var i=0;i<data.length-1;i++){
		var item=data[i];
		var tr=$("<tr>").appendTo("#tb");
		$("<td>").html(item.userid).appendTo(tr);
		$("<td>").html(item.loginname).appendTo(tr);
		$("<td>").html(item.email).appendTo(tr);
		$("<td>").html(item.regtime).appendTo(tr);
		createStateTd(item,state).appendTo(tr);
	}
}

function createStateTd(item,state){
	var td = $("<td>");
	switch(item.state){
	case 0:
		return td.append($("<input type='button' value='恢复'>").click(function(){
			modifyState(item.userid,1);
		}));
		break;
	case 1:
		if(state==2){
			return td.append($("<input type='button' value='删除'>").click(function(){
				deleteUser(item.userid);
			}));
		}else{
			return td.append($("<input type='button' value='升级'>").click(function(){
				modifyState(item.userid,2);
			})).append($("<input type='button' value='删除'>").click(function(){
				deleteUser(item.userid);
			}));
		}	
		break;
	case 2:
		if(state==2){
			return td.html("管理员");
		}else{
			return td.append($("<input type='button' value='降级'>").click(function(){
				modifyState(item.userid,1);
			}));
		}	
		break;
	case 3:
		return td.html("超级管理员");
		break;
	default:
		return td;
	}
}

function modifyState(id,state){
	$.get("modifyState","id="+id+"&state="+state,function(ret){
		if(ret=="true"){
			alert("修改成功！");
			showUsers();
			return;
		}else{
			alert("修改失败！");
			return;
		}
	});
}

function deleteUser(id){
	$.get("deleteUser","id="+id,function(ret){
		if(ret=="true"){
			alert("删除成功！");
			showUsers();
			return;
		}else{
			alert("删除失败！");
			return;
		}
	});
}


