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
	<c:forEach var="verbe" items="${verbes}">
		<p> base Verbale: ${verbe.baseVerbale, prétérit: ${verbe.preterit},
		participe passé: ${verbe.participePasse} - traduction: ${verbe.traduction}
	</c:forEach>
</body>
</html>