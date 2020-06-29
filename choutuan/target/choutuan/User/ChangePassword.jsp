<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/ChangePassword.css"/>
	</head>
	<body>
		<div class="change-block">
			<h2>修改密码</h2>
			<div class="change-content">
				<form action="<c:url value='/ForgetPasswordServlet'/>" method="post">
					<div class="group">
						<label for="change-id"><p>要修改的账号</p></label>
						<input type="text" placeholder="要修改的账号" id="change-id" name="changeId" />
					</div>
					<div class="group">
						<label for="change-phone"><p>预设的手机号</p></label>
						<input type="text" placeholder="预设的手机号" id="change-phone" name="changePhone" />
					</div>
					<button type="submit">下一步</button>
				</form>
			</div>
		</div>
	</body>
</html>
