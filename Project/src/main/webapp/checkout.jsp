<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/registration.css">
<link rel="shortcut icon" href="./icons/favico.png" type=image/x-icon>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>

<%
Boolean isCommon = (Boolean) session.getAttribute("isCommon");
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if(isCommon==null&&isAdmin==null){
	response.sendRedirect("http://localhost:8080/PeekABook/login.html");
	return;
}
%>

<body>



<div class="container">
		
		<form  class="form" id="form" method="post" action="ConfermaOrdine" >
		<div class="header">
			<h2 style="font-size: 1.4em; ">Dati di spedizione</h2>
		</div>
			<div class="form-control">
				<label for="address">Via</label>
				<input type="text" placeholder="" id="address" name="address" value="${utente.via}"/>
				<small id="errorVia"></small>
			</div>
			<div class="form-control">
				<label for="address2">Civico</label>
				<input type="number" placeholder="" id="address2" name="address2" value="${utente.civico}"/>
				<small id="errorCivico">Error message</small>
			</div>
			<div class="form-control">
				<label for="address3">Città</label>
				<input type="text" placeholder="" id="address3" name="address3" value="${utente.citta}"/>
				<small id="errorCitta">Error message</small>
			</div>
			<div class="form-control">
				<label for="cap">CAP</label>
				<input type="number" placeholder="" id="cap" name="cap" min="00001" max="99999" value="${utente.cap}"/>
				<small id="errorCap">Error message</small>
			</div>
			<div class="header">
			<h2 style="font-size: 1.2em; ">Metodo di pagamento</h2>
			</div>
			<div class="form-control">
            	<label for="circuito">Circuito</label><br>
            	<select name="circuito" id="circuito" style="width:250px;height: 30px;border-radius: 10px;font-family: 'Montserrat';">
                    	<option value="Mastercard">Mastercard</option>
                    	<option value="Visa">Visa</option>
                    	<option value="American Express">American Express</option>
                    	<option value="Maestro">Maestro</option>
                    	<option value="PagoBancomat">PagoBancomat</option>
            	</select>        
	            <small id="errorCircuito">Error message</small>
        	</div>
        	<div class="form-control">
				<label for="numero">Numero Carta</label>
				<input type="text" placeholder="XXXX XXXX XXXX XXXX" id="numero" name="numero"/>
				<small id="errorNumero">Error message</small>
			</div>
			<div class="form-control">
				<label for="scadenza">Scadenza</label>
				<input type="text" placeholder="MM/AA" id="scadenza" name="scadenza"/>
				<small id="errorScadenza">Error message</small>
			</div>
			<div class="form-control">
				<label for="cvv">CVV</label>
				<input type="password" placeholder="XXX" id="cvv" name="cvv"/>
				<small id="errorCvv">Error message</small>
			</div>
			<button type="submit" form="form">Conferma Ordine</button>
		</form>
	</div>

</body>
</html>