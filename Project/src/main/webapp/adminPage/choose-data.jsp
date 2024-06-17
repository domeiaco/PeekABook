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
			<h2>Seleziona il periodo degli ordini da visualizzare</h2>
		</div>
		<form  class="form" id="form" method="post" action="ShowOrdiniData" >
        	<div class="form-control">
            	<label for="startDate">Data di inizio</label><br>
            	<input type="date" name="startDate" id="startDate" required style="border-radius: 10px;" > 
        	</div>
        	<div class="form-control">
            	<label for="endDate">Data di fine</label><br>
            	<input type="date" name="endDate" id="endDate" required style="border-radius: 10px" > 
        	</div>
			<button type="submit" form="form">Conferma</button>
		</form>
	</div>

<script>
document.getElementById("startDate").max = new Date().toISOString().split("T")[0];

document.getElementById("endDate").max = new Date().toISOString().split("T")[0];
</script>

</body>
</html>