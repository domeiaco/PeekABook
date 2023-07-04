<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Aggiungi</title>
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
			<h2>Aggiungi libro</h2>
		</div>
		<form  class="form" id="form" method="post" action="InsertProdotto" >
			<div class="form-control">
				<label for="name">Nome</label>
				<input type="text" placeholder="" id="name" name="name" onchange="validateFormElem(this, namePattern, document.getElementById('errorName'), nameErrorMessage)" />
				<small id="errorName">Error message</small>
			</div>
			<div class="form-control">
				<label for="price">Prezzo</label>
				<input type="number" step=".01" placeholder="" id="price" name="price" onchange="validateFormElem(this, pricePattern, document.getElementById('errorPrice'), priceErrorMessage)" />
				<small id="errorPrice">Error message</small>
			</div>
			<div class="form-control">
            	<label for="quantity">Quantità</label>
          	    <input type="number" placeholder="" id="quantity" name="quantity" min="1" onchange="validateFormElem(this, pagesPattern, document.getElementById('errorQuantity'), quantityErrorMessage)"/>
           	 	<small id="errorQuantity" style="font-size:0.8rem">Error message</small>
       		</div>
       		<div class="form-control">
            	<label for="year">Anno</label>
            	<input type="number" placeholder="" id="year" name="year" onchange="validateFormElem(this, yearPattern, document.getElementById('errorYear'), yearErrorMessage)"/>
            	<small id="errorYear">Error message</small>
      	    </div>
      	    <div class="form-control">
            	<label for="isbn">ISBN</label>
        	    <input type="number" placeholder="" id="isbn" name="isbn" onchange="validateFormElem(this, isbnPattern, document.getElementById('errorIsbn'), isbnErrorMessage)"/>
            	<small id="errorIsbn">Error message</small>
        	</div>
        	<div class="form-control">
	            <label for="pages">Pagine</label>
            	<input type="number" placeholder="" id="pages" min="1" name="pages" onchange="validateFormElem(this, pagesPattern, document.getElementById('errorPages'), quantityErrorMessage)"/>
            	<small id="errorPages">Error message</small>
        	</div>
			<div class="form-control">
	            <label for="editor">Editore</label>
            	<input type="text" min="1" placeholder="" id="editor" name="editor" onchange="validateFormElem(this, namePattern, document.getElementById('errorEditor'), nameErrorMessage)"/>
            	<small id="errorEditor">Error message</small>
        	</div>
        	<div class="form-control">
            	<label for="description">Descrizione</label>
            	<input type="text" placeholder="" id="description" name="description" onchange="validateFormElem(this, descriptionPattern, document.getElementById('errorDescription'), descriptionErrorMessage)"/>
            	<small id="errorDescription">Error message</small>
        	</div>
        	<div class="form-control">
            	<label for="rating">Rating (1-5)</label>
        	    <input type="number" placeholder="" id="rating" name="rating" onchange="validateFormElem(this, ratingPattern, document.getElementById('errorRating'), ratingErrorMessage)"/>
            	<small id="errorRating">Error message</small>
        	</div>
        	<div class="form-control">
            	<label for="autore">Autore</label><br>
            	<select name="autore" id="autore" style="width:150px;">
                	<c:forEach items="${autori}" var="autore">
                    	<option value="${autore.codice}">${autore.nome} ${autore.cognome}</option>
                	</c:forEach>
            	</select>        
	            <small id="errorAutore">Error message</small>
        	</div>
        	<div class="form-control">
            	<label for="genere">Genere</label><br>
            	<select name="genere" id="genere" style="width:150px;">
                	<c:forEach items="${generi}" var="genere">
                    	<option value="${genere.nome}">${genere.nome}</option>
                	</c:forEach>
            	</select>
            	<small id="errorGenere">Error message</small>
        	</div>
			<button type="submit" form="form">Registrati</button>
		</form>
	</div>
	
<script>
const namePattern = /^[A-Za-z]+[\s*[A-Za-z]+\s*[A-Za-z]*]*$/;
const ratingPattern = /^([1-5]{1})$/;
const pagesPattern = /^([0-9]+)$/;
const yearPattern = /^([0-9]{4})$/;
const pricePattern = /^([0-9]+\.[0-9]{2})$/;
const isbnPattern= /^([0-9]{10})$/;
const descriptionPattern = /^(?!\s*$).+/;

const nameErrorMessage = "Può contenere solo lettere";
const quantityErrorMessage = "Può contenere solo numeri (Valore minimo: 1)";
const yearErrorMessage = "Deve essere nel formato ####";
const isbnErrorMessage = "Deve essere nel formato ##########";
const descriptionErrorMessage = "Non può essere vuoto";
const ratingErrorMessage = "Inserire un valore da 1 a 5";
const priceErrorMessage = "Deve essere nel formato #.##"


form.addEventListener('submit', e => {
    e.preventDefault();

    var x = validate();
	
	console.log(form.action);
	
    if(x==11){
        form.submit();
    }
});


function validate(){
	let valid= true;
	let form = document.getElementById("form");
	let smallName = document.getElementById("errorName");
	let smallPrice = document.getElementById("errorPrice");
	let smallQuantity = document.getElementById("errorQuantity");
	let smallYear = document.getElementById("errorYear");
	let smallPages = document.getElementById("errorPages");
	let smallEditor = document.getElementById("errorEditor");
	let smallIsbn = document.getElementById("errorIsbn");
	let smallAutore = document.getElementById("errorAutore");
	let smallGenere = document.getElementById("errorGenere");
	let smallRating = document.getElementById("errorRating");
	let smallDescription = document.getElementById("errorDescription");
	
	
	var total = 0;
	
	if(validateFormElem(form.name,namePattern,smallName,nameErrorMessage)){
		total++;
	}
	
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
	
	if(validateFormElem(form.isbn,isbnPattern,smallIsbn,isbnErrorMessage)){
		total++;
	}
	
	if(!(autore.value.trim()==="")){
		total++;
	}
	
	if(!(genere.value.trim()==="")){
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