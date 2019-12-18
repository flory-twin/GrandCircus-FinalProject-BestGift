<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="co.grandcircus.bestgift.tables.GiftList"%>
<%@ page import="co.grandcircus.bestgift.models.etsy.Gift"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/sandstone/bootstrap.min.css" rel="stylesheet" integrity="sha384-G3Fme2BM4boCE9tHx9zHvcxaQoAkksPQa/8oyn1Dzqv7gdcXChereUsXGx6LtbqA" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-light bg-light fixed-top">Hi!</nav>

      	<table>
      	Favorites size: ${favorites.getGifts().size() }
      		
      			<c:forEach var="fav" items="${ gl.findByGiftListId(favorites.getGiftListId()) }" varStatus="s">
      			<tr><td>
      			Fave is: ${f}
      			
      			<%-- <tr>
	      			<td>
					     <div>
							<a href="https://www.etsy.com/listing/${ fav.listingId }" target="_blank"><img src=${gs.getGiftImage(fav.listingId).results[0].url_570xN }
									width="270" height="200" hspace="15" style="border-radius:10%">
							</a>        
							<h3>${fav.price} ${fav.currencyCode}
				        </h3>
				        <!--  This paragraph tag sets the hidden static elements which keep the description blocks uniformly sized. -->
				        <div class="text">
				          <p style="width: 300px;
				        	   overflow: hidden;
				        	   text-overflow: ellipsis;
				        	   height: 10.8em;
				        	   width: 18em;
				        	   line-height: 1.7em;">
				            ${fav.description}
				          </p>
				        </div>
				        <!--  TODO Brian to add mouseover text containing full description. -->
				      </div>
	      			</td>
	      			<td>
				      	<div class="w3-third w3-serif">
							<h3>Keywords</h3>
							<p>
								<c:forEach var="kw"
									items="${ keywords.get(fav.getListingId()) }">
									<span  class="w3-tag w3-black w3-margin-bottom">${ kw }</span>
								</c:forEach>
			
							</p>
						</div>
	      			</td>
      			</tr>--%>
      			</td></tr>
      			</c:forEach>
      	</table>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
      	<br/>
</body>
</html>