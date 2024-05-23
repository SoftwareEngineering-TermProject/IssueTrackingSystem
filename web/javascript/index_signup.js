//MODAL
const modal = document.getElementById("myModal");
const modalBtn = document.getElementById("modal-btn");
const closeBtn = document.getElementById("close-btn");

// 모달 열기 버튼 클릭 시
modalBtn.onclick = function() {
    modal.style.display = "block";
  };
  
// 모달 닫기 버튼 클릭 시
closeBtn.onclick = function() {
    modal.style.display = "none";
};

// 사용자가 모달 외부를 클릭하여 닫을 수 있도록
window.onclick = function(event) {
if (event.target == modal) {
    modal.style.display = "none";
}
};
  

//SIGN UP
const signup_form = document.getElementById("create-form");
const input_name = document.getElementById("input-name");
const id = document.getElementById("input-id");
const password = document.getElementById("input-password");
const signup_btn = document.getElementById("signup-btn");

signup_form.addEventListener('submit', (event) => {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음
    
    signup_btn.disabled = true;

    const signupRequest = new XMLHttpRequest();
    signupRequest.open('POST', url + `users/sign_up`);
    signupRequest.setRequestHeader("Content-Type", "application/json");

    console.log(id.value);
    console.log(password.value);

    var body = JSON.stringify({
        name : input_name.value,
        userName : id.value,
        password : password.value,
        //temporary value
        userRole : "ADMIN"
    });
    signupRequest.send(body);
    signupRequest.onload = () => {
        signup_btn.disabled = false;
        if( signupRequest.status === 200 ) {
            const response = JSON.parse(signupRequest.response);
            alert("계정 생성을 성공하였습니다.")
            location.reload(true);
        } else {
            alert("계정 생성을 실패하였습니다.");
            console.error("Error", signupRequest.status, signupRequest.statusText);
        }
    };
});
