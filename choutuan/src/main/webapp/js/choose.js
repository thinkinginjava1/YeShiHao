
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
			id.innerHTML = "姓名:" + User.user.name;
		}
		else {
			id.innerHTML = "姓名:无";
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


