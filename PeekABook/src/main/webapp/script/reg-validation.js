const namePattern = /^[A-Za-z]+[\s*[A-Za-z]+\s*[A-Za-z]*]*$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const phonePattern = /^([0-9]{10})$/;
const civicoPattern = /^([0-9]+)$/;
const userPattern = /^[\w]+$/;
const capPattern= /^([\d]{5})$/;

const nameErrorMessage = "Può contenere solo lettere";
const emailErrorMessage = "Deve essere nel formato username@domain.ext";
const phoneErrorMessage = "Deve essere nel formato ##########";
const civicoErrorMessage = "Può contenere solo numeri";
const capErrorMessage = "Deve essere nel formato #####";
const usernameErrorMessage = "Può contenere solo caratteri alfanumerici";

let smallName = document.getElementById("errorName");
let smallSurname = document.getElementById("errorSurname");
let smallUsername = document.getElementById("errorUsername");
let smallPassword = document.getElementById("errorPassword");
let smallEmail = document.getElementById("errorEmail");
let smallVia = document.getElementById("errorVia");
let smallCitta = document.getElementById("errorCitta");
let smallCap = document.getElementById("errorCap");
let smallCivico = document.getElementById("errorCivico");

form.addEventListener('submit', e => {
    e.preventDefault();

    var x = validate();
	
	console.log(form.action);
	
    if(x==9){
        form.submit();
    }
});


function validate(){
	let valid= true;
	let form = document.getElementById("form");
	let smallName = document.getElementById("errorName");
	let smallSurname = document.getElementById("errorSurname");
	let smallUsername = document.getElementById("errorUsername");
	let smallPassword = document.getElementById("errorPassword");
	let smallEmail = document.getElementById("errorEmail");
	let smallVia = document.getElementById("errorVia");
	let smallCitta = document.getElementById("errorCitta");
	let smallCap = document.getElementById("errorCap");
	let smallCivico = document.getElementById("errorCivico");
	
	
	var total = 0;
	
	if(validateFormElem(form.name,namePattern,smallName,nameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.surname,namePattern,smallSurname,nameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.username,userPattern,smallUsername,usernameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.password,userPattern,smallPassword,usernameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.email,emailPattern,smallEmail,emailErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.address,namePattern,smallVia,nameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.address2,civicoPattern,smallCivico,civicoErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.address3,namePattern,smallCitta,nameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.cap,capPattern,smallCap,capErrorMessage)){
		total++;
	}
	
	return total;
}

function validateFormElem(formElem, pattern, span, message) {
	if(!(formElem.value.match(pattern))||formElem.value===""){
		formElem.parentElement.className = 'form-control error';
    	span.innerText = message;
		return false;
	}
	formElem.parentElement.className = 'form-control success';
	return true;
}