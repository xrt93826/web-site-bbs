$(function(){
	doExitWrap();
	moveWrap();
});

// 登录
function doLogin(){
	$("#login_wrap").modal("show");
}

//换验证码
function changeVcode(){
	$("#vcode").attr("src","vcode.jpg?"+ new Date().getTime());
}

// 注册
function doRegister(){
	$("#register_wrap").modal("show");
}

// 关闭按钮
function doExitWrap(){
	$("#modal2").modal("hide");
}

// 头像选择按钮
function doShowHeadSelect(){
	$("#modal2").modal("show");
}

// 头像确认按钮
function doHeadSubmit(){
	$("#head_select").fadeOut();
}

//登录注册窗口拖动
function moveWrap(){
	$(".move_wrap").mousedown(function(e){ //e鼠标事件 
		$(this).css("cursor","move");//改变鼠标指针的形状 
		var offset = $(this).offset();//DIV在页面的位置
		var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离
		var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离 
		$(document).bind("mousemove",function(ev){//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件 
			$(".move_wrap").stop();//加上这个之后 
			var _x = ev.pageX - x;//获得X轴方向移动的值 
			var _y = ev.pageY - y;//获得Y轴方向移动的值 
			$(".movable").animate({left:_x+"px",top:_y+"px"},10); 
			$(".move_wrap").animate({left:_x+"px",top:_y+"px"},10); 
		}); 
	}); 
	$(document).mouseup(function(){ 
		$(".move_wrap").css("cursor","default"); 
		$(this).unbind("mousemove"); 
	});
}

//注册时验证用户名
var validUserRegName=false;
function doCheckRegName(){
	var xhr = new XMLHttpRequest();
	var name = $("#txtRegName").val();
	name = encodeURI(encodeURI(name));

	xhr.open("get", "checkName?regname=" + name);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var str = xhr.responseText;
			if(str=="true"){
				validUserRegName=false;
				$("#spRegName").html("用户名已存在").css("color","red");
			}
			else{
				validUserRegName=true;
				$("#spRegName").html("√").css("color","green");
			}
		}
	};
	xhr.send(null);
}

//登录时验证用户名
var validUserLogName=false;
function doCheckLogName(){
	var xhr = new XMLHttpRequest();
	var name = $("#txtLogName").val();
	name = encodeURI(encodeURI(name));

	xhr.open("get", "checkName?regname=" + name);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var str = xhr.responseText;
			if(str=="true"){
				validUserLogName=true;
				$("#spLogName").html("√").css("color","green");
			}
			else{
				validUserLogName=false;
				$("#spLogName").html("用户名不存在").css("color","red");
			}
		}
	};
	xhr.send(null);
}

//验证密码格式 密码为6-16位任意字符
function doCheckPass1(){
	var pass1=$("#pass1").val();
	var sp=$("#spPass1");
	var rx=/^.{6,16}$/;
	if(!rx.test(pass1))
	{
		sp.html("密码格式不正确").css("color","red");
		return false;	
	}
	sp.html("√").css("color","green");
	return true;
}

//验证密码格式 密码为6-16位任意字符
function doCheckPass(){
	var pass=$("#pass").val();
	var sp=$("#spPass");
	var rx=/^.{3,16}$/;
	if(!rx.test(pass))
	{
		sp.html("密码格式不正确").css("color","red");
		return false;	
	}
	sp.html("√").css("color","green");
	return true;
}

//验证两次密码是否一致
function doCheckPass2(){
	var pass1=$("#pass1").val();
	var pass2=$("#pass2").val();
	var sp=$("#spPass2");
	if(pass1==pass2){
		sp.html("√").css("color","green");
		return true;
	}
	else{
		sp.html("两次密码输入不同").css("color","red");
		return false;
	}
}

//验证邮箱格式
var validEmail=false;
function doCheckEmail(){
	var email=$("#email").val();
	var sp=$("#spEmail");
	var rx=/^ \w +@\w+\.\w +$/;
	if(!rx.test(email))
	{
		sp.html("邮箱格式不正确").css("color","red");
		validEmail=false;	
	}
	var xhr = new XMLHttpRequest();
	xhr.open("get", "checkEmail?email=" + email);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var str = xhr.responseText;
			if(str=="true"){
				validEmail=true;
				sp.html("√").css("color","green");
			}
			else{
				validEmail=false;
				sp.html("邮箱已被注册").css("color","red");
			}
		}
	};
	xhr.send(null);
}

//注册信息验证
function doRegCheck(){
	return doCheckPass1() && doCheckPass2() && validUserRegName && validEmail;
}

//登录信息验证
function doLogCheck(){
	return doCheckPass() && validUserLogName;
}