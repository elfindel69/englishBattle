<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>English battle</title>
<link rel="stylesheet" href="styles/styles.css">
</head>
<body>
	<h1>Inscription</h1>
	<c:if test="${erreur ne null }">
		<p>${erreur}</p>
	</c:if>
	<form action="inscription" method="post">
		<label for="prenom">prénom</label>
		<input type="text" name="prenom" value="${prenom}"> 
		<br>
		<label for="nom">nom</label>
		<input type="text" name="nom" value="${nom}"> 
		<br>
		<label for="email">email</label>
		<input type="text" name="email" value="${email}"> 
		<br>
		<label for="password">mot de passe</label>
		<input type="password" name="password"> 
		<br>
		<label for="ville">ville</label>
		<select name="ville">
			<option value="0">choisir une ville</option>
			 <c:forEach var="ville" items="${villes}">
                     <option value="${ville.id}" <c:if test="${ville.id eq idVille}">selected</c:if>>${ville.nom} </option>
             </c:forEach>
		</select>
		
		<label for="niveau">niveau</label>
		
		<select name="niveau">
			<option value="0">choisir un niveau</option>
			 <c:forEach var="niveau" items="${niveaux}">
                     <option value="${niveau.id}" <c:if test="${niveau.id eq idNiveau}">selected</c:if>>${niveau.nom} </option>
             </c:forEach>
		</select>
		<br>
		<input type="submit" value="envoyer">
	</form>
</body>
</html>