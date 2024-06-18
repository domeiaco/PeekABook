<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>Carrello</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<c:choose>
        <c:when test="${carrello.dimensione<2}">
            <link rel="stylesheet" href="./css/footer1.css">
        </c:when>
        <c:otherwise>
            <link rel="stylesheet" href="./css/footer.css">
        </c:otherwise>
    </c:choose>
	<link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/display.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style>
        .product-remove:hover a{
            color: #0e4950!important;
        }
    </style>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<div class="container">
		<h1>Carrello</h1>
		<div class="card">
			<div class="products">
				<c:choose>
					<c:when test="${carrello.numeroArticoli==0 }">
						<h4>Non ci sono articoli nel carrello</h4>
					</c:when>
					<c:otherwise>
						<c:forEach items="${carrello.innerArticoli}" var="prodotto">
							<div class="product">
								<img alt="immagine prodotto" src="./images/${prodotto.pathImg}">
								<div class="product-info">
									<h3 class="product-name">${prodotto.nome}</h3>
									<h4 class="product-price">Prezzo: €${prodotto.prezzo * carrello.articoli[prodotto] }</h4>
									<p class="product-quantity">Quantità: <input type="number" min="1" max="5" id=${prodotto.codice} name="quantita" value="${carrello.articoli[prodotto]}" onchange="updateQuantity(this.id,this.value)">
									<p class="product-remove">
										<i class="fa fa-trash" aria-hidden="true"></i>		
										<a href="http://localhost:8080/PeekABook/RemoveProductCarrello?id=${prodotto.codice}" style="text-decoration: none; color:white;"><span class="remove">Rimuovi</span></a>							
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="cart-total">
						<p>
							<span>Totale</span>
							<span> € ${carrello.totale}</span>
						</p>
						<p>
							<span>Numero Articoli</span>
							<span>${carrello.numeroArticoli}</span>
						</p>
						<a href="http://localhost:8080/PeekABook/Checkout">Checkout</a>
					</div>
					
					</c:otherwise>
				</c:choose>
			</div>
			
		</div>
	<%@include file="./footer.html"%>
	
	<script>
		function updateQuantity(id,val){
			document.location.href="http://localhost:8080/PeekABook/UpdateCarrello?id="+id+"&quantita="+val,true;
		}
	</script>
</body>
</html>