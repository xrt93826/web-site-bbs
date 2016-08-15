<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div id="login_wrap" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">&times;</button>
				<h3>用户登录</h3>
			</div>
			<form action="login" method="post" onsubmit="return doLogCheck()"
				class="form-horizontal" role="form">
				<div class="modal-body col-sm-offset-2">
					<div class="form-group">
						<label for="loginname" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" name="loginname" onblur="doCheckLogName()" id="txtLogName">
							<span class="error_msg" id="spLogName"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="loginpwd" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
							<input type="password" name="loginpwd" id="pass" onblur="doCheckPass()">
							<span class="error_msg" id="spPass"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="vcode" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-10">
							<input type="text" name="vcode">
							<a href="javascript:;" onclick="changeVcode()"><img src="vcode.jpg" alt="" id="vcode"
								class="vcode"></a>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-8 col-sm-offset-2">
							<input type="submit" name="" value="登录">
							<input type="reset" name="" value="重置">
							<a href="findpwd.jsp">忘记密码</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="register_wrap" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal">&times;</button>
				<h3>用户注册</h3>
			</div>
			<form action="register" method="post" onsubmit="return doRegCheck()"
				class="form-horizontal" role="form">
				<div class="modal-body col-sm-offset-2">
					<div class="form-group">
						<label for="regname" class="col-sm-3 control-label">用户名</label>
						<div class="col-sm-9">
							<input type="text" name="regname" onblur="doCheckRegName()" id="txtRegName">
							<span class="error_msg" id="spRegName"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="regpwd" class="col-sm-3 control-label">密码</label>
						<div class="col-sm-9">
							<input type="password" name="regpwd" onblur="doCheckPass1()" id="pass1">
							<span class="error_msg" id="spPass1"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="regpwdr" class="col-sm-3 control-label">重复密码</label>
						<div class="col-sm-9">
							<input type="password" name="regpwdr" onblur="doCheckPass2()" id="pass2">
							<span class="error_msg" id="spPass2"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">Email</label>
						<div class="col-sm-9">
							<input type="email" name="email" onblur="doCheckEmail()" id="email">
							<span class="error_msg" id="spEmail"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="head" class="col-sm-3 control-label">请选择头像</label>
						<div class="col-sm-9">
							<input type="button" value="选择" onclick="doShowHeadSelect()">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-6 col-sm-offset-3">
							<input type="submit" name="" value="注册">
							<input type="reset" name="" value="重置">
						</div>
					</div>
				</div>
				<div id="modal2" class="modal" >
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-group">
									<div class="col-sm-10 col-sm-offset-1">
										<img src="imgs/head/1.gif" alt=""><input
											type="radio" name="head" value="1">
										<img src="imgs/head/2.gif" alt=""><input
											type="radio" name="head" value="2">
										<img src="imgs/head/3.gif" alt=""><input
											type="radio" name="head" value="3">
										<img src="imgs/head/4.gif" alt=""><input
											type="radio" name="head" value="4">
										<img src="imgs/head/5.gif" alt=""><input
											type="radio" name="head" value="5">
									</div>
								</div>
								<div class="form-group">	
									<div class="col-sm-10 col-sm-offset-1">
										<img src="imgs/head/6.gif" alt=""><input
											type="radio" name="head" value="6">
										<img src="imgs/head/7.gif" alt=""><input
											type="radio" name="head" value="7">
										<img src="imgs/head/8.gif" alt=""><input
											type="radio" name="head" value="8">
										<img src="imgs/head/9.gif" alt=""><input
											type="radio" name="head" value="9">
										<img src="imgs/head/10.gif" alt=""><input
											type="radio" name="head" value="10">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10 col-sm-offset-1">
										<img src="imgs/head/11.gif" alt=""><input
											type="radio" name="head" value="11">
										<img src="imgs/head/12.gif" alt=""><input
											type="radio" name="head" value="12">
										<img src="imgs/head/13.gif" alt=""><input
											type="radio" name="head" value="13">
										<img src="imgs/head/14.gif" alt=""><input
											type="radio" name="head" value="14">
										<img src="imgs/head/15.gif" alt=""><input
											type="radio" name="head" value="15">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2 col-sm-offset-5">
										<button onclick="doExitWrap()">确定</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
