<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>English battle</title>
</head>
<body>
	<h1>liste des joueurs</h1>
	<form action="joueurs" method="post">
		<label for="villes">Villes</label>
		<select name="idVille" >
			<option value="0">Toutes les villes</option>
				<c:forEach var="ville" items="${villes}">
					<option value="${ville.id}">${ville.nom}</option>
				</c:forEach>
		</select>
		<label for="prenom">Prénom</label>
		<input type="text" name="prenom">
		<input type="submit" value="modifier">
	</form>
	<table>
		<tr>
			<th>Prénom</th>
			<th>Nom</th>
			<th><a href="joueurs?triListe=1">Ville</a></th>
			<th>Niveau</th>
			<th><a href="joueurs?triListe=2">Meilleur Score</a></th>
		</tr>
		<c:forEach var="joueur" items="${joueurs}">
			<tr>
				<td>${joueur.prenom}</td>
				<td>${joueur.nom}</td>
				<td>${joueur.ville.nom}</td>
				<td>${joueur.niveau.nom}</td>
				<td>${joueur.meilleurScore}</td>
			</tr>
		
		</c:forEach>
		
	</table>
	<a href="joueurs">liste non triée</a>
	<a href="index">retour à l'index</a>
</body>
</html>