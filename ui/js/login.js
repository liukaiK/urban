function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let loginData = {
        "username": username,
        "password": password,
    }
    sendRequest(loginData);
}

function sum(a, b) {
    return a + b;
}

module.exports.sum = sum;
