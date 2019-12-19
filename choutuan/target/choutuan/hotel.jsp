<%@ page import="service.HotelService" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="domain.HotelHome" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width" , initial-scale=1.0/>
    <meta http-equiv="X-UA-Compatible" content="IE-edge"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/hotel.css"/>
    <script src="<%=basePath%>js/hotel.js"></script>
    <script type="text/javascript">
        //登录请求
        <%Object user = session.getAttribute("User");%>
        var User =<%=user%>;

        //这边放回的是直接一个json对象，这不是json对象的字符串,这个就是服务器发送过来的数据
        <%String hotelString = (String) session.getAttribute("hotelString");
        if(hotelString==null){
            //为null，请求一次
        response.sendRedirect(basePath+"ShowHomeServlet");
        }else {
            out.println("var hotelString="+hotelString+";");
        }
        %>
    </script>
</head>
<body onload="csh()">
<form action="<c:url value="/ShowHomeServlet"/>" method="post">
</form>
<div class="main">
    <!-- header -->
    <header>
        <div class="logo">
            <img src="<%=basePath%>img/logo.png">
        </div>
        <nav>
            <ul>
                <li>
                    <a href="#" class="active">酒店预定</a>
                </li>
                <li>
                    <a href="#">火车票</a>
                </li>
                <li>
                    <a href="#">游玩咨询</a>
                </li>
            </ul>
        </nav>
        <!-- 用户部分 -->
        <div class="user" onclick="click_login()">
            <!-- <a href="#" id="login">登录</a> | <a href="#" id="register">注册</a> -->
            <input type="checkbox" name="" id="onoff" value=""/>
            <label for="onoff" onclick="click_login()" id="info-name"></label>
        </div>
        <div class="user-info" id="user-info">
            <h3>个人信息</h3>
            <p id="id"></p>
            <p id="phone"></p>
            <p id="address"></p>
            <p id="birth">账号：研讨会</p>
        </div>
    </header>
    <!-- section -->
    <section>
        <div class="section-content">
            <div class="section-header">
                <img src="<%=basePath%>img/indexbg.gif">
            </div>
            <div class="group">
                <a href="#"><img src="<%=basePath%>img/200.webp"></a>
                <div class="text-1">
                    <h3>大床房</h3>
                    <p>￥666.66<span>包含本金600<br>税金66</span></p>
                    <h5>仅存件</h5>
                    <button type="button" onclick="reserve(1)">立即预定</button>
                </div>
                <div class="introduce">
                    <h4>房间介绍</h4>
                    <div class="introduce-1">
                        <p>床位：2张</p>
                        <p>独立卫生间</p>
                        <p>床位：2张</p>
                        <p>床位：2张</p>
                    </div>
                    <div class="introduce-2">
                        <p>床位：2张</p>
                        <p>独立卫生间</p>
                        <p>床位：2张</p>
                        <p>床位：2张</p>
                    </div>
                </div>
            </div>
            <div class="group">
                <a href="#"><img src="<%=basePath%>img/200.webp"></a>
                <div class="text-1">
                    <h3>大床房</h3>
                    <p>￥666.66<span>包含本金600<br>税金66</span></p>
                    <h5>仅存</h5>
                    <button type="button" onclick="reserve(1)">立即预定</button>
                </div>
                <div class="introduce">
                    <h4>房间介绍</h4>
                    <div class="introduce-1">
                        <p>床位：2张</p>
                        <p>独立卫生间</p>
                        <p>床位：2张</p>
                        <p>床位：2张</p>
                    </div>
                    <div class="introduce-2">
                        <p>床位：2张</p>
                        <p>独立卫生间</p>
                        <p>床位：2张</p>
                        <p>床位：2张</p>
                    </div>
                </div>
            </div>
            <div class="group">
                <a href="#"><img src="<%=basePath%>img/200.webp"></a>
                <div class="text-1">
                    <h3>大床房</h3>
                    <p>￥666.66<span>包含本金600<br>税金66</span></p>
                    <h5>仅存件</h5>
                    <button type="button" onclick="reserve(1)">立即预定</button>
                </div>
                <div class="introduce">
                    <h4>房间介绍</h4>
                    <div class="introduce-1">
                        <p>床位：2张</p>
                        <p>独立卫生间</p>
                        <p>床位：2张</p>
                        <p>床位：2张</p>
                    </div>
                    <div class="introduce-2">
                        <p>床位：2张</p>
                        <p>独立卫生间</p>
                        <p>床位：2张</p>
                        <p>床位：2张</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- article -->
    <article id="article">
        <div class="article-content" id="article-content">
            <button class="close" onclick="closePage()">关闭×</button>
            <h1>预定酒店</h1>
            <div class="fillin">
                <div class="horse-introduce">
                    <p>大床房</p>
                    <p>一张大床 有wifi </p>
                    <p>仅存5件 再不下单就没了！</p>
                </div>
                <div class="horse-reserve">
                    <div class="reserve-group">
                        <p>房间数</p>
                        <select>
                            <option>1间</option>
                            <option>2间</option>
                        </select>
                    </div>
                    <div class="reserve-group">
                        <p>入住人</p>
                        <input type="text"/>
                    </div>
                    <div class="reserve-group">
                        <p>预计到店</p>
                        <input type="time"/>
                    </div>
                    <div class="reserve-group">
                        <p>预计时间</p>
                        <input type="date"/> - <input type="date"/>
                    </div>
                </div>
                <div class="reserve-tips">
                    <h4>退款规则</h4>
                    <p>根据酒店政策，在入住前24小时可免费取消，如未入住，酒店将扣除全部房费。</p>
                    <h4>预定说明</h4>
                    <p>房间整晚保留，14：00前到达可能需要等待</p>
                </div>
                <div class="reserve-submit">
                    <div class="submit-group">
                        <p>总价</p>
                        <span>￥666.66</span>
                    </div>
                    <button type="submit">提交</button>
                </div>
            </div>
        </div>
    </article>
    <!-- footer -->
    <footer>
        <p>&copy; 天津电子信息职业技术学院 QQ：37679562</p>
    </footer>
</div>
</body>
</html>
