<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错误报告</title>
<script type="text/javascript">
	window.onload=function(){
		setTimeout(function(){
			window.history.back();
		},3000);
	}
</script>
</head>
<body>
	错误信息：${requestScope.msg }
	3秒自动跳转
</body>
</html>