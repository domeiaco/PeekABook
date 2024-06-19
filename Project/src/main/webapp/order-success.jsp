<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<title>Ordine completato</title>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/display.css">
    <link rel="stylesheet" type="text/css" href="./css/footer1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	
	<h3 style="margin-top: 50px; margin-left: 20px;">Grazie per aver effettuato l'ordine. La spedizione sarà effettuata entro 2 giorni.</h3>
	
	<%@include file="./footer.html" %>
</body>
</html>