var imported = document.createElement('script');
imported.src = 'style/js/validation/validation.js';
document.head.appendChild(imported);

var email;
var username;
var password;
var usernameReg;
var passwordReg;
var passwordRegRepeat;

$(document).ready(function () {

    $('#login_form').submit(function() {
         init();
         return loginConditions(username, password);
    });

    $('#reg_form').submit(function () {
        init();
        return regConditions(usernameReg, email, passwordReg, passwordRegRepeat);
    });

});

function init() {
    email = $('#modalREMail').val();
    username = $('#modalLUsername').val();
    password = $('#modalLPassword').val();
    usernameReg = $('#modalRUsername').val();
    passwordReg = $('#modalRPassword').val();
    passwordRegRepeat = $('#modalRPasswordRep').val();
}