<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>Info</title>
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
Boolean isCommon = (Boolean) session.getAttribute("isCommon");
if(isCommon==null||!isCommon){
	response.sendRedirect("http://localhost:8080/PeekABook/login.html");
	return;
}
%>

<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<div class="container">
		<h1>Info</h1>
		<div class="card">
			<div class="products">	
				<div class="product">
					<div class="product-info">
						<h3 class="product-name" style="margin-bottom: 15px">Username: ${utente.username}</h3>
						<h4 class="product-price">Nome: ${utente.nome}</h4>
						<h4 class="product-price">Cognome: ${utente.cognome}</h4>
						<h4 class="product-price">Email: ${utente.email}</h4>
						<h4 class="product-price">Via: ${utente.via}, ${utente.civico}</h4>
						<h4 class="product-price">Città: ${utente.citta}</h4>
						<h4 class="product-price">CAP: ${utente.cap}</h4>
						
		
						<p class="product-remove">
							<i class="fa-solid fa-wrench" aria-hidden="true"></i>
							<a href="http://localhost:8080/PeekABook/AggiornaUtente" style="text-decoration: none; color: white;"><span class="remove">Aggiorna</span></a>
						</p>		
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./footer.html"%>
</body>
</html>