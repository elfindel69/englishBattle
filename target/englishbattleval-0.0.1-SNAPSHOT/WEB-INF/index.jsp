
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>English battle</title>
	<link rel="stylesheet" href="styles/styles.css">
</head>
<body>
	<h1>Bienvenue sur English Battle</h1>
	<c:if test="${erreur ne null }">
	<p>${erreur }</p>
	</c:if>
	<form action="connexion" method="post">
		<label for="email">email</label>
		<input type="email" name="email"> 
		<br>
		<label for="password">mot de passe</label>
		<input type="password" name="password"> 
		<br>
		<input type="submit" value="connexion">
	</form>
	<a href="inscription">inscription</a>
	<br>
	<a href="verbes">liste des verbes</a>
	<a href="joueurs">liste des joueurs</a>
	<br>
	<h2>Hall of Fame</h2>
	<ul>
		<c:forEach var="joueur" items="${joueurs}">
            <li>${joueur.prenom} ${joueur.nom}: ${joueur.getMeilleurScore()} verbes 
            <c:if test="${joueur.estEnLigne }"> (en ligne)</c:if>
            </li>
        </c:forEach>
	</ul>
</body>
</html>