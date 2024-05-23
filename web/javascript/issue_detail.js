//detailed issue part
const detail = document.getElementById("detail-wrapper");
const closeBtn = document.getElementsByClassName("close-detail")[0];
const detail_title = document.getElementById("detail-title");
const detail_description = document.getElementById("detail-description");

var issue;

function openDetail(event) {
    const issue_id = event.target.parentElement.childNodes[0].innerText;
    console.log(issue_id);
    issue = issues.find(e => e.issueId == issue_id);
    detail_title.innerText = issue.title;
    detail_description.innerText = issue.description;
    detail.style.display = "flex";
}

//close
closeBtn.onclick = function() {
  detail.style.display = "none";
};

//사용자가 모달 외부를 클릭하여 닫을 수 있도록
window.onclick = function(event) {
  if (event.target == detail) {
    detail.style.display = "none";
  }
};