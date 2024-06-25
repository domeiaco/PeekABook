<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Conferma Ordine</title>
<link rel="stylesheet" href="css/registration.css">
<script src="https://kit.fontawesome.com/7c496c47e4.js"
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="./icons/favico.jpg" type=image/x-icon>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./script/payment-validation.js" defer></script>


</head>

<%
Boolean isCommon = (Boolean) session.getAttribute("isCommon");
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if (isCommon == null && isAdmin == null) {
	response.sendRedirect("http://localhost:8080/PeekABook/login.html");
	return;
}
%>

<body>



	<div class="container">

		<form class="form" id="form" method="post" action="ConfermaOrdine">
			<div class="header">
				<h2 style="font-size: 1.2em;">Dati di spedizione</h2>
			</div>
			<div class="form-control">
				<label for="address">Via</label> <input type="text" placeholder=""
					id="address" name="address" value="${utente.via}"
					onchange="validateFormElem(this, namePattern, document.getElementById('errorVia'), nameErrorMessage)" />
				<small id="errorVia"></small>
			</div>
			<div class="form-control">
				<label for="address2">Civico</label> <input type="number"
					placeholder="" id="address2" name="address2"
					value="${utente.civico}"
					onchange="validateFormElem(this, civicoPattern, document.getElementById('errorCivico'), civicoErrorMessage)" />
				<small id="errorCivico">Error message</small>
			</div>
			<div class="form-control">
				<label for="address3">Città</label> <input type="text"
					placeholder="" id="address3" name="address3"
					value="${utente.citta}"
					onchange="validateFormElem(this, namePattern, document.getElementById('errorCitta'), nameErrorMessage)" />
				<small id="errorCitta">Error message</small>
			</div>
			<div class="form-control">
				<label for="cap">CAP</label> <input type="number" placeholder=""
					id="cap" name="cap" min="00001" max="99999" value="${utente.cap}"
					onchange="validateFormElem(this, capPattern, document.getElementById('errorCap'), capErrorMessage)" />
				<small id="errorCap">Error message</small>
			</div>
			<div class="header">
				<h2 style="font-size: 1.2em;">Metodo di pagamento</h2>
			</div>
			<div class="form-control">
				<label for="circuito">Circuito</label><br>
				<div style="display: flex;align-items: center;"> 
					<select name="circuito" id="circuito" style="width: 250px; height: 30px; border-radius: 10px; font-family: 'Montserrat';" onchange="changeCircuito(this.value)">
						<option value="Mastercard">Mastercard</option>
						<option value="Visa">Visa</option>
						<option value="American Express">American Express</option>
					</select> 
					<img id="circuitImg" alt="card-logo" src="./icons/mastercard.png" style="width: 60px; height: auto; margin-left: 10px">
				</div>
			</div>
			<div class="form-control">
				<label for="numero">Numero Carta</label> 
				<input type="text" placeholder="16 cifre senza spazi" id="numero" name="numero" />
				<small id="errorNumero" style="font-size: 0.7em; top: 65px">Error message</small>
			</div>
			<div class="form-control">
				<label for="scadenza">Scadenza</label> 
				<input type="text" placeholder="MM/AA" id="scadenza" name="scadenza" /> 
				<small id="errorScadenza">Error message</small>
			</div>
			<div class="form-control">
				<label for="cvv">CVV</label> 
				<input type="password" placeholder="XXX" id="cvv" name="cvv" /> 
				<small id="errorCvv">Error message</small>
			</div>
			<button type="submit" form="form">Conferma Ordine</button>
		</form>
	</div>

</body>
</html>