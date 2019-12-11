<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test Page</title>
</head>
<body>

<!-- <a href="/image?listing_id=494853077">Show Cookies</a> -->
<%-- <img src="${ i.results[0].url_570xN }" width="270" height="200"/> --%>
<h1>Test</h1>
<<<<<<< HEAD
		<form action="/etsy-results2">
		Keywords: <input type="text" name="keywords"/>
		Keywords: <input type="text" name="keywords2"/>
		
<!-- 			<input type="checkbox" onchange="function(){document.getElementById('setMaxPrice').visibility='inline'}"> Max Price: <input type="number" id="setMaxPrice" name="max_price" style="visibility:none"/>
 -->		<input type="submit" value="Submit"/> 
	</form>
		<table>
			<c:forEach var="g" items="${result.getResults()}">
				<tr>
					<td><img
						src="${gs.getGiftImage(g.listingId).results[0].url_570xN }"
						width="270" height="200" /></td>
					<td>${g.title}</td>
					<td>${g.price} ${g.currencyCode}</td>

					<td>${g.description}</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>