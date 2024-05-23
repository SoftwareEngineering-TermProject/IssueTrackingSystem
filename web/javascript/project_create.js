const form = document.getElementById("create-form");
const title = document.getElementById("input-title");
const description = document.getElementById("input-description");
const create_btn = document.getElementById("create-btn");

const url = "http://localhost:8080/";
const userId = localStorage.getItem("userId");

console.log("userId : " + userId);

form.addEventListener('submit', (event) => {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음
    
    create_btn.disabled = true;

    const createProjectRequest = new XMLHttpRequest();
    createProjectRequest.open('POST', url + `projects/?userId=${userId}`);
    createProjectRequest.setRequestHeader("Content-Type", "application/json");

    console.log(title.value);
    console.log(description.value);

    var body = JSON.stringify({
        title : title.value,
        description : description.value
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
            alert("프로젝트 생성에 실패하였습니다.");
            console.error("Error", createProjectRequest.status, createProjectRequest.statusText);
        }
    };
});
