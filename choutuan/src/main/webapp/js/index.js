function anjian() {
    if(event.keyCode == 13) {
        document.getElementById("tijiao").click();
    }
} 
// id_focus() passowrd_focus()
function id_focus() {
    var s1 = document.getElementById("id-title");
    var input = document.getElementById("id");

    if (input.value == "") {
        s1.style.display = "block";
    }
    else {
        s1.style.display = "none";
    }
}
function password_focus() {
    var s1 = document.getElementById("password-title");
    var input = document.getElementById("password");

    if (input.value == "") {
        s1.style.display = "block";
    }
    else {
        s1.style.display = "none";
    }
}