<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Aggiungi</title>
	<link rel="stylesheet" href="./css/registration.css">
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
</head>

<%
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if(isAdmin==null||!isAdmin){
	response.sendRedirect("http://localhost:8080/PeekABook/login.html");
	return;
}
%>

<body>
	<div class="container">
		<div class="header">
			<h2>Aggiungi autore</h2>
		</div>
		<form  class="form" id="form" method="post" action="InsertAutore" >
			<div class="form-control">
				<label for="name">Nome</label>
				<input type="text" placeholder="" id="name" name="name" onchange="validateFormElem(this, namePattern, document.getElementById('errorName'), nameErrorMessage)" />
				<small id="errorName">Error message</small>
			</div>
			<div class="form-control">
				<label for="surname">Cognome</label>
				<input type="text" placeholder="" id="surname" name="surname" onchange="validateFormElem(this, namePattern, document.getElementById('errorSurname'), nameErrorMessage)" />
				<small id="errorSurname">Error message</small>
			</div>
			
			<button type="submit" form="form">Conferma</button>
		</form>
	</div>
	
<script>
const namePattern = /^[A-Za-z]+[\s*[A-Za-z]+\s*[A-Za-z]*]*$/;

const nameErrorMessage = "Può contenere solo lettere";

let smallName = document.getElementById("errorName");
let smallSurname = document.getElementById("errorSurname");

form.addEventListener('submit', e => {
    e.preventDefault();

    var x = validate();
		
    if(x==2){
        form.submit();
    }
});


function validate(){
	let form = document.getElementById("form");
	let smallName = document.getElementById("errorName");
	let smallSurname = document.getElementById("errorSurname");

	var total = 0;
	
	if(validateFormElem(form.name,namePattern,smallName,nameErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.surname,namePattern,smallSurname,nameErrorMessage)){
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
</script>
</body>
</html>