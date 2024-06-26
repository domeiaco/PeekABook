<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/search.css">
<link rel="shortcut icon" href="./icons/favico.png" type=image/x-icon>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
<script src="./script/searchbar.js" defer></script>
</head>
<body>
<nav>
	<div class="menu-icon">
		<span class="fas fa-bars"></span>
	</div>
	<div class="logo">
		<a href="http://localhost:8080/PeekABook/Home"><img alt="logo" src="./icons/logo.png"></a>
	</div>
	<div class="nav-items">
		<ul>
			<li><a href="http://localhost:8080/PeekABook/Home">Home</a></li>
			<li><a href="http://localhost:8080/PeekABook/ShowAll">Libri</a></li>
			<li><a href="http://localhost:8080/PeekABook/ChooseGenere">Generi</a></li>
		</ul>
		<c:choose>
			<c:when test="${not empty utente}">
				<c:choose>
					<c:when test="${utente.admin==1}">
					<ul>
						<li><a class="icons" href="http://localhost:8080/PeekABook/AdminPage" id="user"><i style="margin-right: 0px!important;" class="fa-solid fa-user-gear fa-lg"></i></a></li>
					</ul>
					</c:when>
					<c:otherwise>
					<ul>
						<li><a class="icons" href="http://localhost:8080/PeekABook/UserPage" id="user"><i style="margin-right: 0px!important;" class="fa-solid fa-user fa-lg"></i></a></li>
					</ul>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
			<ul>
				<li><a href="http://localhost:8080/PeekABook/login.html">Login</a></li>
			</ul>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${not empty utente}">
			<ul>
				<li><a class="icons" href="http://localhost:8080/PeekABook/ShowCarrello" id="cart"><i style="margin-right: 0px!important;" class="fas fa-shopping-cart fa-lg"></i><p style="color: white; display: inline; margin-left: 3px;">${carrello.numeroArticoli}</p></a></li>
			</ul>
			</c:when>
			<c:otherwise>
			<ul>
				<li><a class="icons" href="http://localhost:8080/PeekABook/login.html" id="cart"><i style="margin-right: 0px!important;" class="fas fa-shopping-cart fa-lg"></i></a></li>
			</ul>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="cancel-icon">
		<span class="fas fa-times"></span>
	</div>
	<div class="wrapper">
		<div class="search-input">
			<a href="" hidden></a>
			<input type="text" placeholder="Cerca un libro...">
			<div class="autocom-box">
			
			</div>
			<div class="icon"><i class="fas fa-search"></i></div>
		</div>
	</div>
</nav>

<script>
	const menuButton = document.querySelector(".menu-icon span");
	const cancelButton = document.querySelector(".cancel-icon");
	const items = document.querySelector(".nav-items");
	menuButton.onclick = ()=>{
		items.classList.add("active");
		menuButton.classList.add("hide");
		cancelButton.classList.add("show");
	}
	cancelButton.onclick=()=>{
		items.classList.remove("active");
		menuButton.classList.remove("hide");
		cancelButton.classList.remove("show");
		cancelButton.style.color="#fff";
	}
	
</script>

</body>

</html>