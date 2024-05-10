const tbody = document.getElementById('tbody');
var id = 0;

const issues = [
    {'id': 0,'title': 'draw picture', 'reporter': 'dev1', 'fixer': 'dev2', 'assignee': 'dev2', 'priority':'major', 'status':'closed', 'date':'24-04-30'},
    {'id': 1,'title': 'synchronization error', 'reporter': 'pl1', 'fixer': '', 'assignee': '', 'priority':'critical', 'status':'new', 'date':'24-05-03'},
    {'id': 2,'title': 'invalid link', 'reporter': 'dev1', 'fixer': '', 'assignee': 'dev3', 'priority':'trivial', 'status':'assigned', 'date':'24-05-05'},
    {'id': 3, 'title': 'synchronization error', 'reporter': 'pl1', 'fixer': '', 'assignee': '', 'priority': 'critical', 'status': 'new', 'date': '24-05-03'},
    {'id': 4, 'title': 'login failure', 'reporter': 'pl2', 'fixer': '', 'assignee': '', 'priority': 'high', 'status': 'open', 'date': '24-05-04'},
    {'id': 5, 'title': 'data corruption', 'reporter': 'pl1', 'fixer': '', 'assignee': '', 'priority': 'critical', 'status': 'assigned', 'date': '24-05-05'},
    {'id': 6, 'title': 'missing files', 'reporter': 'dev1', 'fixer': '', 'assignee': '', 'priority': 'medium', 'status': 'new', 'date': '24-05-06'},
    {'id': 7, 'title': 'server downtime', 'reporter': 'pl1', 'fixer': '', 'assignee': '', 'priority': 'critical', 'status': 'investigating', 'date': '24-05-07'},
    {'id': 8, 'title': 'email disruption', 'reporter': 'pl1', 'fixer': '', 'assignee': '', 'priority': 'high', 'status': 'new', 'date': '24-05-08'},
    {'id': 9, 'title': 'network instability', 'reporter': 'pl1', 'fixer': '', 'assignee': '', 'priority': 'high', 'status': 'open', 'date': '24-05-09'},
    {'id': 10, 'title': 'printer error', 'reporter': 'pl2', 'fixer': '', 'assignee': 'dev1', 'priority': 'medium', 'status': 'assigned', 'date': '24-05-10'},
];

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
    
    td_id.innerText = issue.id;
    td_title.innerText = issue.title;
    td_reporter.innerText = issue.reporter;
    td_fixer.innerText = issue.fixer;
    td_assignee.innerText = issue.assignee;
    td_priority.innerText = issue.priority;
    td_status.innerText = issue.status;
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
    tr.addEventListener("click", openIssue);
}

issues.map((element) => addIssue(element));

//detailed issue part
var modal = document.getElementById("myModal");
var closeBtn = document.getElementsByClassName("close-modal")[0];
function openIssue(event) {
    console.log(event.target.parentElement.childNodes[0]);
    modal.style.display = "flex";
}

//close
closeBtn.onclick = function() {
  modal.style.display = "none";
};

//사용자가 모달 외부를 클릭하여 닫을 수 있도록
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
};