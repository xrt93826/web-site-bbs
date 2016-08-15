<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bbs.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<title>首页</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<article>
		<%@ include file="head.jsp"%>
		<%@ include file="login_register.jsp"%>
		<section>
			<div class="table-responsive">
				<table class="table table-bordered table-responsive">
					<thead>
						<tr class="success">
							<th colspan="2">论坛</th>
							<th>主题</th>
							<th>最后发表</th>
						</tr>
					</thead>
					<tbody id="tb"></tbody>
				</table>
			</div>
		</section>
		<footer>
			<p>2016 NanJing Xiongrt Computer System Co.,Ltd 版权所有</p>
		</footer>
	</article>
</body>
</html>
