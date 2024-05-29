const project_id = localStorage.getItem("projectId");
const user_id = localStorage.getItem("userId");
const project_title = document.getElementById("project-title");

// load project info
const projectRequest = new XMLHttpRequest();
projectRequest.open('GET', url + `projects/${project_id}`);
projectRequest.setRequestHeader("Content-Type", "application/json");

projectRequest.send();
projectRequest.onload = () => {
    if( projectRequest.status === 200 ) {
        const result = JSON.parse(projectRequest.response).result;
        const title = result.title;
        project_title.innerText = title;
    } else {
        const err_msg = JSON.parse(projectRequest.response).message
        alert(err_msg);
        console.log(err_msg);
        console.error("Error", projectRequest.status, projectRequest.statusText);
    }
};

const tbody = document.getElementById('tbody');
const all_issues = document.getElementById("");

//functions for show issues
function clearIssue() {
    while(tbody.firstChild) {
	    tbody.removeChild(tbody.firstChild);
	}
}

function addIssue(issue,index,length){
    const tr = document.createElement('tr');
    const td_id = document.createElement('td');
    const td_title = document.createElement('td');
    const td_reporter = document.createElement('td');
    const td_fixer = document.createElement('td');
    const td_assignee = document.createElement('td');
    const td_priority = document.createElement('td');
    const td_status = document.createElement('td');
    const td_date = document.createElement('td');
    
    td_id.innerText = length - index;
    td_title.innerText = issue.title;
    td_reporter.innerText = issue.user.userName;
    td_fixer.innerText = issue.fixer;
    td_assignee.innerText = issue.assignee;
    td_priority.innerText = issue.issuePriority;
    td_status.innerText = issue.issueStatus;
    td_date.innerText = issue.createAt.substr(0,16);

    tr.appendChild(td_id);
    tr.appendChild(td_title);
    tr.appendChild(td_reporter);
    tr.appendChild(td_fixer);
    tr.appendChild(td_assignee);
    tr.appendChild(td_priority);
    tr.appendChild(td_status);
    tr.appendChild(td_date);

    tbody.appendChild(tr);

    tr.addEventListener("click", (e) => {
        localStorage.setItem("issueId",issue.issueId);
        openDetail(e);
    });
}

// load all issues
function loadAllIssues(mode = 0) {
    const issueRequest = new XMLHttpRequest();
    if (mode == 0) { issueRequest.open('GET', url + `issues/list/${project_id}`); }
    else { issueRequest.open('GET', url + `issues/list/assignee/${project_id}?userId=${user_id}`); }
    issueRequest.setRequestHeader("Content-Type", "application/json");

    issueRequest.send();
    issueRequest.onload = () => {
        if( issueRequest.status === 200 ) {
            const result = JSON.parse(issueRequest.response).result;
            issues = result.issues;
            console.log(issues);
            var length = issues.length;
            clearIssue();
            issues.forEach((element,index) => {
                addIssue(element,index,length);
            });
        } else {
            const err_msg = JSON.parse(issueRequest.response).message
            alert(err_msg);
            console.log(err_msg);
            console.error("Error", issueRequest.status, issueRequest.statusText);
        }
    };    
}
loadAllIssues(0);

//search
const search_form = document.getElementById("search-form");
const search_input = document.getElementById("search-input");
const search_result = document.getElementById("search-result");

search_form.addEventListener("submit", (event) => {
    event.preventDefault();

    const keyword = search_input.value;
    
    const issueRequest = new XMLHttpRequest();
    issueRequest.open('GET', url + `issues/list/${project_id}?search=${keyword}`);
    issueRequest.setRequestHeader("Content-Type", "application/json");
    issueRequest.send();
    issueRequest.onload = () => {
        if( issueRequest.status === 200 ) {
            const result = JSON.parse(issueRequest.response).result;
            issues = result.issues;
            console.log(issues);
            var length = issues.length;
            search_result.innerText = (issues.length == 0) ? "Found nothing" : `Found ${length} issue`;
            clearIssue();
            issues.forEach((element,index) => {
                addIssue(element,index,length);
            });
        } else {
            alert("이슈 목록을 받아오지 못했습니다.");
            console.error("Error", issueRequest.status, issueRequest.statusText);
        }
    };
})

//toggle assigned to me
const filter_btn = document.getElementById("filter");
filter_btn.addEventListener("click", toggleFilter);
var filter_mode = 0;
function toggleFilter() {
    filter_mode = (filter_mode + 1) % 2;
    loadAllIssues(filter_mode);
    if (filter_mode == 0) { filter_btn.innerText = "Filter : all" }
    else { filter_btn.innerText = "Filter : assigned to me" }
}

//sort by 
const sort_priority_btn = document.getElementById("sort-priority-btn");
const sort_status_btn = document.getElementById("sort-status-btn");
const sort_date_btn = document.getElementById("sort-date-btn");

var is_descend_priority = false;
var is_descend_status = false;
var is_descend_date = false;

sort_priority_btn.addEventListener("click", () => {
    issues.sort((a,b) => {
        var val_a;
        var val_b;
        switch (a.issuePriority) {
            case "BLOCKER" : 
            val_a = 5;    
            break;
            case "CRITICAL" : 
            val_a = 4;    
            break;
            case "MAJOR" : 
            val_a = 3;    
            break;
            case "MINOR" : 
            val_a = 2;    
            break;
            case "TRIVIAL" : 
            val_a = 1;    
            break;
        } 
        switch (b.issuePriority) {
            case "BLOCKER" : 
            val_b = 5;    
            break;
            case "CRITICAL" : 
            val_b = 4;    
            break;
            case "MAJOR" : 
            val_b = 3;    
            break;
            case "MINOR" : 
            val_b = 2;    
            break;
            case "TRIVIAL" : 
            val_b = 1;    
            break;
        } 
        if (is_descend_priority) { return val_a - val_b; }
        else { return val_b - val_a; }        
    });
    is_descend_priority = !is_descend_priority;
    if(is_descend_priority) {sort_priority_btn.innerText = "▼";}
    else {sort_priority_btn.innerText = "▲";}
    var length = issues.length;
    clearIssue();
    issues.forEach((element,index) => {
        addIssue(element,index,length);
    });
});

sort_status_btn.addEventListener("click", () => {
    issues.sort((a,b) => {
        var val_a;
        var val_b;
        switch (a.issueStatus) {
            case "NEW" : 
            val_a = 5;    
            break;
            case "ASSIGNED" : 
            val_a = 4;    
            break;
            case "RESOLVED" : 
            val_a = 3;    
            break;
            case "CLOSED" : 
            val_a = 2;    
            break;
            case "REOPEND" : 
            val_a = 1;    
            break;
        } 
        switch (b.issueStatus) {
            case "NEW" : 
            val_b = 5;    
            break;
            case "ASSIGNED" : 
            val_b = 4;    
            break;
            case "RESOLVED" : 
            val_b = 3;    
            break;
            case "CLOSED" : 
            val_b = 2;    
            break;
            case "REOPEND" : 
            val_b = 1;    
            break;
        } 
        if (is_descend_status) { return val_a - val_b; }
        else { return val_b - val_a; }        
    });
    is_descend_status = !is_descend_status;
    if(is_descend_status) {sort_status_btn.innerText = "▼";}
    else {sort_status_btn.innerText = "▲";}
    var length = issues.length;
    clearIssue();
    issues.forEach((element,index) => {
        addIssue(element,index,length);
    });
});

sort_date_btn.addEventListener("click", () => {
    var mult;
    if (is_descend_date) { mult = 1; }
    else { mult = -1; }
    issues.sort((a,b) => {
        if ( a.createAt > b.createAt ) { return -1 * mult; }
        else { return  1 * mult; }
    });
    console.log("sorted by date" + " is_descend : " + is_descend_date);
    is_descend_date = !is_descend_date;
    if(is_descend_date) {sort_date_btn.innerText = "▲";}
    else {sort_date_btn.innerText = "▼";}
    var length = issues.length;
    clearIssue();
    issues.forEach((element,index) => {
        addIssue(element,index,length);
    });
});

//jump to statistics
const statistics = document.getElementById("statistics");

statistics.addEventListener("click", ()=>{
    window.location.href = './statistics';
});