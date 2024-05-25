//detailed issue part
const detail = document.getElementById("detail-wrapper");
const closeBtn = document.getElementsByClassName("close-detail")[0];
const detail_title = document.getElementById("detail-title");
const detail_description = document.getElementById("detail-description");
const detail_date = document.getElementById("detail-date");
const detail_priority = document.getElementById("detail-priority");
const detail_status = document.getElementById("detail-status");
const detail_reporter = document.getElementById("detail-reporter");

function openDetail(event) {
  const issue_id = localStorage.getItem("issueId");
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
      detail_priority.value = issue.priority;
      detail_status.value = issue.status;
      detail_reporter.innerText = "Reporter : " + issue.user.userName;

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
