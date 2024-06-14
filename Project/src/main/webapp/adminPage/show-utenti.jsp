<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="utf-8">
<title>Utenti</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://kit.fontawesome.com/7c496c47e4.js"
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/display.css">
<c:choose>
	<c:when test="${numeroUtenti<2}">
		<link rel="stylesheet" href="./css/footer1.css">
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" href="./css/footer.css">
	</c:otherwise>
</c:choose>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
.product-remove:hover a {
	color: #0e4950 !important;
}

.product {
	height: auto !important;
}
</style>
</head>

<%
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if (isAdmin == null || !isAdmin) {
	response.sendRedirect("http://localhost:8080/PeekABook/login.html");
	return;
}
%>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h1>Utenti</h1>
		<div class="card">
			<div class="products">
				<c:choose>
					<c:when test="${numeroUtenti==0 }">
						<h4>Non ci sono utenti registrati</h4>
					</c:when>
					<c:otherwise>
						<c:forEach items="${utenti}" var="utente">
							<div class="product">
								<div class="product-info">
									<h4 class="product-price">ID: ${utente.id}</h4>
									<h4 class="product-price">Nome: ${utente.nome}</h4>
									<h4 class="product-price">Cognome: ${utente.cognome}</h4>
									<h4 class="product-price">Username: ${utente.username}</h4>
									<h4 class="product-price">Email: ${utente.email}</h4>
									<c:choose>
										<c:when test="${utente.admin==1}">
											<h4 class="product-price">ADMIN</h4>
											<p class="product-remove">
												<i class="fas fa-minus" aria-hidden="true"></i> <a
													href="http://localhost:8080/PeekABook/RemoveAdmin?id=${utente.id}"
													style="text-decoration: none; color: white;"><span
													class="remove">Rimuovi Admin</span></a>
											</p>
										</c:when>
										<c:otherwise>
											<p class="product-remove">
												<i class="fas fa-plus" aria-hidden="true"></i> <a
													href="http://localhost:8080/PeekABook/SetAdmin?id=${utente.id}"
													style="text-decoration: none; color: white;"><span
													class="remove">Set Admin</span></a>
											</p>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<%@include file="../footer.html"%>
</body>
</html>