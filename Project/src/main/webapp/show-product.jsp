<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>${articolo.nome}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/footer.css">
    <link rel="stylesheet" href="./css/items.css">
    <link rel="stylesheet" href="./css/show-product.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	
	
	<div class="card-wrapper">
		<div class="card">
			<div class="product-imgs">
				<div class="img-showcase">
						<img alt="image" src="./images/${articolo.pathImg}">
				</div>
			</div>		
			<div class="product-content">
				<h2 class="product-title">${articolo.nome}</h2>
				<div class="product-rating">
					<c:forEach var="i" begin="1" end="${articolo.valutazione}">
						<i class="fas fa-star"></i>
					</c:forEach>
				</div>
				<div class="product-price">
					<p class="price">Prezzo: <span>€${articolo.prezzo}</span></p>
				</div>
				<div class="product-detail">
					<h2>Descrizione: </h2>
					<p>${articolo.descrizione}</p>
					<ul>
						<li> Autore: <span>${articolo.autore.nome} ${articolo.autore.cognome}</span></li>
						<li> ISBN: <span>${articolo.isbn}</span></li>
						<li> Anno: <span>${articolo.anno}</span></li>
						<li> Pagine: <span>${articolo.pagine}</span></li>
						<li> Editore: <span>${articolo.editore}</span></li>
					</ul>	
				</div>
				<div class="purchase-info">
					<c:choose>
						<c:when test="${empty utente}">
							<input type="number" min="1" value="1">
							<a style="text-decoration:none" href="http://localhost:8080/PeekABook/login.html"><button type="button" class="btn">Carrello <i class="fas fa-shopping-cart"></i></button></a>
						</c:when>
						<c:otherwise>
							<form action="AddToCart" style="display:inline" id="${articolo.codice}form">
								<input type="hidden" name="articolo" value="${articolo.codice}">
								<input type="number" name="quantita" min="1" value="1">
								<button type="submit" class="btn" form="${articolo.codice}form">
								Carrello <i class="fas fa-shopping-cart"></i>
								</button>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	
	<div class="products">
		<div class="container">
			<h1 class="lg-title">Ti potrebbe interessare</h1>
			
			<div class="product-items">
				<c:forEach items="${consigliati}" var="consigliato">
					<div class="product">
						<div class="product-img">
							<img alt="${consigliato.nome }" src="./images/${consigliato.pathImg}" name="${consigliato.nome }" onclick="document.location='http://localhost:8080/PeekABook/ShowProduct?nome='+this.alt;">
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