<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Seleziona</title>
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
			<h2>Seleziona l'utente di cui vuoi visualizzare gli ordini</h2>
		</div>
		<form  class="form" id="form" method="post" action="ShowOrdiniUtente" >
        	<div class="form-control">
            	<label for="utente">Utente</label><br>
            	<select name="utente" id="utente" style="width:250px;height: 30px;border-radius: 10px;font-family: 'Montserrat';">
                	<c:forEach items="${utenti}" var="utente">
                    	<option value="${utente.id}">${utente.id} ${utente.nome} ${utente.cognome}</option>
                	</c:forEach>
            	</select>        
	            <small id="errorAutore">Error message</small>
        	</div>
			<button type="submit" form="form">Conferma</button>
		</form>
	</div>

</body>
</html>