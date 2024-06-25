const namePattern = /^[A-Za-z]{1,100}[\s*[A-Za-z]{0,100}\s*[A-Za-z]{0,100}]{0,100}$/;
const civicoPattern = /^(\d+)$/;
const capPattern = /^(\d{5})$/;
const mastercardPattern = /^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$/;
const amexPattern = /^3[47][0-9]{13}$/;
const visaPattern = /^4[0-9]{12}(?:[0-9]{3})?$/;
const scadenzaPattern = /^(0[1-9]|1[0-2])\/?([0-9]{2})$/;
const cvvPattern = /^[0-9]{3}$/;

const nameErrorMessage = "Può contenere solo lettere";
const civicoErrorMessage = "Può contenere solo numeri (min='1')";
const capErrorMessage = "Deve essere nel formato ##### (min='00001')";
const mastercardErrorMessage = "Il numero inserito non corrisponde ad una carta Mastercard";
const amexErrorMessage = "Il numero inserito non corrisponde ad una carta American Express";
const visaErrorMessage = "Il numero inserito non corrisponde ad una carta Visa";
const scadenzaErrorMessage = "Deve essere nel formato MM/YY";
const cvvErrorMessage = "Deve essere nel formato ###"


form.addEventListener('submit', e => {
	e.preventDefault();

	let x = validate();

	console.log(form.action);

	if (x == 7) {
		form.submit();
	}
});


function validate() {
	let form = document.getElementById("form");
	let smallVia = document.getElementById("errorVia");
	let smallCitta = document.getElementById("errorCitta");
	let smallCap = document.getElementById("errorCap");
	let smallCivico = document.getElementById("errorCivico");
	let smallScadenza = document.getElementById("errorScadenza");
	let smallNumero = document.getElementById("errorNumero");
	let smallCvv = document.getElementById("errorCvv");



	let total = 0;


	if (validateFormElem(form.address, namePattern, smallVia, nameErrorMessage)) {
		total++;
	}

	if (validateFormElem(form.address2, civicoPattern, smallCivico, civicoErrorMessage)) {
		total++;
	}

	if (validateFormElem(form.address3, namePattern, smallCitta, nameErrorMessage)) {
		total++;
	}

	if (validateFormElem(form.cap, capPattern, smallCap, capErrorMessage)) {
		total++;
	}

	if (document.getElementById("circuito").value == "Mastercard") {
		if (validateFormElem(form.numero, mastercardPattern, smallNumero, mastercardErrorMessage)) {
			total++;
		}
	}
	else if(document.getElementById("circuito").value == "American Express") {
		if (validateFormElem(form.numero, amexPattern, smallNumero, amexErrorMessage)) {
			total++;
		}
	}
	
	else if(document.getElementById("circuito").value == "Visa") {
		if (validateFormElem(form.numero, visaPattern, smallNumero, visaErrorMessage)) {
			total++;
		}
	}

	if (validateFormElem(form.scadenza, scadenzaPattern, smallScadenza, scadenzaErrorMessage)) {
		total++;
	}

	if (validateFormElem(form.cvv, cvvPattern, smallCvv, cvvErrorMessage)) {
		total++;
	}

	return total;
}

function validateFormElem(formElem, pattern, span, message) {
	if ((formElem === form.cap && formElem.value === "00000") || (formElem === form.address2 && formElem.value === "0")) {
		formElem.parentElement.className = 'form-control error';
		span.innerText = message;
		return false;
	}
	if (!(formElem.value.match(pattern)) || formElem.value === "") {
		formElem.parentElement.className = 'form-control error';
		span.innerText = message;
		return false;
	}
	formElem.parentElement.className = 'form-control success';
	return true;
}

function changeCircuito(circuitValue){
	if(circuitValue=="Mastercard"){
		document.getElementById("circuitImg").src="icons/mastercard.png";
		document.getElementById("numero").placeholder="Numero di 16 cifre senza spazi";
	}
	else if(circuitValue=="Visa"){
		document.getElementById("circuitImg").src="icons/visa.png";
		document.getElementById("numero").placeholder="Numero di 16 cifre senza spazi";
	}
	else if(circuitValue=="American Express"){
		document.getElementById("circuitImg").src="icons/amex.png";
		document.getElementById("numero").placeholder="Numero di 15 cifre senza spazi";
	}
}