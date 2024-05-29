const select_year = document.getElementById("select-year");

for(var i=2024;i>=2000;i--){
    const opt = document.createElement("option");
    opt.innerText = i;
    opt.value = i;
    select_year.appendChild(opt);
}

select_year.addEventListener("change", () => {
    loadStatistics();
})

const project_id = localStorage.getItem("projectId");
const project_title = document.getElementById("project-title");
project_title.addEventListener("click", (event) => {
    window.location.href = './issue';
})

const user_info_div = document.getElementById("user-info");
user_info_div.innerText = `${localStorage.getItem("name")}(${localStorage.getItem("role")})`
//load statistics info
function loadStatistics() {
    const statisticsRequest = new XMLHttpRequest();
    statisticsRequest.open('GET', url + `issues/statistic/${project_id}?year=${select_year.value}`);
    statisticsRequest.setRequestHeader("Content-Type", "application/json");
    
    statisticsRequest.send();
    statisticsRequest.onload = () => {
        if (statisticsRequest.status === 200) {
            const result = JSON.parse(statisticsRequest.response).result;
            const issue_count = result.issue_count;
            console.log(result);
            project_title.innerText = result.title;
            // parse data
            data.datasets.forEach(e => e.data = []);
            issue_count.forEach((e) => {
                data.datasets[0].data.push(e.total);
                data.datasets[1].data.push(e.blocker);
                data.datasets[2].data.push(e.critical);
                data.datasets[3].data.push(e.major);
                data.datasets[4].data.push(e.minor);
                data.datasets[5].data.push(e.trivial);
            });
            console.log(data)
            myChart.update();
        } else {
            alert("통계를 받아오지 못했습니다.");
            console.error("Error", statisticsRequest.status, statisticsRequest.statusText);
        }
    };
}
loadStatistics();

// chart.js
const ctx = document.getElementById('myChart').getContext('2d');

var data = {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'],
    datasets: [
        {
            label: 'Total',
            data: [],
            backgroundColor: 'rgba(66, 68, 78, 0.2)',
            borderColor: 'rgba(66, 68, 78, 1)',
            borderWidth: 1
        },
        {
            label: 'Blocker',
            data: [],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        },
        {
            label: 'Critical',
            data: [],
            backgroundColor: 'rgba(153, 102, 255, 0.2)',
            borderColor: 'rgba(153, 102, 255, 1)',
            borderWidth: 1
        },
        {
            label: 'Major',
            data: [],
            backgroundColor: 'rgba(255, 206, 86, 0.2)',
            borderColor: 'rgba(255, 206, 86, 1)',
            borderWidth: 1
        },
        {
            label: 'Minor',
            data: [],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1) ',
            borderWidth: 1
        },
        {
            label: 'Trivial',
            data: [],
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
        },
    ]
};

const options = {
    scales: {
        y: {
            beginAtZero: true,
            ticks: {
                stepSize: 1
            }
        }
    }
};

const myChart = new Chart(ctx, {
    type: 'bar',
    data: data,
    options: options
});


//control chart
var currentMode = 0;
const filter = document.getElementById('filter');
filter.addEventListener('click', function() {
    currentMode = (currentMode + 1) % 3; // 다음 모드로 변경

    myChart.data.datasets.forEach((dataset, index) => {
        if (currentMode === 0) {
            dataset.hidden = false; // 모두 노출
            filter.innerText = "Filter : all";
        } else if (currentMode === 1) {
            dataset.hidden = dataset.label !== "Total"; // Total만 노출
            filter.innerText = "Filter : total";
        } else {
            dataset.hidden = dataset.label === "Total"; // Total 제외
            filter.innerText = "Filter : priority";
        }
    });

    myChart.update();
});