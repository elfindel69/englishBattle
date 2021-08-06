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
	<h1>Liste des verbes</h1>
	<table>
	<tr>
		<th>base Verbale</th>
		<th>prétérit</th>
		<th>participe passé</th>
		<th>traduction</th>
		<th>modifier</th>
	</tr>
		<c:forEach var="verbe" items="${verbes}">
			<tr>
				<td>${verbe.baseVerbale}</td>
				<td>${verbe.preterit}</td>
				<td>${verbe.participePasse}</td>
				<td>${verbe.traduction}</td>
				<td><a href="verbe?id=${verbe.id}">modifier</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="index">retour à l'index</a>
</body>
</html>