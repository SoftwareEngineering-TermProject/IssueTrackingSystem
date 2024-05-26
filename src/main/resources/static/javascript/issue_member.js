var member = document.getElementById("member");
var member_open = document.getElementById("member-open");
var member_close = document.getElementById("member-close");

member_open.onclick = function () {
    member.style.display = "block";
}

member_close.onclick = function () {
    member.style.display = "none";
}

window.addEventListener("click", (event)=> {
    if (event.target == member) {
        member.style.display = "none";
    }
});

var project_users = null;
//load all project participants
function loadAllMembers(){
    const userRequest = new XMLHttpRequest();
    userRequest.open('GET', url + `projects/participants/${project_id}`);
    userRequest.setRequestHeader("Content-Type", "application/json");

    userRequest.send();
    userRequest.onload = () => {
        if( userRequest.status === 200 ) {
            const result = JSON.parse(userRequest.response).result;
            project_users = result.users;
            console.log("USERS OF PROJECT : ");
            console.log(project_users);

            const pl_list = document.getElementById("pl-list");
            const dev_list = document.getElementById("dev-list");
            const tester_list = document.getElementById("tester-list");
            const assignee = document.getElementById("detail-assignee");
            const option = document.createElement("option");
            option.innerText = "";
            option.value = null;
            assignee.appendChild(option);
            project_users.forEach((element) => {
                const div = document.createElement("div");
                div.innerText = element.userName;
                div.classList.add("element");
                if(element.userId == localStorage.getItem("userId")){
                    div.classList.add("self");
                }
                if (element.userRole == "PL") {
                    pl_list.appendChild(div);
                } else if (element.userRole == "DEV") {
                    dev_list.appendChild(div);
                } else if (element.userRole == "TESTER") {
                    tester_list.appendChild(div);
                }

                const option = document.createElement("option");
                option.innerText = element.userName;
                option.value = element.userName;
                assignee.appendChild(option);
            })
        } else {
            alert("프로젝트의 유저 목록을 받아오지 못했습니다.");
            console.error("Error", userRequest.status, userRequest.statusText);
        }
    };
}

loadAllMembers();
const checkVariable = setInterval(() => {
    if (project_users !== null) {
        // 변수가 null이 아니면 함수를 실행하고 주기적인 체크를 멈춥니다.
        loadAllUsers();
        clearInterval(checkVariable);
    }
}, 100);