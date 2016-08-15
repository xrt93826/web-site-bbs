$(function(){
	$("#addBrd").click(function() {
		$("#addBrdWrap").fadeIn();
		showParentBoard();
	});
	$("#back").click(function() {
		history.back();
	});
	$("#boardAddBtn").click(function(){
		var sel=$("#selBoard");
		addBoard(sel.val());
	});
	$("#boardAddRst").click(function(){
		$("#selBoard").val(0);
		$("#boardName").val("");
	});
});

//验证版块名
function valiName(){
	var validBoardName=false;
	var name = $("#boardName").val();
	if(name==""){
		$("#msg").html("版块名不能为空").css("color","red");
		return validBoardName;
	}
	console.log(name);
	name = encodeURI(encodeURI(name));
	
//	$.get("valiName", "boardName="+name, function(data) {
//		console.log(data);
//		if(data=="true"){
//			$("#msg").html("版块名已存在").css("color","red");
//			validBoardName = false;
//		}else{
//			console.log("****");
//			validBoardName = true;
//		}
//	}); 
	
//	此处用异步会来不及改变validBoardName的值，因此改用同步
	
	$.ajax({
		type: "GET",
		url: "valiName",
		data: "boardName="+name,
		async: false,
		success: function(data){
			if(data=="true"){
				$("#msg").html("版块名已存在").css("color","red");
				validBoardName = false;
			}else{
				validBoardName = true;
			}
		}
	});
	
	console.log("---"+validBoardName);
	return validBoardName;
}

//获得所有父版块并添加到select中
function showParentBoard(){
	var sel=$("#selBoard").empty();
	var opt0 = new Option("一级版块",0);
	sel.append(opt0);
	$.getJSON("getParentBoard",function(data){
		for(var i=0;i<data.length;i++){
			var opt = new Option(data[i].boardName,data[i].boardId);
			sel.append(opt);
		}
	});
}

//
function addBoard(parentId){
	var str="";
	if(!valiName()){
		return;
	}else{
		if(parentId==0){
			str = "&level=1";
		}else{
			str = "&level=2&parentId="+parentId;
		}
	}
	var name=$("#boardName").val();
	$.post("addBoard","boardName="+name+str,function(data){
		if(data=="true"){
			$("#msg").html("版块添加成功").css("color","green");
		}else{
			$("#msg").html("版块添加失败").css("color","red");
		}
	});
	
}

