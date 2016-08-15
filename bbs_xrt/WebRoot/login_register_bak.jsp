<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="move_wrap"></div>
<aside class="login_wrap movable" id="login_wrap">
	<div>
		<div class="exit_btn exit_btn_out">×</div>
		<form action="login" method="post" onsubmit="return doLogCheck()">
			<table>
				<tbody>
					<tr>
						<td colspan="2">用户登录</td>
					</tr>
					<tr>
						<td><label for="loginname">用户名</label></td>
						<td><input type="text" name="loginname" onblur="doCheckLogName()" id="txtLogName"><span class="error_msg" id="spLogName"></span></td>
					</tr>
					<tr>
						<td><label for="loginpwd">密码</label></td>
						<td><input type="password" name="loginpwd" id="pass" onblur="doCheckPass()"><span class="error_msg" id="spPass"></span></td>
					</tr>
					<tr>
						<td><label for="vcode">验证码</label></td>
						<td><input type="text" name="vcode"><a href="javascript:;" onclick="changeVcode()"><img
								src="vcode.jpg" alt="" id="vcode" class="vcode"></a></td>
					</tr>
				</tbody>
			</table>
			<div>
				<input type="submit" name="" value="登录"> <input type="reset"
					name="" value="重置"><a href="findpwd.jsp">忘记密码</a>
			</div>
		</form>
	</div>
</aside>
<aside class="register_wrap movable" id="register_wrap">
	<div>
		<div class="exit_btn exit_btn_out">×</div>
		<form action="register" method="POST" onsubmit="return doRegCheck()">
			<table>
				<tbody>
					<tr>
						<td colspan="2">用户注册</td>
					</tr>
					<tr>
						<td><label for="regname">用户名</label></td>
						<td><input type="text" name="regname" onblur="doCheckRegName()" id="txtRegName"><span class="error_msg" id="spRegName"></span></td>
					</tr>
					<tr>
						<td><label for="regpwd">密码</label></td>
						<td><input type="password" name="regpwd" onblur="doCheckPass1()" id="pass1"><span class="error_msg" id="spPass1"></span></td>
					</tr>
					<tr>
						<td><label for="regpwdr">重复密码</label></td>
						<td><input type="password" name="regpwdr" onblur="doCheckPass2()" id="pass2"><span class="error_msg" id="spPass2"></span></td>
					</tr>
					<tr>
						<td><label for="email">Email</label></td>
						<td><input type="email" name="email" onblur="doCheckEmail()" id="email"><span class="error_msg" id="spEmail"></span></td>
					</tr>
					<tr>
						<td><label for="head">请选择头像</label></td>
						<td><input type="button" value="选择"
							onclick="doShowHeadSelect()"></td>
					</tr>
				</tbody>
			</table>
			<div class="head_select" id="head_select">
				<table>
					<tbody>
						<tr>
							<td><img src="imgs/head/1.gif" alt=""><input
								type="radio" name="head" value="1"></td>
							<td><img src="imgs/head/2.gif" alt=""><input
								type="radio" name="head" value="2"></td>
							<td><img src="imgs/head/3.gif" alt=""><input
								type="radio" name="head" value="3"></td>
							<td><img src="imgs/head/4.gif" alt=""><input
								type="radio" name="head" value="4"></td>
							<td><img src="imgs/head/5.gif" alt=""><input
								type="radio" name="head" value="5"></td>
						</tr>
						<tr>
							<td><img src="imgs/head/6.gif" alt=""><input
								type="radio" name="head" value="6"></td>
							<td><img src="imgs/head/7.gif" alt=""><input
								type="radio" name="head" value="7"></td>
							<td><img src="imgs/head/8.gif" alt=""><input
								type="radio" name="head" value="8"></td>
							<td><img src="imgs/head/9.gif" alt=""><input
								type="radio" name="head" value="9"></td>
							<td><img src="imgs/head/10.gif" alt=""><input
								type="radio" name="head" value="10"></td>
						</tr>
						<tr>
							<td><img src="imgs/head/11.gif" alt=""><input
								type="radio" name="head" value="11"></td>
							<td><img src="imgs/head/12.gif" alt=""><input
								type="radio" name="head" value="12"></td>
							<td><img src="imgs/head/13.gif" alt=""><input
								type="radio" name="head" value="13"></td>
							<td><img src="imgs/head/14.gif" alt=""><input
								type="radio" name="head" value="14"></td>
							<td><img src="imgs/head/15.gif" alt=""><input
								type="radio" name="head" value="15"></td>
						</tr>
					</tbody>
				</table>
				<div>
					<input type="button" name="" value="确认" onclick="doHeadSubmit()">
				</div>
			</div>
			<div>
				<input type="submit" name="" value="注册"> <input type="reset"
					name="" value="重置">
			</div>
		</form>
	</div>
</aside>