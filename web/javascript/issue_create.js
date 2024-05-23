const modal = document.getElementById("create-modal");
const modal_btn = document.getElementById("modal-btn");
const close_btn = document.getElementById("close-btn");

// 모달 열기 버튼 클릭 시
modal_btn.onclick = function() {
    modal.style.display = "block";
};
  
// 모달 닫기 버튼 클릭 시
close_btn.onclick = function() {
    modal.style.display = "none";
};
  
// 사용자가 모달 외부를 클릭하여 닫을 수 있도록
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

const form = document.getElementById("create-form");
const title = document.getElementById("input-title");
const description = document.getElementById("input-description");
const create_btn = document.getElementById("create-btn");

const userId = localStorage.getItem("userId");

form.addEventListener('submit', (event) => {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음
    
    create_btn.disabled = true;

    const createProjectRequest = new XMLHttpRequest();
    createProjectRequest.open('POST', url + `issues/?userId=${userId}`);
    createProjectRequest.setRequestHeader("Content-Type", "application/json");

    console.log(title.value);
    console.log(description.value);

    var body = JSON.stringify({
        projectId : localStorage.getItem("projectId"),
        title : title.value,
        description : description.value,
    });
    createProjectRequest.send(body);
    createProjectRequest.onload = () => {
        create_btn.disabled = false;
        if( createProjectRequest.status === 200 ) {
            console.log(createProjectRequest.response);
            const response = JSON.parse(createProjectRequest.response);
            console.log(response);   
            location.reload(true);
        } else {
            alert("이슈 생성에 실패하였습니다.");
            console.error("Error", createProjectRequest.status, createProjectRequest.statusText);
        }
    };
});
