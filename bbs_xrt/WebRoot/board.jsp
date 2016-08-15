<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>版块</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/board.css">
</head>
<body>
	<article>
		<%@ include file="head.jsp"%>
		<%@ include file="login_register.jsp"%>
		<section>
			<div>
				&gt;&gt;<a href="index.jsp">论坛首页</a>&gt;&gt;<a href="board?boardid=${requestScope.board.boardId }">${requestScope.board.boardName }</a>
			</div>
			<a href="post?boardid=${param.boardid }"><div class="post_btn"></div></a>
			<div>
				<a href="javascript:;" onclick="prev()">上一页</a> | <a href="javascript:;" onclick="next()">下一页</a>
			</div>
			<table>
				<thead>
					<tr>
						<th colspan="4"></th>
					</tr>
					<tr>
						<th colspan="2">文章</th>
						<th>作者</th>
						<th>回复</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${requestScope.topicall!=null }">
						<c:forEach items="${requestScope.topicall }" var="one">
							<tr>
								<td><img src="imgs/topic.gif" alt=""></td>
								<td><a href="topic?boardid=${requestScope.board.boardId}&topicid=${one.topicId}">${one.topicTitle }</a></td>
								<td>${one.topicUser }</td>
								<td>${one.replyCount }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div>
				<a href="javascript:;" onclick="prev()">上一页</a> | <a href="javascript:;" onclick="next()">下一页</a>
			</div>
			<script type="text/javascript">
				
				var currentpage = "${param.pagenum}"*1;
				if(currentpage == 0){
					currentpage=1;
				}
				function prev(event) {
					if (currentpage > 1) {
						currentpage--;
						window.location.href = 'board?boardid=${requestScope.board.boardId }&pagenum='
								+ (currentpage);
					}
				}

				

				function next(event) {
					if(currentpage < ${requestScope.maxpage})
					window.location.href = 'board?boardid=${requestScope.board.boardId }&pagenum='
							+ (currentpage + 1);
				}
			</script>



		</section>
		<footer>
			<p>2016 NanJing Xiongrt Computer System Co.,Ltd 版权所有</p>
		</footer>
	</article>
</body>
</html>