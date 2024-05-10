var modalBtn = document.getElementById("create-project-btn");
var modal = document.getElementById("myModal");
var closeBtn = document.getElementsByClassName("close")[0];
var projectBoxes = document.getElementsByClassName("project-box");

// 모달 열기 버튼 클릭 시
modalBtn.onclick = function() {
  modal.style.display = "block";
};

// 모달 닫기 버튼 클릭 시
closeBtn.onclick = function() {
  modal.style.display = "none";
};

// 사용자가 모달 외부를 클릭하여 닫을 수 있도록
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
};

for(let e of projectBoxes) {
  e.onclick = function() { window.location.href = './issue.html';};
};