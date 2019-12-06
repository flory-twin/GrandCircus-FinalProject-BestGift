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
 
<%-- 	<p><img src="${ i.results[0].url_570xN }" width="270" height="200"/></p> --%>
<%-- 	<p>${g.title}</p> --%>
<%-- 	<p>${g.price} ${g.currency_code}</p> --%>
<%-- 	<p>${g.description}</p> --%>
	<table class="table">
		
	<c:forEach var="g" items="${result.getResults() }">
		<tr>
			<td>${g.title}</td>
			<td>${g.price} ${g.currency_code}</td>
			<td>
				<img src="${gs.getGiftImage(g.listing_id).results[0].url_570xN }"  width="270" height="200"/>
			</td>
			<td>${g.description}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>