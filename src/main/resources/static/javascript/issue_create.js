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

window.addEventListener("click", (event) => {
    if (event.target == modal) {
        modal.style.display = "none";
    }
});


const form = document.getElementById("create-form");
const title = document.getElementById("input-title");
const description = document.getElementById("input-description");
const create_btn = document.getElementById("create-btn");
var priority = null;

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
        issuePriority : priority
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
            const err_msg = JSON.parse(createProjectRequest.response).message
            alert(err_msg);
            console.log(err_msg);
            console.error("Error", createProjectRequest.status, createProjectRequest.statusText);
        }
    };
});

//RADIO BUTTON 
const radios = document.querySelectorAll(".radio-elem");
console.log(radios);
radios.forEach((element) => element.addEventListener("click", (event) => {
    priority = event.target.innerText;
    radios.forEach((element) => {element.className = "radio-elem"});
    element.classList.add("selected");
}));