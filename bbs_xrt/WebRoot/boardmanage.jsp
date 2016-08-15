<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" ng-app="MyApp">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<script type="text/javascript" src="angular/angular.min.js"></script>
<script type="text/javascript" src="angular/angular-route.min.js"></script>
<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bbs.js"></script>
<script type="text/javascript" src="js/ngBoardMan.js"></script>
<!-- <script type="text/javascript" src="js/boardmanage.js"></script> -->
<title>版块管理</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/boardmanage.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div>
			<%@ include file="head.jsp"%>
			<%@ include file="login_register.jsp"%>
			</div>
		</div>
	</div>
		
			<div class="container" ng-controller="BoardController">
				<div class="row">
					<div class="col-md-3 col-lg-3">		
						<menu-group title="版块管理" menus="版块列表,添加版块,修改版块" base="boardmanage.jsp" links="list,add,modify"></menu-group>
					</div>
					<div class="col-md-9 col-lg-9 right_wrap" id="addBrdWrap" ng-view>
						
					</div>
				</div>
			</div>
		<div class="container">
			<div class="row">
				<footer>
					<p>2016 NanJing Xiongrt Computer System Co.,Ltd 版权所有</p>
				</footer>
			</div>
		</div>
</body>
</html>
