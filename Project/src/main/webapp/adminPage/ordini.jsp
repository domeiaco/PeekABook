<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="utf-8">
	<title>Admin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/footer.css">
    <link rel="stylesheet" href="./css/admin.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	<div class="products">
		<div class="container">
			<h1 class="lg-title">Aggiungi elementi al database</h1>
			
			<div class="product-items" style="display: flex;justify-content:center;flex-wrap: wrap;">
				<div class="product">
					<div class="product-img">
						<a href="http://localhost:8080/PeekABook/ShowOrdini"><h1 style="font-weight: 500; text-align:center ">Tutti gli ordini</h1></a>
					</div>
				</div>
				<div class="product">
					<div class="product-img">
						<a href="http://localhost:8080/PeekABook/ChooseUtente"><h1 style="font-weight: 500; text-align:center">Ordini per utente</h1></a>
					</div>
				</div>
				<div class="product">
					<div class="product-img">
						<a href="http://localhost:8080/PeekABook/ChooseData"><h1 style="font-weight: 500; text-align:center">Ordini per data</h1></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../footer.html"%>
</body>
</html>