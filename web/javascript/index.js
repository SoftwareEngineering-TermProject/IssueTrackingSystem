const login_btn = document.getElementById("login-btn");
const form = document.getElementById("login-form");

form.addEventListener('submit', (event) => {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음
});

login_btn.addEventListener('click', (event) => {
    window.location.href = './project.html';
});