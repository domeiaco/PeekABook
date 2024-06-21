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
    <link rel="stylesheet" href="./css/display.css"> 
    <link rel="stylesheet" href="./css/footer.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style>
        .product-remove:hover a{
            color: #0e4950!important;
        }

        .product{
            height: auto!important;
        }
    </style>
</head>

<%
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if(isAdmin==null||!isAdmin){
	response.sendRedirect("http://localhost:8080/PeekABook/login.html");
	return;
}
%>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h1>Prodotti</h1>
		<div class="card">
			<div class="products">
				<c:forEach items="${articoli}" var="articolo">
					<div class="product">
						<img alt="Immagine prodotto" src="./images/${articolo.pathImg}">
						<div class="product-info">
						<h3 class="product-name">Nome: ${articolo.nome}</h3>
						<h4 class="product-price" style="margin-top: 20px">Codice: ${articolo.codice}</h4>
						<h4 class="product-price">Prezzo: ${articolo.prezzo}</h4>
						<h4 class="product-price">Valutazione: ${articolo.valutazione}</h4>
						<h4 class="product-price">Quantità: ${articolo.quantita}</h4>
						<p class="product-remove">
                            <i class="fa-solid fa-wrench" aria-hidden="true"></i>
                            <a href="http://localhost:8080/PeekABook/UpdateProdotto?codice=${articolo.codice}" style="text-decoration: none; color: white;"><span class="remove">Modifica</span></a>
                        </p>
                        <p class="product-remove">
                            <i class="fa-solid fa-trash" aria-hidden="true" ></i>
                            <a href="http://localhost:8080/PeekABook/RemoveProdotto?codice=${articolo.codice}" style="text-decoration: none; color: white;"><span class="remove">Elimina</span></a>
                        </p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="../footer.html"%>
</body>
</html>