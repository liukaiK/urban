import $ from 'jquery';
import './style.css';

// import {
//     Check,
//     Delete,
//     Edit,
//     Message,
//     Search,
//     Star,
// } from 'element-plus';


const login_url = 'http://127.0.0.1:8888/login';

window.onload = function () {
    document.querySelector('#login_btn').addEventListener('click', function () {
        login();
    }, false);
};

// createApp({
//     data() {
//         return {
//             username: 'liukai'
//         }
//     }
// }).mount('#app');

function login() {
    let username = $('#username').val();
    let password = $('#password').val();
    let loginData = {
        "username": username,
        "password": password,
    }
    sendRequest(loginData);
}

function sendRequest(loginData) {
    $.ajax({
        url: login_url,
        data: JSON.stringify(loginData),
        contentType: 'application/json;charset=UTF-8',
        type: 'POST',
        dataType: 'json',
        success: function (responseBody) {
            saveTokeInfo(responseBody);
            displayResponseText(responseBody);
        },
    })
}


function saveTokeInfo(responseBody) {
    let tokenName = responseBody.data.tokenName;
    let tokenValue = responseBody.data.tokenValue;
    localStorage.setItem(tokenName, tokenValue);
}


function displayResponseText(responseBody) {
    document.getElementById("token").value = JSON.stringify(responseBody, undefined, 2);
}

