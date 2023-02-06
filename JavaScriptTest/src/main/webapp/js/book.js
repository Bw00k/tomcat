/**
 *
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
  {
    bookCode: "P0206001",
    bookTitle: "이것이자바다",
    bookAuthor: "홍성문",
    bookPress: "신흥출판사",
    bookPrice: "20000",
  },
  {
    bookCode: "P0206002",
    bookTitle: "이것이자바스크립트다",
    bookAuthor: "홍영웅",
    bookPress: "우리출판사",
    bookPrice: "25000",
  },
];

// 목록출력하기.
// 전체목록 담아놓을 용도.

function printBookList() {
  let tr = document.createElement("tr");
  bookList.forEach(function (item) {
    let td = document.createElement("td");
    td.textContent = item.bookTitle;
    tr.appendChild(td);
  });
}

// 저장버튼에 submit 이벤트 등록.
document
  .querySelector('form[name="empForm"]')
  .addEventListener("submit", addMemberFnc);

// 전체선택 체크박스.
document
  .querySelector('thead input[type="checkbox"]')
  .addEventListener("change", allCheckChange);

// 선택삭제 버튼.
document.querySelector("#checkDel").addEventListener("click", deleteCheckedFnc);

// 데이터 한건 활용해서 tr 요소를 생성.
function makeTr(item = {}) {
  // DOM 요소생성.
  let titles = ["id", "lastName", "email", "hireDate", "job"];
  // 데이터 건수만큼 반복.
  let tr = document.createElement("tr");
  // columns 갯수만큼 반복.
  titles.forEach(function (title) {
    let td = document.createElement("td");
    td.innerText = item[title];
    tr.append(td);
  });
  // 삭제.
  let td = document.createElement("td");
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.addEventListener("click", deleteRowFunc);
  td.append(btn);
  tr.append(td);
  // 수정.
  td = document.createElement("td");
  btn = document.createElement("button");
  btn.innerText = "수정";
  btn.addEventListener("click", modifyTrFunc);
  td.append(btn);
  tr.append(td);
  // 체크박스.
  td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.addEventListener("change", checkAllFnc);
  td.append(chk);
  tr.append(td);

  // tr반환.
  return tr;
}

// 전체선택 체크박스 - 개별체크박스 동기화.
function checkAllFnc() {
  // 전체건수 vs. 선택건수 비교.
  let allTr = document.querySelectorAll("tbody#list tr");
  let chkTr = document.querySelectorAll(
    'tbody#list input[type="checkbox"]:checked'
  );
  if (allTr.length == chkTr.length)
    document.querySelector('thead input[type="checkbox"]').checked = true;
  else document.querySelector('thead input[type="checkbox"]').checked = false;
}

// 삭제버튼 이벤트 콜백함수.

// 삭제:비활성화, 변경: DB반영.
let btn = document.createElement("button");
btn.innerText = "삭제";
btn.disabled = true;
td = document.createElement("td");
td.append(btn);
newTr.append(td);

thisTr.replaceWith(newTr);

// 저장 처리 함수.
function addMemberFnc(evnt) {
  evnt.preventDefault();
  console.log("여기에 출력.");
  let bookCode = document.querySelector('input[id="bookCode"]').value;
  let bookName = document.querySelector('input[id="bookName"]').value;
  let author = document.querySelector('input[id="author"]').value;
  let press = document.querySelector('input[ id="press"]').value;
  let price = document.querySelector('input[id="price"]').value;

  if (!bookCode || !bookName || !author || !press || !price) {
    alert("필수입력값을 확인!!");
    return;
  }
}

// 전체선택 체크박스.
function allCheckChange() {
  console.log(this.checked);
  // tbody에 있는 체크박스 선택.
  document.querySelectorAll('tbody input[type="checkbox"]').forEach((chk) => {
    chk.checked = this.checked;
  });
}

// fetch API => 비동기방식처리. => 동기식 처리. (async, await)
async function deleteCheckedFnc() {
  let ids = [];
  let chks = document.querySelectorAll('#list input[type="checkbox"]:checked');

  for (let i = 0; i < chks.length; i++) {
    let id = chks[i].parentElement.parentElement.firstChild.innerText;
    let resp = await fetch("../empListJson?del_id=" + id, {
      method: "DELETE",
    });
    let json = await resp.json();
    console.log(json);
    ids.push(json);
  }
  console.log("ids>>>>", ids); // alert(101, 102, 103 삭제했습니다!)

  processAfterFetch(ids); // [{id:101,retCode:Success},{id:101,retCode:Success},]
}
// 화면처리.
function processAfterFetch(ary = []) {
  let targetTr = document.querySelectorAll("#list tr");
  console.log(targetTr, " vs ", ary);
  // targetTr vs. ary
  targetTr.forEach((tr) => {
    for (let i = 0; i < ary.length; i++) {
      if (tr.children[0].innerText == ary[i].id) {
        if (ary[i].retCode == "Success") {
          tr.remove(); // Success 조건 하에 삭제.
        } else {
          tr.setAttribute("class", "delError");
        }
      }
    }
  });
}

// 사원 목록()
function employeeList(curPage = 1) {
  // init.
  document.querySelectorAll("#list tr").forEach((item) => item.remove());
  // 페이지별로 보여줄 건수 지정.localStorage.setItem('pagePerCnt', this.value);
  let cntPerPage = parseInt(localStorage.getItem("pagePerCnt"));
  let end = curPage * cntPerPage;
  let start = end - (cntPerPage - 1);
  let newList = totalAry.filter((emp, idx) => {
    return idx + 1 >= start && idx < end;
  });
  let lst = document.getElementById("list");
  newList.forEach((emp) => {
    let tr = makeTr(emp);
    lst.append(tr);
  });
}
