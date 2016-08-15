<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bbs.js"></script>
<script type="text/javascript" src="js/usermanage.js"></script>
<title>管理员系统</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/manage.css">
</head>
<body>
	<article>
		<%@ include file="head.jsp"%>
		<%@ include file="login_register.jsp"%>
		<section>
			<div class="table-responsive">
				<table class="table table-bordered table-responsive">
					<thead>
						<tr>
							<th>ID</th>
							<th>用户名</th>
							<th>Email</th>
							<th>注册时间</th>
							<th>状态</th>
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
