<%@ page import="domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/choose.css"/>
    <script src="<%=basePath%>js/choose.js"></script>
    <script type="text/javascript">
        <%String userJsonString = (String) request.getAttribute("UserJsonString");%>
        var User=<%=userJsonString%>;
        //这边放回的是直接一个json对象，这不是json对象的字符串,这个就是服务器发送过来的数据
        //%=就是向页面打印内容
        function pd_login() {
        var onoff = document.getElementById("info-name");

        if (onoff.innerHTML == "登录") {
            alert("你还未登录，关闭跳到登录界面");
            window.location.href="User/Login.jsp";
        }
        else {
            <%
            if(userJsonString!=null&&!userJsonString.equals("")){//不等于null，且内容不为空
                 request.getSession().setAttribute("User",userJsonString);//在session域中设置一个user
                out.print("window.location.href=\"hotel.jsp\";");
            }
            %>
        }
        }
    </script>
</head>
<body onload="csh()">
<div class="main">
    <!-- 头部 logo部分 -->
    <header>
        <!-- logo -->
        <div class="logo">
            <img src="<%=basePath%>img/logo.png" >
        </div>
        <!-- 导航 -->
        <nav>
            <ul>
                <li><a href="#">酒店预订</a></li>
                <li><a href="#">火车票</a></li>
                <li><a href="#">游玩资讯</a></li>
            </ul>
        </nav>
        <!-- 用户部分 -->
        <div class="user">
            <!-- <a href="#" id="login">登录</a> | <a href="#" id="register">注册</a> -->
            <input type="checkbox" name="" id="onoff" value="" />
            <label for="onoff" onclick="click_login()" id="info-name"></label>
        </div>
        <div class="user-info" id="user-info">
            <h3>个人信息</h3>
            <p id="id"></p>
            <p id="phone"></p>
            <p id="address"></p>
            <p id="birth"></p>
        </div>
    </header>
    <div class="welcome"><h2>欢迎您</h2></div>
    <!-- section -->
    <section>
        <a class="hotel default" onclick="pd_login()">
            <h1>酒店预订</h1>
        </a>
    </section>
</div>
</body>
</html>
