<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		    <c:when test="${import}">
		       <p>verbes importés</p> 
		        <br />
		    </c:when>    
		    <c:otherwise>
		       <p>erreur</p>
		        <br />
		    </c:otherwise>
		</c:choose>
</body>
</html>