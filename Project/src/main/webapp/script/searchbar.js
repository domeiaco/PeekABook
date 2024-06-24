const searchWrapper = document.querySelector(".search-input");
const inputBox = searchWrapper.querySelector("input");
const suggBox = searchWrapper.querySelector(".autocom-box");
const icon = searchWrapper.querySelector(".fa-search");
let linkTag = searchWrapper.querySelector("a");
let webLink;
let suggestions;

$("document").ready(function(){
	$.get("SearchAjax", function(response){
		suggestions = JSON.parse(response);
	})
})

inputBox.onkeyup = (e)=>{
	let userData = e.target.value;
	let emptyArray = [];
	if(userData){
		icon.onclick = ()=>{
			webLink = `http://localhost:8080/PeekABook/ShowSearchProducts?nome=${userData}`;
			linkTag.setAttribute("href",webLink);
			linkTag.click();
		}
		emptyArray = suggestions.filter((data)=>{
			return data.toLocaleLowerCase().includes(userData.toLocaleLowerCase());
		});
		emptyArray = emptyArray.map((data)=>{
			return `<li>${data}</li>`;
		});
		searchWrapper.classList.add("active");
		showSuggestions(emptyArray);
		let allList = suggBox.querySelectorAll("li");
		for(let value of allList){
			value.setAttribute("onclick", "select(this)");
		}
	}
	else{
		searchWrapper.classList.remove("active");
	}
}

function select(element){
	let selectData = element.textContent;
	inputBox.value = selectData;
	icon.onclick = ()=>{
		webLink= `http://localhost:8080/PeekABook/ShowSearchProducts?nome=${selectData}`;
		linkTag.setAttribute("href",webLink);
		linkTag.click();
	}
	searchWrapper.classList.remove("active");
}

function showSuggestions(list){
	let listData;
	let userValue;
    if(!list.length){
        userValue = inputBox.value;
        listData = `<li>${userValue}</li>`;
    }else{
        listData = list.join('');
    }
    suggBox.innerHTML = listData;
}