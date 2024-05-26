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
const detail_comment = document.getElementById("detail-comment");

var issue_id;

function openDetail(event, blinkLast = false) {
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
      detail_date.innerText = issue.createAt.substr(0,16);
      detail_priority.value = issue.issuePriority;
      detail_status.value = issue.issueStatus;
      detail_reporter.innerText = "Reported by " + issue.user.userName;
      detail_assignee.value = issue.assignee;
      detail_fixer.innerText = ((issue.fixer === null) ? "Not fixed" : "Fixed by " + issue.fixer);
      
      detail_comment.innerHTML = "";
      issue.comments.forEach((e) => {addComment(e)});
      if(blinkLast) { detail_comment.childNodes[0].classList.add("new"); }
      detail_comment.scrollTop = 0;
      detail.style.display = "flex";
    } else {
      alert("이슈를 불러오지 못했습니다.");
      console.error("Error", issueRequest.status, issueRequest.statusText);
    }
  };
}

//comment 
function addComment(comment) {
  const box = document.createElement("div");
  const top = document.createElement("div");
  const user = document.createElement("label")
  const time = document.createElement("label")
  const content = document.createElement("div");

  box.classList.add("comment-box");
  top.classList.add("comment-top");
  user.classList.add("comment-user");
  time.classList.add("comment-time");
  user.innerText = comment.user.userName;
  time.innerText = comment.createdAt.substr(0,16)
  content.innerText = comment.content;

  top.appendChild(user);
  top.appendChild(time);
  box.appendChild(top);
  box.appendChild(content);
  detail_comment.appendChild(box);
}

//create comment
const comment_btn = document.getElementById("comment-btn");
const comment_content = document.getElementById("comment-content");
comment_btn.addEventListener("click", createComment);
function createComment(event) {
  event.preventDefault();
  const commentRequest = new XMLHttpRequest();
  commentRequest.open('POST', url + `comments/?userId=${localStorage.getItem("userId")}`);
  commentRequest.setRequestHeader("Content-Type", "application/json");

  var body = JSON.stringify({
    issueId: issue_id,
    content: comment_content.value
  });
  commentRequest.send(body);
  commentRequest.onload = () => {
    if (commentRequest.status === 200) {
      const result = JSON.parse(commentRequest.response).result;
      console.log(result);
      comment_content.value = "";
      openDetail(event, true);
    } else {
      alert("댓글을 생성하지 못했습니다.");
      console.error("Error", commentRequest.status, commentRequest.statusText);
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
    userName: detail_assignee.value
  });
  assignRequest.send(body);
  assignRequest.onload = () => {
    if (assignRequest.status === 200) {
      const result = JSON.parse(assignRequest.response).result;
      console.log(result);
      changeState(event, "ASSIGNED", null );
      alert("담당자를 할당하였습니다.");
      location.reload(true);
    } else {
      alert("담당자를 할당하지 못했습니다.");
      console.error("Error", assignRequest.status, assignRequest.statusText);
    }
  };
}

const save_btn = document.getElementById("save-btn");

save_btn.addEventListener("click", (event) => {
  changeState(event, detail_status.value, detail_priority.value);
})

function changeState(event, status, priority) {
  event.preventDefault();
  const changeRequest = new XMLHttpRequest();
  changeRequest.open('PATCH', url + `issues/status/priority${issue_id}`);
  changeRequest.setRequestHeader("Content-Type", "application/json");

  var body = JSON.stringify({
    issueStatus: status,
    issuePriority: priority
  });
  changeRequest.send(body);
  changeRequest.onload = () => {
    if (changeRequest.status === 200) {
      const result = JSON.parse(changeRequest.response).result;
      console.log(result);
      alert("이슈를 수정하였습니다.");
      location.reload(true);
    } else {
      alert("이슈를 수정하지 못했습니다.");
      console.error("Error", changeRequest.status, changeRequest.statusText);
    }
  };
}

const fix_btn = document.getElementById("fix-btn");

fix_btn.addEventListener("click", (event) => {
  fixIssue(event);
})

function fixIssue(event) {
  event.preventDefault();
  const fixRequest = new XMLHttpRequest();
  fixRequest.open('POST', url + `issues/fixer/${issue_id}?userId=${localStorage.getItem("userId")}`);
  fixRequest.setRequestHeader("Content-Type", "application/json");

  fixRequest.send();
  fixRequest.onload = () => {
    if (fixRequest.status === 200) {
      const result = JSON.parse(fixRequest.response).result;
      console.log(result);
      alert("이슈를 fix하였습니다.");
      location.reload(true);
    } else {
      alert("이슈를 fix하지 못했습니다.");
      console.error("Error", fixRequest.status, fixRequest.statusText);
    }
  };
}