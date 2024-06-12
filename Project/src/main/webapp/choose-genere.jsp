<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>Prodotti</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/footer.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<div class="products">
		<div class="container">
			<h1 class="lg-title">Seleziona un genere</h1>
			
			<div class="product-items">
				<c:forEach items="${generi}" var="genere">
					<div class="product">
						<div class="product-img">
							<a href="http://localhost:8080/PeekABook/ShowGenere?genere=${genere.nome}"><h1 style="font-weight: 500">${genere.nome }</h1></a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="./footer.html"%>
</body>
</html>