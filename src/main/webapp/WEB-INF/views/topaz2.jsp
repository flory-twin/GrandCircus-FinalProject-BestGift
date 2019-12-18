<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="co.grandcircus.bestgift.tables.GiftList"%>
<%@ page import="co.grandcircus.bestgift.models.etsy.Gift"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<title>Best Gift Finder</title>
<!--  TODO Brian to add this to shared JSP. -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"	href="https://fonts.googleapis.com/css?family=Karma">
<style>
Head
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px
}

.desLim {
	width: 300px;
	Body
	,h1,h2,h3,h4,h5,h6
	{font-family
	:
	"Karma"
	,
	sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px
}

.text:hover {
	overflow: visible;
}
</style>




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
      	
<c:forEach var="f" items="${ favorites.getGifts() }" varStatus = "j">
	${ f }
</c:forEach>

<script>
	function openLeftMenu() {
		document.getElementById("leftMenu").style.display = "block";
	}

	function closeLeftMenu() {
		document.getElementById("leftMenu").style.display = "none";
	}

	function openRightMenu() {
		document.getElementById("rightMenu").style.display = "block";
	}

	function closeRightMenu() {
		document.getElementById("rightMenu").style.display = "none";
	}
</script>


</body>
</html>
