function loginConditions(username, password) {
    if (username == null || username === "") {
        printErrorMessage("Please enter the username.");
        return false;
    }
    if (password == null || password === "") {
        printErrorMessage("Please enter the password.");
        return false;
    }
}

function regConditions(usernameReg, email, passwordReg, passwordRegRepeat) {
    if (usernameReg == null || usernameReg === "") {
        printErrorMessage("Please enter the username.");
        return false;
    }
    if (email == null || email === "") {
        printErrorMessage("Please enter the email.");
        return false;
    }
    if (usernameReg.length < 5 || usernameReg.length > 15) {
        printErrorMessage("Username should not be less then five symbols and less then 15 symbols.");
        return false;
    }
    if (!validateCharacter(usernameReg)) {
        printErrorMessage("Username has not valid symbols.");
        return false;
    }
    if (!validateEmail(email)) {
        printErrorMessage("Email have incorrect format.");
        return false;
    }
    if (passwordReg == null || passwordReg === "") {
        printErrorMessage("Please enter the password");
        return false;
    }
    if(!validatePassword(passwordReg)) {
        printErrorMessage("Password should be more than 8 symbols.");
        return false;
    }
    if (passwordRegRepeat == null || passwordRegRepeat === "") {
        printErrorMessage("Please, enter the password again.");
        return false;
    }
    if (passwordReg !== passwordRegRepeat) {
        printErrorMessage("Passwords are not compared.");
        return false;
    }
}

function validateEmail(mail) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
        return true;
    }
    return false;
}

function validateCharacter(str) {
    if (/[^a-zA-Z0-9\-\/]/.test(str)) {
        return false;
    }
    return true;
}

function validatePassword(password) {
    if(password.length < 8) {
        return false;
    }
    return true;
}

function printErrorMessage(message) {
    errorMessageDisplay(message);
}

document.getElementById('login_switch').onclick = function() {
    errorMessageNone();
}

function errorMessageNone() {
    var errorBlock = document.querySelectorAll('.errorMsg');
    errorBlock.forEach(function(block) {
            block.style.display = 'none';
    });
}

function errorMessageDisplay(message) {
    var errorBlock = document.querySelectorAll('.errorMsg');
    errorBlock.forEach(function(block) {
        block.style.display = 'block';
        block.innerHTML = message;
    });
}