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

var project_users;
//load all project participants
function loadAllMembers(){
    const userRequest = new XMLHttpRequest();
    userRequest.open('GET', url + `projects/participants/{projectId}?projectId=${project_id}`);
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
            project_users.forEach((element) => {
                const div = document.createElement("div");
                div.innerText = element.userName;
                div.classList.add("element");
                if (element.userRole == "PL") {
                    pl_list.appendChild(div);
                } else if (element.userRole == "DEV") {
                    dev_list.appendChild(div);
                } else if (element.userRole == "TESTER") {
                    tester_list.appendChild(div);
                }
            })
        } else {
            alert("프로젝트의 유저 목록을 받아오지 못했습니다.");
            console.error("Error", userRequest.status, userRequest.statusText);
        }
    };
}

loadAllMembers();