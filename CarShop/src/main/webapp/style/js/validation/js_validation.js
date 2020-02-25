var imported = document.createElement('script');
imported.src = 'style/js/validation/validation.js';
document.head.appendChild(imported);

var email;
var username;
var password;
var usernameReg;
var passwordReg;
var passwordRegRepeat;

function initValues() {
    email = document.getElementById("modalREMail").value;
    username = document.getElementById("modalLUsername").value;
    password = document.getElementById("modalLPassword").value;
    usernameReg = document.getElementById("modalRUsername").value;
    passwordReg = document.getElementById("modalRPassword").value;
    passwordRegRepeat = document.getElementById("modalRPasswordRep").value;
}

function loginValidationJs() {
    initValues();
    loginConditions(username, password);
}

function regValidationJs() {
    initValues();
    regConditions(usernameReg, email, passwordReg, passwordRegRepeat);
}