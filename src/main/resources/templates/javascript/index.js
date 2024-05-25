const login_btn = document.getElementById("login-btn");
const form = document.getElementById("login-form");
const user_name = document.getElementById("username");
const pass_word = document.getElementById("password");
const warn = document.getElementById("warn");

form.addEventListener('submit', (event) => {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음
    
    login_btn.disabled = true;
    warn.style.display = "none";

    const loginRequest = new XMLHttpRequest();
    loginRequest.open('POST', url + `users/sign_in`);
    loginRequest.setRequestHeader("Content-Type", "application/json");

    var body = JSON.stringify({
        userName : user_name.value,
        password : pass_word.value
    });
    loginRequest.send(body);
    loginRequest.onload = () => {
        login_btn.disabled = false;
        if( loginRequest.status === 200 ) {
            console.log(loginRequest.response);
            const response = JSON.parse(loginRequest.response);
            console.log(response);   
            
            localStorage.setItem("userId", response.result.userId);
            window.location.href = './project';
        } else {
            warn.style.display = "block";
            console.error("Error", loginRequest.status, loginRequest.statusText);
        }
    };
});
