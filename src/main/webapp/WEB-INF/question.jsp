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
	<p> ENGLISH BATTLE utilisateur ${joueur.prenom}, meilleur score: ${joueur.meilleurScore} verbes <a href="deconnection">déconnection</a></p>
	
	<h2> Question ${sessionScope.partie.nbQuestions}: ${verbe.baseVerbale }</h2>
	<p> il reste : ${sec} secondes </p> 
	<form action="correction"  method="post">
		<p> ${verbe.baseVerbale}</p>
		<br>
		<c:choose>
		<c:when test="${sessionScope.rand > 0.5 }">
			<label for="preterit">Prétérit</label>
			<input type="text" name="preterit"  value="${preterit}">
			<label for="pp">Participe passé</label>
			<input type="text" name="pp" >
		</c:when>
		<c:otherwise >
			<label for="preterit">Prétérit</label>
			<input type="text" name="preterit">
			<label for="pp">Participe passé</label>
			<input type="text" name="pp"  value="${pp}">
		</c:otherwise>
		</c:choose>
		
		<c:if test="${niveau.nom eq 'débutant' }"><p>${verbe.traduction}</p></c:if>
		
		<br>
		<input type="submit" value="envoyer">
	</form>
</body>
</html>