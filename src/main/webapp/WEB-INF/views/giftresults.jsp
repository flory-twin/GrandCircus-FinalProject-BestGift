<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gift Results</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/sketchy/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-N8DsABZCqc1XWbg/bAlIDk7AS/yNzT5fcKzg/TwfmTuUqZhGquVmpb5VvfmLcMzp"
	crossorigin="anonymous">
</head>
<body>
	<p>${giftresult.title}</p>
	<p>${giftresult.price} ${giftresult.currency_code}</p>
	<p><a target="_blank" href="${giftresult.url}">${giftresult.url}</a></p>
	<c:forEach var="cp" items="${giftresult.category_path}">
		<p>${cp}</p>
	</c:forEach>
	<c:forEach var="m" items="${giftresult.materials}">
		<p>${m}</p>
	</c:forEach>
	<c:forEach var="tp" items="${giftresult.taxonomy_path}">
		<p>${tp}</p>
	</c:forEach>
	<p>${giftresult.description}</p>
</body>
</html>