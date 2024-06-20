<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>Autori</title>
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
		<h1>Autori</h1>
		<div class="card">
			<div class="cart-total" style="height:100px!important;">
				<a href="http://localhost:8080/PeekABook/AddAutore">Aggiungi</a>
			</div>
			<div class="products">
				<c:forEach items="${autori}" var="autore">
					<div class="product">
						<div class="product-info">
						<h4 class="product-price">Codice: ${autore.codice}</h4>
						<h4 class="product-price">Nome: ${autore.nome}</h4>
						<h4 class="product-price">Cognome: ${autore.cognome}</h4>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="../footer.html"%>
</body>
</html>