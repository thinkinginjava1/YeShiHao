<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/indexcss.css"/>
    <script src="../js/index.js"></script>
</head>
<body onkeydown="anjian()">
<h1>欢迎光临</h1>
<form action="" method="post" name="form1">
<div class="login">
    <h2>用户登录</h2>
    <div class="group">
        <label for="id"><img src="../img/user.png"  ></label>
        <input type="text" placeholder="请输入账号" required="required" id="id" name="name" onfocusout="id_focus()"/>
        <div class="id-title" id="id-title">账号不可以为空</div>
    </div>
    <div class="group">
        <label for="password"><img src="../img/password.png" ></label>
        <input type="password" placeholder="请输入密码" required="required" id="password"  name="password" onfocusout="password_focus()"/>
        <div class="id-title" id="password-title">密码不可以为空</div>
    </div>
	<a href="<c:url value='/User/ChangePassword.jsp'/>" class="forget-password">忘记密码？</a>
    <div class="btn-group">
        <button type="button" class="register_login" onclick="login()" id="tijiao">登录</button>
        <a href="<c:url value='/User/register.jsp'/>"  class="register">注册</a>
    </div>
    <p style="color: red; text-align: center; margin: 0 auto;">
        <%  String msg= (String) session.getAttribute("msg");
            if(msg!=null){
                out.print(msg);}
        %>
    </p>
</div>
</form>
</body>
<script type="text/javascript">
    function login()
    {  document.forms[0].method="POST";
        document.forms[0].action="<c:url value='/LoginServlet'/>";
        //登录请求
        document.forms[0].submit();
    }
</script>
</html>
