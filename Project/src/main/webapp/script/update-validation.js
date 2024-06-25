const namePattern = /^[A-Za-z]{1,100}[\s*[A-Za-z]{0,100}\s*[A-Za-z]{0,100}]{0,100}$/;
const emailPattern = /^\S{1,30}@\S{1,30}\.\S{1,20}$/;
const civicoPattern = /^(\d+)$/;
const capPattern= /^(\d{5})$/;

const nameErrorMessage = "Può contenere solo lettere";
const emailErrorMessage = "Deve essere nel formato username@domain.ext";
const civicoErrorMessage = "Può contenere solo numeri (min='1')";
const capErrorMessage = "Deve essere nel formato ##### (min='00001')";


form.addEventListener('submit', e => {
    e.preventDefault();

    let x = validate();
	
	console.log(form.action);
	
    if(x==7){
        form.submit();
    }
});


function validate(){
	let form = document.getElementById("form");
	let smallName = document.getElementById("errorName");
	let smallSurname = document.getElementById("errorSurname");
	let smallEmail = document.getElementById("errorEmail");
	let smallVia = document.getElementById("errorVia");
	let smallCitta = document.getElementById("errorCitta");
	let smallCap = document.getElementById("errorCap");
	let smallCivico = document.getElementById("errorCivico");
	
	
	let total = 0;
	
	if(validateFormElem(form.name,namePattern,smallName,nameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.surname,namePattern,smallSurname,nameErrorMessage)){
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
	if((formElem===form.cap&&formElem.value==="00000")||(formElem===form.address2&&formElem.value==="0")){
		formElem.parentElement.className = 'form-control error';
    	span.innerText = message;
		return false;
	}
	if(!(formElem.value.match(pattern))||formElem.value===""){
		formElem.parentElement.className = 'form-control error';
    	span.innerText = message;
		return false;
	}
	formElem.parentElement.className = 'form-control success';
	return true;
}