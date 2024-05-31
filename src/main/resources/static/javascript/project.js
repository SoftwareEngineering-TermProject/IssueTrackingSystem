const all_project = document.getElementById("all-projects");

//load project list
const projectRequest = new XMLHttpRequest();
const user_id = localStorage.getItem("userId");
projectRequest.open('GET', url + `projects/projectList/${user_id}`);
projectRequest.setRequestHeader("Content-Type", "application/json");

projectRequest.send();
projectRequest.onload = () => {
    if( projectRequest.status === 200 ) {
        const result = JSON.parse(projectRequest.response).result;
        const projects = result.projects;
        console.log(projects);
        projects.forEach(element => {
          addProject(element);
        });
    } else {
        alert("프로젝트 목록을 받아오지 못했습니다.");
        console.error("Error", projectRequest.status, projectRequest.statusText);
    }
};

/*
<div class="project-box" id="project-2">
  <div class="project-title">League of legend</div>
  <div class="project-info">
    <div class="info-role">tester</div>
    <div class="info-participant">👤10</div>
  </div>
</div>
*/

function addProject(project) {
  console.log(project.title);
  const box = document.createElement("div");
  box.className = "project-box";
  box.id = "project-" +  project.projectId;
  
  const color = document.createElement("div");
  color.className = "color-div";
  color.style.backgroundColor = stringToColorCode(project.title);

  const info = document.createElement("div");
  info.className = "info-div";

  const title = document.createElement("div");
  title.className = "project-title";
  title.innerText = project.title;

  const role = document.createElement("div");
  role.className = "project-role";
  role.innerText = project.userRole;

  //link box
  box.onclick = function() { 
    console.log("clicked" + project.projectId)
    localStorage.setItem("projectId", project.projectId);
    window.location.href = './issue';
  };

  info.appendChild(title);
  info.appendChild(role);
  box.appendChild(color);
  box.appendChild(info);
  all_project.appendChild(box);
}

function stringToColorCode(str) {
  // 문자열을 숫자로 변환
  let hash = 0;
  for (let i = 0; i < str.length; i++) {
      hash = str.charCodeAt(i) + ((hash << 5) - hash);
  }
  
  // 해시 값을 16진수 색 코드로 변환
  let color = "#";
  for (let i = 0; i < 3; i++) {
      // 해시에서 색상 값을 가져오고, 밝기를 조정
      let value = (hash >> (i * 8)) & 0xFF;
      // 밝기 조정을 위해 중간 값 (128)과의 평균을 취함
      value = Math.floor((value + 128) / 2);
      // 16진수로 변환하고, 두 자리 수를 맞춤
      color += ('00' + value.toString(16)).slice(-2);
  }
  
  return color;
}