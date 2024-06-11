<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Error</title>
</head>

<body>
	<jsp:include page="./header.jsp"></jsp:include>
	
	<h1 class="lg-title">Qualcosa è andato storto...</h1>
	<h1 class="md-title">probabilmente l'username da te scelto è già occupato.</h1>
	<p style="text-align: center; line-height: 60px;">Torna alla <a href="http://localhost:8080/PeekABook/registration.html" style="text-align: center; color: #1e7682;">REGISTRAZIONE</a></p>
	<p style="text-align: center; line-height: 60px;">Vai alla <a href="http://localhost:8080/PeekABook/Home" style="text-align: center; color: #1e7682;">HOME</a></p>

	<%@include file="./footer.html" %>
</body>
</html>