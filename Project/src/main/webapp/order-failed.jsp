<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<title>Ordine completato</title>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/display.css">
    <link rel="stylesheet" type="text/css" href="./css/footer1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <style>
    	.product{
    		height:auto!important;
    	}
    	
    	.products{
    		margin-left: 50px;
    	}
    </style>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	
	<h3 style="margin-top: 50px;  margin-left: 50px; margin-bottom: 30px">Il seguente articolo non è disponibile:</h3>
	
	<div class="card">
		<div class="products">
			<div class="product">
				<img alt="immagine prodotto" src="./images/${articolo.pathImg}">
				<div class=product-info>
					<h4 class="product-name">Nome: ${articolo.nome}</h4>
					<h4 class="product-price">Massima quantità disponibile: ${articolo.quantita}</h4>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="./footer.html" %>
</body>
</html>