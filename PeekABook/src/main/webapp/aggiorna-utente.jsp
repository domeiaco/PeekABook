<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Aggiorna</title>
	<link rel="stylesheet" href="./css/registration.css">
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<script src="./script/update-validation.js" defer></script>
</head>
<body>
	<div class="container">
		<div class="header">
			<h2>Aggiorna info</h2>
		</div>
		<form  class="form" id="form" method="post" action="UpdateUser" >
			<div class="form-control">
				<label for="email">Email</label>
				<input type="email" value="${utente.email}" placeholder="" id="email" name="email" onchange="validateFormElem(this, emailPattern, document.getElementById('errorEmail'), emailErrorMessage)"/>
				<small id="errorEmail" style="font-size:0.8rem">Error message</small>
			</div>
			<div class="form-control">
				<label for="name">Nome</label>
				<input type="text" value="${utente.nome}" placeholder="" id="name" name="name" onchange="validateFormElem(this, namePattern, document.getElementById('errorName'), nameErrorMessage)" />
				<small id="errorName">Error message</small>
			</div>
			<div class="form-control">
				<label for="surname">Cognome</label>
				<input type="text" value="${utente.cognome}" placeholder="" id="surname" name="surname" onchange="validateFormElem(this, namePattern, document.getElementById('errorSurname'), nameErrorMessage)" />
				<small id="errorSurname">Error message</small>
			</div>
			<div class="form-control">
				<label for="address">Via</label>
				<input type="text" value="${utente.via}" placeholder="" id="address" name="address" onchange="validateFormElem(this, namePattern, document.getElementById('errorVia'), nameErrorMessage)" />
				<small id="errorVia"></small>
			</div>
			<div class="form-control">
				<label for="address2">Civico</label>
				<input type="number" value="${utente.civico}" placeholder="" id="address2" name="address2" onchange="validateFormElem(this, civicoPattern, document.getElementById('errorCivico'), civicoErrorMessage)" />
				<small id="errorCivico">Error message</small>
			</div>
			<div class="form-control">
				<label for="address3">Città</label>
				<input type="text" value="${utente.citta}" placeholder="" id="address3" name="address3" onchange="validateFormElem(this, namePattern, document.getElementById('errorCitta'), nameErrorMessage)" />
				<small id="errorCitta">Error message</small>
			</div>
			<div class="form-control">
				<label for="address">CAP</label>
				<input type="number" value="${utente.cap}" placeholder="" id="cap" name="cap" max="99999" onchange="validateFormElem(this, capPattern, document.getElementById('errorCap'), capErrorMessage)" />
				<small id="errorCap">Error message</small>
			</div>
			<button type="submit" form="form">Conferma</button>
		</form>
	</div>
</body>
</html>