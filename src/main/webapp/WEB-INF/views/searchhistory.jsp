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
<!-- KDF TODO: Rebuild this entire page! -->
	
	<!-- ------------------------------------------------------------------------- -->
	<!--  This page takes an optional, non session parameter--Integer listId.      -->
	<!--  If set, this param causes the gift list with that ID in the DB to be     -->
	<!--  displayed. -->
	<!-- ------------------------------------------------------------------------- -->
	<h1>List of Searches</h1>
	<c:forEach var="sh" items="${shr.findSearchByUser(user)}">
	<p>${sh.createdAt} ${sh.query.k1.value} ${sh.query.k2.value}</p>
		<c:forEach var="gl" items="${sh.searchResult.gifts}">
			<p>${gl}</p>
		</c:forEach>
	</c:forEach>
	<div class="table table-hover">
		<table class="table-dark" border=1>
			<thead>
				<tr>
					<th>User</th>
					<th>Search Date</th>
					<th>Search Parameters</th>
					<th>Selected Results</th>
				</tr>
			</thead>
			<c:forEach var="sh" varStatus="i" items="${ shr.findAll() }">
				<tr>
					<td>${ user }</td>
					<td>${ sh.getCreatedAt() }	</td>
					<td>
					     <div class="container">
					     	<p>
					      		<c:forEach var="kw" items="${ sh.getQuery().getAllKeywordsAsStrings()}">
					      			<span class="w3-tag w3-black w3-margin-bottom">${ kw }</span>
					      		</c:forEach>
					      	</p>     	
   						</div>
					</td>
					<td>
						<div class="container">
							<!-- Get the first two results and their images; display. -->	
							<div class="container">
							    <div class="w3-quarter">
								      <!-- TODO Needs update to synch with main page changes.  -->
								      <img src=${ gs.getGiftImage(sh.getSearchResult().getGifts()[0].listingId)
								      	  .results[0].url_570xN } width="80" height="100" hspace="15"  style="width:90%; float:left; margin: 5px;">
								      <h3>${sh.getSearchResult().getGifts()[0].price} ${sh.getSearchResult().getGifts()[0].currencyCode}</h3>
								      <!--  This paragraph tag sets the hidden static elements which keep the description blocks uniformly sized. -->
								      <p style="width: 300px;
									overflow: hidden;
									text-overflow: ellipsis;
									height: 10em;
									width: 10em;
									line-height: 1.7em;
									" >${sh.getSearchResult().getGifts()[0].description.substring(0, 20)}...</p>
									<!--  TODO Brian to add mouseover text containing full description. -->
								</div>
							</div>
						</div> 
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
		
	<div>
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
	
</body>
</html>