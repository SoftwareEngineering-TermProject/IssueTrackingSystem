const nav_projects = document.getElementById("nav-projects");
const nav_log_out = document.getElementById("nav-log-out");

nav_projects.addEventListener("click", (event) => {
    console.log("click nav project");
    window.location.href = './project';
});

nav_log_out.addEventListener("click", (event) => {
    console.log("click log out");
    localStorage.clear();
    window.location.href = './';
});