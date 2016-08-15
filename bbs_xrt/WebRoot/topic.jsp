<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit"><!--html打开强制为极速模式 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><!--避免IE使用兼容模式-->
	<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bbs.js"></script>
	<title>帖子</title>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/read.css">
</head>
<body>
	<article>
		<%@ include file="head.jsp"%>
		<%@ include file="login_register.jsp"%>
		<div class="delete_win movable" id="delete_win">
			<div>
				<div>确认删除吗？</div>
				<div><button onclick="deleteConfirm()">确认</button><button onclick="doCancel()">取消</button></div>
			</div>
		</div>
		<section>
			<div>
				&gt;&gt;<a href="index.jsp">论坛首页</a>&gt;&gt;<a href="board?boardid=${requestScope.board.boardId }">${requestScope.board.boardName }</a>
			</div>
			<a href="reply?boardid=${param.boardid }&topicid=${param.topicid}"><div class="reply_btn"></div></a><a href="post?boardid=${param.boardid }"><div class="post_btn"></div></a>
			<div><a href="javascript:;" onclick="prev()">上一页</a> | <a href="javascript:;" onclick="next()">下一页</a></div>
			<table>
				<thead>
					<tr>
						<c:forEach items="${requestScope.toprep}" var="one" end="0">
							<th colspan="2">本页主题：${one.title }</th>
						</c:forEach>
					</tr>
					<tr>
						<th colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.toprep}" var="one" varStatus="status">
						<tr>
						<td class="poster">
							<div>${one.username }</div>
							<div><img src="${one.head }" alt=""></div>
							<div>注册：${one.regtime }
								<c:if test="${(param.pagenum==1 || param.pagenum==null)&&status.index == 0 }">
									楼主
								</c:if>
							</div>
						</td>
						<td class="post_content">
							<div>${one.title }</div>
							<div><p>${one.contents }</p></div>
							<div>
								<span>发表：[${one.postdate }]</span>
								<span>最后修改：[${one.modifydate }]</span>
								<!--判断如果是第一页并且是遍历循环的第一个，那么则为主题帖  -->
								<c:if test="${sessionScope.user.loginname==one.username }">
									<c:choose>
										<c:when test="${(param.pagenum==1 || param.pagenum==null)&&status.index == 0 }">
											<a href="javascript:;" onclick="showWin()">[删除]</a>
											<a href="modify?boardid=${param.boardid }&topicid=${param.topicid}">[修改]</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:;" onclick="showWin(${one.replyid})">[删除]</a>
											<a href="modify?boardid=${param.boardid }&topicid=${param.topicid}&replyid=${one.replyid}">[修改]</a>
										</c:otherwise>
									</c:choose>
								</c:if>
								
							</div>
						</td>
					</tr>
					
					</c:forEach>
				</tbody>
			</table>
			<div><a href="javascript:;" onclick="prev()">上一页</a> | <a href="javascript:;" onclick="next()">下一页</a></div>
			<script type="text/javascript">
				
				var currentpage = "${param.pagenum}"*1;
				if(currentpage == 0){
					currentpage=1;
				}
				function prev(event) {
					if (currentpage > 1) {
						currentpage--;
						window.location.href = 'topic?boardid=${requestScope.board.boardId}&topicid=${requestScope.topicid}&pagenum='
								+ (currentpage);
					}
				}

				

				function next(event) {
					if(currentpage < ${requestScope.maxpage})
						
						window.location.href = 'topic?boardid=${requestScope.board.boardId}&topicid=${requestScope.topicid}&pagenum='
							+ (currentpage+1);
				}
				
				
				var replyid = null;
				
				//显示删除窗口
				function showWin(id){
					$("#delete_win").fadeIn();
					$(".move_wrap").show();
					replyid = id;
				}
				
				function doCancel(){
					$("#delete_win").fadeOut();
					$(".move_wrap").hide();
					replyid = null;
					
				}
				
				//确认删除
				function deleteConfirm(){
					if(replyid == null)
					{
						window.location.href = 'delete?boardid=${param.boardid }&topicid=${param.topicid}';
					}
					else
					{
						window.location.href = 'delete?boardid=${param.boardid }&topicid=${param.topicid}&replyid='+replyid;
					}
					
				}
			</script>


		</section>
		<footer>
			<p>2016 NanJing Xiongrt Computer System Co.,Ltd 版权所有</p>
		</footer>
	</article>
</body>
</html>