const all_project = document.getElementById("all-projects");

//load project list
const projectRequest = new XMLHttpRequest();
projectRequest.open('GET', url + `projects`);
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
  
  const title = document.createElement("div");
  title.className = "project-title";
  title.innerText = project.title;

  //link box
  box.onclick = function() { 
    console.log("clicked" + project.projectId)
    localStorage.setItem("projectId", project.projectId);
    window.location.href = './issue.html';
  };

  box.appendChild(title);
  all_project.appendChild(box);
}