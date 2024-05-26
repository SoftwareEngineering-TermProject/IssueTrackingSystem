//detailed issue part
const detail = document.getElementById("detail-wrapper");
const closeBtn = document.getElementsByClassName("close-detail")[0];
const detail_title = document.getElementById("detail-title");
const detail_description = document.getElementById("detail-description");
const detail_date = document.getElementById("detail-date");
const detail_priority = document.getElementById("detail-priority");
const detail_status = document.getElementById("detail-status");
const detail_reporter = document.getElementById("detail-reporter");
const detail_assignee = document.getElementById("detail-assignee");
const detail_fixer = document.getElementById("detail-fixer");

var issue_id;

function openDetail(event) {
  issue_id = localStorage.getItem("issueId");
  console.log(issue_id);
  var issue;
  issueRequest.open('GET', url + `issues/${issue_id}`);
  issueRequest.setRequestHeader("Content-Type", "application/json");
  issueRequest.send();
  issueRequest.onload = () => {
    if (issueRequest.status === 200) {
      issue = JSON.parse(issueRequest.response).result;
      console.log(issue);
      detail_title.innerText = issue.title;
      detail_description.innerText = issue.description;
      detail_date.innerText = issue.createAt;
      detail_priority.value = issue.issuePriority;
      detail_status.value = issue.issueStatus;
      detail_reporter.innerText = "Reporter : " + issue.user.userName;
      detail_assignee.value = issue.assignee;
      detail_fixer.innerText = ((issue.fixer === null) ? "Not fixed" : "Fixer :" + issue.fixer);

      detail.style.display = "flex";
    } else {
      alert("이슈를 불러오지 못했습니다.");
      console.error("Error", issueRequest.status, issueRequest.statusText);
    }
  };
}

//close
closeBtn.onclick = function () {
  detail.style.display = "none";
};

window.addEventListener("click", (event) => {
  if (event.target == detail) {
    detail.style.display = "none";
  }
});

const assign_btn = document.getElementById("assign-btn");

assign_btn.addEventListener("click", (event) => {
  assign(event);
});

function assign(event) {
  event.preventDefault();
  const assignRequest = new XMLHttpRequest();
  assignRequest.open('POST', url + `issues/assignee/${issue_id}?userId=${localStorage.getItem("userId")}`);
  assignRequest.setRequestHeader("Content-Type", "application/json");

  var body = JSON.stringify({
    userName : detail_assignee.value
  });
  assignRequest.send(body);
  assignRequest.onload = () => {
    if (assignRequest.status === 200) {
      const result = JSON.parse(assignRequest.response).result;
      console.log(result);
      alert("담당자를 할당하였습니다.");
      location.reload(true);
    } else {
      alert("담당자를 할당하지 못했습니다.");
      console.error("Error", assignRequest.status, assignRequest.statusText);
    }
  };
}