<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/register.css"/>
		<script src="../js/register.js"></script>
	</head>
	<body>
		<div class="logo">
			<img src="../img/logo.png" >
		</div>
		<div class="register">
			<h2>用户注册</h2>
			<div class="register-contnet">
				<form action="<c:url value='/RegisterServlet'/>" method="post">
					<div class="group">
						<label for="name"><p>账号</p></label>
						<input type="text" name="name" placeholder="账号" id="name" required="required" onfocusout="name_check()" />
						<div class="password_title" id="name_title">账号不可以为空！</div>
					</div>
					<div class="group">
						<label for="password"><p>密码</p></label>
						<input type="password" name="password" placeholder="6-18位" id="password" required="required" onfocusout = "password_check()" />
						<div class="password_title" id="password-title">密码必须是6-18位！</div>
					</div>
					<div class="group">
						<label for="cf_password"><p>重复密码</p></label>
						<input type="password" name="cf_password" placeholder="和上方的密码一致" id="cf_password" required="required" onfocusout="password_focus()" />
						<div class="password_title" id="password_title">重复密码与密码不一致！</div>
					</div>
					<div class="group">
						<label><p>性别</p></label>
						<label for="man" style="margin-left: 1em;">男</label><input type="radio" id="man" name="gender" value="man" />
						<label for="woman">女</label><input type="radio" id="woman" name="gender" value="woman" />
						<label for="no">不选</label><input type="radio" id="no" name="gender" value="no" />
					</div>
					<div class="group">
						<label for="birth"><p>出生日期</p></label>
						<input type="date" name="birthday" id="birth" required="required" />
					</div>
					<div class="group">
						<label for="address"><p>地址</p></label>
						<input type="text" name="address" placeholder="请输入您的地址" id="address" required="required" />
					</div>
					<div class="group">
						<label for="phone"><p>联系电话</p></label>
						<input type="text" name="phoneNumber" placeholder="请输入您的联系电话" id="phone" required="required" onfocusout="phone_check()" />
						<div class="password_title" id="phone-title">手机号必须是11位大陆手机号！</div>
					</div>
					<div class="btn-group">
						<button type="submit" class="submit">提交</button>
						<button type="reset" class="reset">重置</button>
					</div>
					<% String resgiterInfo= (String)request.getSession().getAttribute("registinfo");
					if(resgiterInfo!=null){
						out.print(resgiterInfo);
					}%>
				</form>
			</div>
		</div>
	</body>
</html>
