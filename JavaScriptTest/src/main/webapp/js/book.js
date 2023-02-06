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
printBookList();
// 전체목록 담아놓을 용도.
function printBookList() {
  bookList.forEach(item => {
		let tr = document.createElement("tr");
		let td = document.createElement("td");
		let input = document.createElement("input");
		input.setAttribute("type","checkbox");
		td.append(input);
		tr.append(td);
		
		td = document.createElement("td");
		td.innerText = item.bookCode;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookTitle;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookAuthor;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookPress;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookPrice;
		tr.append(td);

		td = document.createElement("td");
		let button = document.createElement("button");
		button.innerText="삭제";

		td.append(button);
		tr.append(td);
		list.append(tr);
		
	});

}
// 저장 처리 함수.
/* function addFnc() {
  let add={
   bookCode : document.querySelector("#bookCode").value,
   bookTitle : document.querySelector("#bookName").value,
   bookAuthor : document.querySelector("#author").value,
   bookPress : document.querySelector("#press").value,
   bookPrice : document.querySelector("#price").value,
  };

  bookList.push(add);
  printBookList();

  document.querySelector("#bookCode").value = "";
  document.querySelector("#bookName").value = "";
  document.querySelector("#author").value = "";
  document.querySelector("#press").value = "";
  document.querySelector("#price").value = "";
 
} */


checkSv.addEventListener("click", function(){
	// 저장버튼 여러번 클릭시 초기화(그전에 있던 리스트들이 또 등장하지않도록)
	list.innerText="";
	let add = {
					bookCode:bookCode.value,
			    bookTitle:bookName.value,
		      bookAuthor:author.value,
	        bookPress:press.value,
          bookPrice: price.value
					};

	bookList.push(add);
	
	// 입력후 입력항목 초기화
	bookCode.value="";
	bookName.value="";
	author.value="";
	press.value="";
	price.value="";

	printBookList(); // 도서 목록(리스트)
});
//삭제
function deleteOneSelect() {
  let delCode = this.closest("tr").children[1];
  let index = bookList.findIndex((e) => e.bookCode == delCode.innerText);
  bookList.splice(index, 1);
  showList();
}

// 전체선택 체크박스.
document
  .querySelector('thead input[type="checkbox"]')
  .addEventListener("change", allCheckChange);

// 선택삭제 버튼.
document.querySelector("#checkDel").addEventListener("click", deleteCheckedFnc);
function deleteCheckedFnc() {
  let tr = document.querySelectorAll('tbody input[type="checkbox"]:checked');
  if (tr.length == 0) {
    alert("선택된 사원이 없습니다");
    return;
  }
  for (let i = 0; i < tr.length; i++) {
    let delCode = tr[i].closest("tr").children[1].innerText;
    let index = bookList.findIndex((e) => e.bookCode == delCode);
    bookList.splice(index, 1);
  }
  printBookList();
}


  // 체크박스.
  td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.addEventListener("change", checkAllFnc);
  td.append(chk);
  tr.append(td);




// 5번문제 

// 전체선택 체크박스 
function checkAllFnc() {
  let allTr = document.querySelectorAll("tbody#list tr");
  let chkTr = document.querySelectorAll(
    'tbody#list input[type="checkbox"]:checked'
  );
  if (allTr.length == chkTr.length)
    document.querySelector('thead input[type="checkbox"]').checked = true;
  else document.querySelector('thead input[type="checkbox"]').checked = false;
}

// 삭제버튼 이벤트 콜백함수.





// 전체선택 체크박스.
function allCheckChange() {
  console.log(this.checked);
  // tbody에 있는 체크박스 선택.
  document.querySelectorAll('tbody input[type="checkbox"]').forEach((chk) => {
    chk.checked = this.checked;
  });
}
//6번
checkDel.addEventListener("click", function(){
	let selectAll = document.querySelectorAll("#list input[type='checkbox']");
	selectAll.forEach(item => {
		if(item.checked == true){
			item.closest("tr").remove();
		}
	});
})


