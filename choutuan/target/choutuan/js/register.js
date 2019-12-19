

function password_focus() {
	var password = document.getElementById("password");
	var cf_password = document.getElementById("cf_password");
	var password_title = document.getElementById("password_title");
	
	if(password.value == cf_password.value) {
		password_title.style.display = "none";
	}
	else {
		password_title.style.display = "block";
	}
}

function phone_check() {
	var phone = document.getElementById("phone-title");
	var input = document.getElementById("phone");

	if (input.value.length != 11) {
		phone.style.display = "block";
	}
	else {
		phone.style.display = "none";
	}
}

function password_check() {
	var password = document.getElementById("password-title");
	var input = document.getElementById("password");
	
	if (input.value.length >= 6 && input.value.length <= 18) {
		password.style.display = "none";
	}
	else {
		password.style.display = "block";
	}
}

function name_check() {
	var id = document.getElementById("name_title");
	var input = document.getElementById("name")

	if (input.value == "") {
		id.style.display = "block";
	}
	else {
		id.style.display = "none";
	}
}