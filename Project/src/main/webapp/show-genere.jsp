<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Prodotti</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/footer.css">
    <link rel="stylesheet" href="./css/items.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<div class="products">
		<div class="container">
			<h1 class="lg-title">La nostra selezione di libri del genere: ${genere.nome}</h1>
			
			<div class="product-items">
				<c:forEach items="${articoli}" var="consigliato">
					<div class="product">
						<div class="product-img">
							<img alt="${consigliato.nome }" src="./images/${consigliato.pathImg}" onclick="document.location='http://localhost:8080/PeekABook/ShowProduct?nome='+this.alt;">
						</div>
						<div class=product-info>
							<div class=product-info-top>
								<h2 class="sm-title">Rating:</h2>
								<div class="rating">
									<c:forEach var="i" begin="1" end="${consigliato.valutazione}">
										<i class="fa-solid fa-star"></i>
									</c:forEach>
								</div>
							</div>
							<a href="http://localhost:8080/PeekABook/ShowProduct?nome=${consigliato.nome}" class="product-name">${consigliato.nome}</a>
							<p class = "product-price">€${consigliato.prezzo}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="./footer.html"%>
</body>
</html>