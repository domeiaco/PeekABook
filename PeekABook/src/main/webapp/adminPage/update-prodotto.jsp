<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Modifica</title>
	<link rel="stylesheet" href="./css/registration.css">
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
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
			<h2>Modifica ${articolo.nome}</h2>
		</div>
		<form  class="form" id="form" method="post" action="Aggiorna" accept-charset="utf-8" >
			
			<div class="form-control">
				<label for="price">Prezzo</label>
				<input type="number" step=".01" placeholder="" id="price" name="price" value="${articolo.prezzo}" onchange="validateFormElem(this, pricePattern, document.getElementById('errorPrice'), priceErrorMessage)" />
				<small id="errorPrice">Error message</small>
			</div>
			<div class="form-control">
            	<label for="quantity">Quantità da aggiungere</label>
          	    <input type="number" placeholder="" id="quantity" name="quantity" value="0" onchange="validateFormElem(this, pagesPattern, document.getElementById('errorQuantity'), quantityErrorMessage)"/>
           	 	<small id="errorQuantity" style="font-size:0.8rem">Error message</small>
       		</div>
       		<div class="form-control">
            	<label for="year">Anno</label>
            	<input type="number" placeholder="" id="year" name="year" value="${articolo.anno}" onchange="validateFormElem(this, yearPattern, document.getElementById('errorYear'), yearErrorMessage)"/>
            	<small id="errorYear">Error message</small>
      	    </div>
  
        	<div class="form-control">
	            <label for="pages">Pagine</label>
            	<input type="number" placeholder="" id="pages" min="1" name="pages" value="${articolo.pagine }" onchange="validateFormElem(this, pagesPattern, document.getElementById('errorPages'), quantityErrorMessage)"/>
            	<small id="errorPages">Error message</small>
        	</div>
			<div class="form-control">
	            <label for="editor">Editore</label>
            	<input type="text" min="1" placeholder="" id="editor" name="editor" value="${articolo.editore }" onchange="validateFormElem(this, namePattern, document.getElementById('errorEditor'), nameErrorMessage)"/>
            	<small id="errorEditor">Error message</small>
        	</div>
        	<div class="form-control">
            	<label for="description">Descrizione</label>
            	<input type="text" placeholder="" id="description" name="description" value="${articolo.descrizione }" onchange="validateFormElem(this, descriptionPattern, document.getElementById('errorDescription'), descriptionErrorMessage)"/>
            	<small id="errorDescription">Error message</small>
        	</div>
        	<div class="form-control">
            	<label for="rating">Rating (1-5)</label>
        	    <input type="number" placeholder="" id="rating" name="rating" value="${articolo.valutazione }" onchange="validateFormElem(this, ratingPattern, document.getElementById('errorRating'), ratingErrorMessage)"/>
            	<small id="errorRating">Error message</small>
        	</div>
        	
        	<div class="form-control" style="display: none;">
            	<input type="hidden" placeholder="" value="${articolo.codice}" name="codice"/>
        	</div>
        	
			<button type="submit" form="form">Conferma</button>
		</form>
	</div>
	
<script>
const namePattern = /^[A-Za-z]+[\s*[A-Za-z]+\s*[A-Za-z]*]*$/;
const ratingPattern = /^([1-5]{1})$/;
const pagesPattern = /^([0-9]+)$/;
const yearPattern = /^([0-9]{4})$/;
const pricePattern = /^([0-9]+\.[0-9]{2})$/;
const descriptionPattern = /^(?!\s*$).+/;

const nameErrorMessage = "Può contenere solo lettere";
const quantityErrorMessage = "Può contenere solo numeri (Valore minimo: 1)";
const yearErrorMessage = "Deve essere nel formato ####";
const descriptionErrorMessage = "Non può essere vuoto";
const ratingErrorMessage = "Inserire un valore da 1 a 5";
const priceErrorMessage = "Deve essere nel formato #.##"


form.addEventListener('submit', e => {
    e.preventDefault();

    var x = validate();
	
	console.log(form.action);
	
    if(x==7){
        form.submit();
    }
});


function validate(){
	let valid= true;
	let form = document.getElementById("form");
	let smallPrice = document.getElementById("errorPrice");
	let smallQuantity = document.getElementById("errorQuantity");
	let smallYear = document.getElementById("errorYear");
	let smallPages = document.getElementById("errorPages");
	let smallEditor = document.getElementById("errorEditor");
	let smallRating = document.getElementById("errorRating");
	let smallDescription = document.getElementById("errorDescription");
	
	
	var total = 0;
	
	
	if(validateFormElem(form.price,pricePattern,smallPrice,priceErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.quantity,pagesPattern,smallQuantity,quantityErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.year,yearPattern,smallYear,yearErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.pages,pagesPattern,smallPages,quantityErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.editor,namePattern,smallEditor,nameErrorMessage)){
		total++;
	}
	


	if(validateFormElem(form.rating,ratingPattern,smallRating,ratingErrorMessage)){
		total++;
	}
	
	if(validateFormElem(form.description,descriptionPattern,smallDescription,descriptionErrorMessage)){
		total++;
	}
	
	console.log("total: "+total);
	
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