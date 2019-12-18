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

Head, body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px
}

.desLim {
width: 300px; 
overflow: hidden;
text-overflow: ellipsis;
height: 12em;
width: 18em; 
line-height: 1.2em;

}
.desLim:hover{
/* overflow: visible;
background-color: coral;
height: 50em;
width: 18em;  */
}

.w3-bar-block .w3-bar-item {
	padding: 20px
}

.text:hover {
	overflow: visible;
}
</style>




<div class="w3-sidebar w3-bar-block w3-card w3-animate-left"
	style="display: none; width: 30%; opacity: 0.95" id="leftMenu">
	<button onclick="closeLeftMenu()"
		class="w3-bar-item w3-button w3-xlarge">Close &times;</button>
	<table>
		<tr>
			<th>Time</th>
			<th>Keywords Used</th>
		</tr>
		<c:forEach var="sh" items="${shr.findSearchByUser(user)}">
			<tr>
				<td><a href="/gift-history?historyLogId=${sh.historyLogId}">${sh.createdAt}</a></td>
				<td>${sh.query.getAllKeywordsAsStrings()}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<!-- This is the correct table format for searched keywords and synonyms -->
<div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display: none; right: 0; width:30%; opacity: 0.95;" id="rightMenu">
	<button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large">Close &times;</button>
			<div>
			<h3 align="center">Search By More KeyWords</h3>
			<form action="/etsy-results">	
				<!-- The following table lays out search parameters and possible synonyms in a grid. -->			
				<table>
					<thead>
						<tr>
							<td>Searched Terms:</td>
							<td>Pick a Synonym:</td>
						</tr>
					</thead>
					<tbody>
						<!-- Create a separate row for each of the search parameters used to create the last search. -->
						<c:forEach var="kw"	items="${ shr.findByMaxCreatedAt().getQuery().getAllKeywordsAsStrings() }"	varStatus="s">
							<tr>
								<td>
									<input id="option${ s.count+1}" type="text" name="keywords${ s.count+1}" value="${ kw }"  />
								</td>
								<td>
									<select onchange="changeKeyword(this,${ s.count +1})">
										<c:forEach var="synonym" items="${ dms.getSynonyms(kw) }" end="20" varStatus="t">
											<option value="${ synonym }">${ synonym }</option>
										</c:forEach>	
									</select>
								</td>
								<td>
								
								</td>
							</tr>
						</c:forEach>
						<!-- Create one additional row with a blank parameter. -->
						<tr>
							<td>
								<input type="text" name="keywords1" /> 
							</td>
							<td>
								<input	type="submit" value="Search" />
							</td>
						</tr>							
					</tbody>
				</table>
			</form>


		</div>
</div>

<div>
	<button class="w3-button w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
	<button class="w3-button w3-xlarge w3-right" onclick="openRightMenu()">&#9776;</button>
	<div class="w3-container"></div>
</div>

<div class="w3-main w3-content w3-padding"
	style="max-width: 1200px; margin-top: 100px">

	<!-- Form to contain the 1...${etsy.itemlimit}th 'favorites' checkboxes. -->
	<c:forEach var="g" items="${ currentGiftList }" varStatus="i">
		<form id="favoritesCheckboxForm${ i.count }"
			action="processFavoritesSelection">
			<div class="w3-quarter">
				<a href="https://www.etsy.com/listing/${ g.listingId }"
					target="_blank"><img
					src=${gs.getGiftImage(g.listingId).results[0].url_570xN }
					width="270" height="200" hspace="15" style="border-radius: 10%"></a>
				<h3>${g.price}
					${g.currencyCode}<br> Favorite: <input type="checkbox"
						name="checkbox"
						onclick="document.getElementById('favoritesCheckboxForm${ i.count }').submit();"
						<c:forEach var="f" items="${ favorites.getGifts() }" varStatus = "j">
                <c:if test="${ g.getListingId().equals(f.getListingId()) }">checked</c:if>
              </c:forEach> />
				</h3>
				<!--  This paragraph tag sets the hidden static elements which keep the description blocks uniformly sized. -->
				<div class="desLim">
						${g.description}
				</div>
				<!--  TODO Brian to add mouseover text containing full description. -->
			</div>
			<!-- Add a hidden input holding the description so it can be passed back to the controller.  -->
			<input type="number" value="${ g.listingId }" name="listId" hidden />
			<%--    <input type="submit" value="Submit"/> --%>
		</form>
	</c:forEach>

	<footer class="w3-row-padding w3-padding-32">
		<div class="w3-third">
			<h3>Search By More KeyWords</h3>
			<form action="/etsy-results">
				
						<c:forEach var="kw"
							items="${ shr.findByMaxCreatedAt().getQuery().getAllKeywordsAsStrings() }"
							varStatus="s">
						Search Param:<input type="text" name="keywords${ s.count + 1 }" value="${ kw }" />
						<br>
						Synonyms: <select><c:forEach var="synonym" items="${ dms.getSynonyms(kw) }" end="20"><option value="${ synonym }">${ synonym }</option></c:forEach></select>
						<br>
						</c:forEach>									
						Search Param: <input type="text" name="keywords1" /> <input	type="submit" value="Search" />
			</form>


		</div>

		<div class="w3-third">
			<h3>Past Favorite Items</h3>
			<ul class="w3-ul w3-hoverable">
				<c:forEach var="f" items="${ favorites.getGifts() }" varStatus = "j">
					<li>
						<span class="w3-large">
							${ f.title.substring(0, 25) }
							<c:if test="${f.title.length() > 25 }" >...</c:if>
                        </span><br> 
                        <span>${f.description.substring(0, 80) }<c:if test="${f.description.length() > 80 }" >...</c:if></span></li>
				</c:forEach>
			</ul>
		</div>



		<div class="w3-third w3-serif">
			<h3>Interested Keywords</h3>
			<p>
				<c:forEach var="kw" items="${shr.findByMaxCreatedAt().getQuery().getAllKeywordsAsStrings()}">
					<span class="w3-tag w3-black w3-margin-bottom"><a href="etsy-results?keywords1=${kw}">${kw}</a></span>
				</c:forEach>

			</p>
		</div>
	</footer>

</div>

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

	function changeKeyword(data,count) {
		console.log(data);
		console.log(count);
		document.getElementById("option".concat(count)).value = data.value;
	}
	
</script>


</body>
</html>
