


var n = 0;

function csh() {
	var info = document.getElementById("user-info");
	var onoff = document.getElementById("info-name");


	info.style.display = "none";

	if(User == null) {

		onoff.innerHTML = "登录";
	}
	else {
		onoff.innerHTML = User.user.name;

		var phone = document.getElementById("phone");
		var id = document.getElementById("id");
		var address = document.getElementById("address");
		var birth = document.getElementById("birth");

		// 判断是否有联系电话
		if(typeof(User.user.phoneNumber) != "undefined") {
			phone.innerHTML = "联系电话:" + User.user.phoneNumber;
		}
		else {
			phone.innerHTML = "联系电话:无";alert("1");
		}

		// 判断是否有ID
		if(typeof(User.user.name) != "undefined") {
			id.innerHTML = "账号:" + User.user.name;
		}
		else {
			id.innerHTML = "账号:无";
		}

		// 判断是否有地址
		if(typeof(User.user.address) != "undefined") {
			address.innerHTML = "地址:" + User.user.address;
		}
		else {
			address.innerHTML = "地址:无";
		}

		// 判断是否有生日
		if(typeof(User.user.birthday) != "undefined") {
			birth.innerHTML = "生日:" + User.user.birthday;
		}
		else {
			birth.innerHTML = "生日:无";
		}
	}

}
function flash() {
    var choose = document.getElementById("choose");
    var home=choose.innerHTML;
    if (home == "choose +") {
    }
    else {
    	alert("预定成功"+home);
        window.location.href="BuyHomeServlet?home="+home;//只做这一次刷新，并且请i去后，不再刷新整个页面
    }
}
function flashTwo() {
    window.location.href="ShowHomeServlet";//只做这一次刷新，并且请i去后，不再刷新整个页面
}


function click_login() {
	var info = document.getElementById("user-info");
	var onoff = document.getElementById("info-name");

	if (onoff.innerHTML == "登录") {
		window.location.href = "User/Login.jsp";
	}
	else {
		if (info.style.display == "none") {
			info.style.display = "block";
		} else {
			info.style.display = "none";
		}
	}
}

function reserve(n) {
	var a = document.getElementById("article");
	var b = document.getElementById("article-content");
	var c = document.getElementById("");

	a.style.top = "10%";
	a.style.opacity = "1";

// <article id="article">
// 		<div class="article-content" id="article-content">
// 		<button class="close" onclick="closePage()">关闭×</button>
// 	<h1>预定酒店</h1>
// 	<div class="fillin">
// 	<div class="horse-introduce">
// 		<p>大床房</p>
// 		<p>一张大床 有wifi </p>
// 	<p>仅存5件 再不下单就没了！</p>
}

function closePage() {
	var a = document.getElementById("article");
	var b = document.getElementById("article-content");
	var c = document.getElementById("");

	a.style.top = "-100%";
	a.style.opacity = "0";
}



