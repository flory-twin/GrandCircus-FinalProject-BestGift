<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search History (Non-User-Specific)</title>

<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/lux/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-oOs/gFavzADqv3i5nCM+9CzXe3e5vXLXZ5LZ7PplpsWpTCufB7kqkTlC9FtZ5nJo"
	crossorigin="anonymous">
</head>

<body>

	<!-- ------------------------------------------------------------------------- -->
	<!--  This page takes an optional, non session parameter--Integer listId.      -->
	<!--  If set, this param causes the gift list with that ID in the DB to be     -->
	<!--  displayed. -->
	<!-- ------------------------------------------------------------------------- -->
	<h1>List of Searches</h1>
	
	<div class="table table-hover">
		<table class="table-dark" border=1>
			<thead>
				<tr>
					<th>Search Number</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:forEach var="gl" varStatus="i" items="${ gs.getCompleteSearchHistory() }">
				<tr>
					<td>${ i.index }</td>
					<td>
						<form action="/search-history">
							<input type="hidden" name="listId" value="${ gl.getGiftListId() }"/>
							<input type="submit" name="Show Search"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<h1> Value of repo: ${ gl }</h1>
		<h1>Gifts Returned by Search</h1>
		<c:choose>
			<c:when test="${ listId == null }">
				No search has been selected.		
			</c:when>
			<c:otherwise>
				<table class="table-dark" border=1>
					<c:forEach var="giftInList" items="${ gl.getOne(listId).getGifts() }">
						<tr>
							<td><img
								src="${gs.getGiftImage(giftInList.listingId).results[0].url_570xN }"
								width="270" height="200" /></td>
							<td>${giftInList.title}</td>
							<td>${giftInList.price}${giftInList.currencyCode}</td>
		
							<td>${giftInList.description}</td>
						</tr>
					</c:forEach>
				</table>
				
			</c:otherwise>
		</c:choose>
</div>
	</div>
	
</body>
</html>