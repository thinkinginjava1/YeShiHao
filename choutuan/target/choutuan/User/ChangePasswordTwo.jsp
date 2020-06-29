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
				<form action="<c:url value="/ChangePassWordServlet"/>" method="post">
					<div class="group">
						<label for="change-id"><p>密码</p></label>
						<input type="text" placeholder="6-18位,必须有数字字母特色字符中的2种" id="change-id" name="password" />
					</div>
					<div class="group">
						<label for="change-phone"><p>重复密码</p></label>
						<input type="text" placeholder="重复密码" id="change-phone" name="change-phone" />
					</div>
					<button type="submit">修改</button>
				</form>
			</div>
		</div>
	</body>
</html>
