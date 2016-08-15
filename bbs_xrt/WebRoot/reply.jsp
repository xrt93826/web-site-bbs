<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit"><!--html打开强制为极速模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><!--避免IE使用兼容模式-->
	<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>	
	<script type="text/javascript" src="js/bbs.js"></script>
	<title>回帖</title>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/post.css">
</head>
<body>
	<article>
		<%@ include file="head.jsp" %>
		<%@ include file="login_register.jsp" %>
		<section>
			<div>&gt;&gt;<a href="index.jsp">论坛首页</a>&gt;&gt;<a href="board?boardid=${requestScope.board.boardId }">${requestScope.board.boardName }</a></div>
			<form action="addreply" method="post">
				<table>
					<thead>
						<tr>
							<th colspan="2">回复帖子</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="poster">标题(可选)</td>
							<td class="post_content">
								<input type="text" name="rtitle">
							</td>
						</tr>
						<tr>
							<td class="poster">内容</td>
							<td class="post_content">
								<textarea name="rcontents" maxlength="1000"></textarea>
								<div>（不能大于1000字）</div>
							</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" value="${param.topicid }" name="topicId" >
				<input type="hidden" value="${param.boardid }" name="boardId" >
				<div>
					<input type="submit" value="提交">
					<input type="reset" value="重置">
				</div>
				
			</form>	
		</section>
		<footer>
			<p>2016 NanJing Xiongrt Computer System Co.,Ltd 版权所有</p>
		</footer>
	</article>
</body>
</html>