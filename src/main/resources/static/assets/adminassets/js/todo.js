const toDoForm = document.getElementById("todo-form");
const toDoInput = toDoForm.querySelector("#todo-form input");
const toDoList = document.getElementById("todo-list");
const TODOS_KEY = "todos";

let toDos = []; // newToDo를 담을 배열

function saveToDos(){ // toDos 배열의 내용을 localStorage에 넣음
    localStorage.setItem(TODOS_KEY, JSON.stringify(toDos));
}

function deleteToDo(event){
    // console.log(event.target.parentElement);
    const li = event.target.parentElement; // 삭제하려는 li
    // console.log(li.id); // 삭제하기전 li의 id를 얻음
    li.remove();
    // toDo는 toDos의 DB에 있는 요소 중 하나
    // toDO의 id가 li.id와 같은걸 제외하고 기존 DB의 요소들 출력(li.id와 같은걸 삭제함)
    // li.id는 string -> toDo.id와 같은 타입인 number로 맞춰야 함
    toDos = toDos.filter((toDo) => toDo.id !== parseInt(li.id));
    saveToDos();
}

// <li th:if="${session.memberLogin.user_auth == '1'}">
// <li style="border: none;">

function paintToDo(newToDo){
    // console.log("I will paint " + newToDo);
    const li = document.createElement("li");
    li.id = newToDo.id;
    const span = document.createElement("span");
    span.innerText = newToDo.text;
    
    const button = document.createElement("button");
    button.style.border = "none";
    button.style.background = "none";
    button.innerText = "❎";
    button.addEventListener("click", deleteToDo);

    li.appendChild(span);
    li.appendChild(button);
    // console.log(li);
    toDoList.appendChild(li);
}

function handleToDoSubmit(event){
    event.preventDefault();
    const newToDo = toDoInput.value; // input내용을 newToDo에 저장
    // console.log(toDoInput.value);
    toDoInput.value = "";

    const newTodoObj = {
        text: newToDo,
        id: Date.now(),
    };
    // console.log(newToDo, toDoInput.value); // toDoInput의 값은 사라지고 newToDo에 저장됨
    toDos.push(newTodoObj);
    paintToDo(newTodoObj);
    saveToDos();
}

toDoForm.addEventListener("submit", handleToDoSubmit);

// function sayHello(item){
//     console.log("this is the turn of " + item);
// }

const savedToDos = localStorage.getItem(TODOS_KEY);
// console.log(savedToDos);
if(savedToDos !== null){ // localStorage에 있는 string을 object로 변경
    const parsedToDos = JSON.parse(savedToDos);
    // console.log(parsedToDos);
    // localStorage에 있는 내용을 최초의 배열에 저장(새로고침해도 사라지지 않도록)
    toDos = parsedToDos;
    // array의 각 item에 대해 sayHello함수를 차례대로 실행하게 해줌
    parsedToDos.forEach(paintToDo);
}

// sexyFilter()가 새 array에 요소들을 유지하고 싶으면 true 반환해야함
// function sexyFilter(item){
    
// }