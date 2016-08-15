<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<a href="index.jsp"><img src="imgs/logo.jpg" alt=""></a>
</header>
<nav class="navbar">
	<ul>
		<c:choose>
			<c:when test="${sessionScope.user == null}">
				<li>您尚未</li>
				<li><a href="javascript:;" onclick="doLogin()">登录</a></li>
				<li><a href="javascript:;" onclick="doRegister()">注册</a></li>
			</c:when>
			<c:otherwise>
				<span>欢迎，${sessionScope.user.loginname }</span>
				<c:if test="${sessionScope.user.state == 2 || sessionScope.user.state == 3}">
					<span><a href="usermanage.jsp">用户管理</a></span>
				</c:if>
				<c:if test="${sessionScope.user.state == 3}">
					<span><a href="boardmanage.jsp">版块管理</a></span>
				</c:if>
				<span><a href="exit">退出</a></span>
			</c:otherwise>
		</c:choose>
	</ul>


</nav>