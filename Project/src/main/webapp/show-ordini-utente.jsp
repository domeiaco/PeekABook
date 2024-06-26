<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>Ordini</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/display.css">
    <c:choose>
        <c:when test="${numeroOrdini<2}">
            <link rel="stylesheet" href="./css/footer1.css">
        </c:when>
        <c:otherwise>
            <link rel="stylesheet" href="./css/footer.css">
        </c:otherwise>
    </c:choose>
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
		<h1>Ordini</h1>
		<div class="card">
			<div class="products">
				<c:choose>
					<c:when test="${numeroOrdini==0 }">
						<h4>Non ci sono ordini</h4>
					</c:when>
					<c:otherwise>
						<h1>Numero ordini: ${numeroOrdini}</h1>
						<c:forEach items="${ordini}" var="ordine">
							<div class="product">
								<div class="product-info">
									<h4 class="product-price">Numero ordine: ${ordine.numero}</h4>
									<h4 class="product-price">Indirizzo: ${ordine.via}, ${ordine.civico}</h4>
									<h4 class="product-price">Città: ${ordine.citta}</h4>
									<h4 class="product-price">CAP: ${ordine.cap}</h4>
									<h4 class="product-price">Data ordine: ${ordine.dataOrdine}</h4>
									<h4 class="product-price">Data consegna: ${ordine.dataConsegna}</h4>
									<h4 class="product-price">Stato: ${ordine.stato}</h4>
									<h4 class="product-price">Metodo di pagamento: ${ordine.metodoPagamento}</h4>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			
		</div>
	</div>
	<%@include file="./footer.html"%>
</body>
</html>