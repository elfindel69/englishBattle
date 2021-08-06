<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>English battle</title>
</head>
<body>
	<h1>Modifier ${verbe.baseVerbale}</h1>
	
	<form action="verbe" method="post"> 
		<label for="baseVerbale">Base Verbale</label>
		<input name="baseVerbale" value="${verbe.baseVerbale}">
		<br>
		<label for="preterit">Prétérit</label>
		<input name="preterit" value="${verbe.preterit}">
		<br>
		<label for="pp">participe passé</label>
		<input name="pp" value="${verbe.preterit}">
		<br>
		<label for="traduction">traduction</label>
		<input name="traduction" value="${verbe.traduction}">
		<br>
		<input type="submit" value="modifier">
	</form>
</body>
</html>