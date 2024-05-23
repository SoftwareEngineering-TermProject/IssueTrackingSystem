const tbody = document.getElementById('tbody');
const all_issues = document.getElementById("");
var issues = null;

// load all issues
const issueRequest = new XMLHttpRequest();
issueRequest.open('GET', url + `issues/`);
issueRequest.setRequestHeader("Content-Type", "application/json");

issueRequest.send();
issueRequest.onload = () => {
    if( issueRequest.status === 200 ) {
        const result = JSON.parse(issueRequest.response).result;
        issues = result.issues;
        console.log(issues);
        issues.forEach(element => {
            addIssue(element);
        });
    } else {
        alert("이슈 목록을 받아오지 못했습니다.");
        console.error("Error", issueRequest.status, issueRequest.statusText);
    }
};

function addIssue(issue){
    const tr = document.createElement('tr');
    const td_id = document.createElement('td');
    const td_title = document.createElement('td');
    const td_reporter = document.createElement('td');
    const td_fixer = document.createElement('td');
    const td_assignee = document.createElement('td');
    const td_priority = document.createElement('td');
    const td_status = document.createElement('td');
    const td_date = document.createElement('td');
    
    td_id.innerText = issue.issueId;
    td_title.innerText = issue.title;
    td_reporter.innerText = issue.user.userName;
    td_fixer.innerText = issue.fixer;
    td_assignee.innerText = issue.assignee;
    td_priority.innerText = issue.priority;
    td_status.innerText = issue.issueStatus;
    td_date.innerText = issue.date;

    tr.appendChild(td_id);
    tr.appendChild(td_title);
    tr.appendChild(td_reporter);
    tr.appendChild(td_fixer);
    tr.appendChild(td_assignee);
    tr.appendChild(td_priority);
    tr.appendChild(td_status);
    tr.appendChild(td_date);

    tbody.appendChild(tr);
    tr.addEventListener("click", openDetail);
}