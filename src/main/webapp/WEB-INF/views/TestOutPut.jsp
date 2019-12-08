<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<<<<<<< HEAD
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
=======
>>>>>>> c177afd54f8e1b9d64430f9ae1c149688a8d08dc
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<<<<<<< HEAD
<title>Test</title>
</head>
<body>

<!-- <a href="/image?listing_id=494853077">Show Cookies</a> -->
<%-- <img src="${ i.results[0].url_570xN }" width="270" height="200"/> --%>
<h1>Test</h1>
		<table>
			<c:forEach var="g" items="${giftresult}">
				<tr>
					<td><img
						src="${gs.getGiftImage(g.listing_id).results[0].url_570xN }"
						width="270" height="200" /></td>
					<td>${g.title}</td>
					<td>${g.price} ${g.currency_code}</td>

					<td>${g.description}</td>
				</tr>
			</c:forEach>
		</table>
=======
<title>Insert title here</title>
</head>
<body>

<a href="/image?listing_id=494853077">Show Cookies</a>
<img src="${ i.results[0].url_570xN }" width="270" height="200"/>

>>>>>>> c177afd54f8e1b9d64430f9ae1c149688a8d08dc
</body>
</html>