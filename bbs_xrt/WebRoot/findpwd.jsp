<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<!--html打开强制为极速模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--避免IE使用兼容模式-->
<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bbs.js"></script>
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<style type="text/css">
	section div{
		text-align:center;
		margin:10px auto;
	}

</style>
</head>
<body>
	<article>
		<%@ include file="head.jsp"%>
		<%@ include file="login_register.jsp"%>
		<section>
			<form action="findpwd" method="POST">
				<div>登录名<input type="text" name="loginname"></div>
				<div>邮箱<input type="email" name="email"></div>
				<div><button>提交</button></div>
			</form>
		</section>
		<footer>
			<p>2016 NanJing Xiongrt Computer System Co.,Ltd 版权所有</p>
		</footer>
	</article>
</body>
</html>

