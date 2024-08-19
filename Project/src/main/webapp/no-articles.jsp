<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="it">
<head>
    <meta charset="utf-8">
	<title>Prodotti</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/7c496c47e4.js" crossorigin="anonymous"></script>
	<link rel="shortcut icon" href="./icons/favico.jpg" type="image/x-icon">
	<link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/footer1.css">
    <link rel="stylesheet" href="./css/items.css">
    <link rel="stylesheet" href="./css/search.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
</head>
<body>

<jsp:include page="./header.jsp" />

<div class = "products">
    <h1 class="lg-title">Nessun articolo corrisponde alla ricerca</h1>
</div>

<%@include file="./footer.html"%>
</body>
</html>
