<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<title>Best Gift: Start Search</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/sandstone/bootstrap.min.css" rel="stylesheet" integrity="sha384-G3Fme2BM4boCE9tHx9zHvcxaQoAkksPQa/8oyn1Dzqv7gdcXChereUsXGx6LtbqA" crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px

}

</style>

<body>

<header class="navbar navbar-light bg-light fixed-top" style="height: 100px;">
	<button class="w3-button w3-xlarge w3-left" onclick="openLeftMenu()">&#9776; Search History</button>
	<h2 align="center">Gift Finder</h2>
	<button class="w3-button w3-xlarge w3-right" onclick="openRightMenu()">Keywords/Favorites &#9776;</button>
</header>

<body>

<!-- Right-hand collapsible element -->
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

<div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display:none;right:0; width: 30%; opacity: 0.95" id="rightMenu">
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
						<c:forEach var="kw"	items="${giftHistory.getQuery().getAllKeywordsAsStrings()}"	varStatus="s">
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
		
		<!-- Right-hand favorited gifts and keywords -->
		<br>
		<div>
		<table>
			<thead>
				<tr>
					<th>Favorite</th>
					<th>Keywords</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="f" items="${ favorites.getGifts() }" varStatus = "j">
					<tr>
						<td>
						<p>${ f.title.substring(0, 25) }
							<c:if test="${f.title.length() > 25 }" >...</c:if>
                        </p>
                        <p><img src=${gs.getGiftImage(f.listingId).results[0].url_570xN } width="180" height="130" hspace="15" style="border-radius: 10%"></p>
                        <p>${f.description.substring(0, 80) }<c:if test="${f.description.length() > 80 }" >...</c:if></p>
                        </td>
                        <td>
                 			<c:forEach var="kw" items="${ keywords.get(f.getListingId()) }">
							<span class="w3-tag w3-black w3-margin-bottom">${kw}</span>
							</c:forEach>
                        </td>
                     </tr>
                     <br>
				</c:forEach>
			</tbody>
		</table>
		<br>
		</div>
</div>

<!-- Right-hand collapsible element -->
<div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display: none; right: 0; width:50%; opacity: 0.95;" id="rightMenu">

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
		
		<!-- Right-hand favorited gifts and keywords -->
		<br>
		<div>
		<table>
			<thead>
				<tr>
					<th>Favorite</th>
					<th>Keywords</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="f" items="${ favorites.getGifts() }" varStatus = "j">
					<tr>
						<td>
						<p>${ f.title.substring(0, 25) }
							<c:if test="${f.title.length() > 25 }" >...</c:if>
                        </p>
                        <p><img src=${gs.getGiftImage(f.listingId).results[0].url_570xN } width="180" height="130" hspace="15" style="border-radius: 10%"></p>
                        <p>${f.description.substring(0, 80) }<c:if test="${f.description.length() > 80 }" >...</c:if></p>
                        </td>
                        <td>
                 			<c:forEach var="kw" items="${ keywords.get(f.getListingId()) }">
							<span class="w3-tag w3-black w3-margin-bottom">${kw}</span>
							</c:forEach>
                        </td>
                     </tr>
                     <br>
				</c:forEach>
			</tbody>
		</table>
		<br>
		</div>
</div>




<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">
	<div>  
  <c:forEach var="g" items="${giftHistory.searchResult.gifts}">  
    <div class="w3-quarter">
      <a href="https://www.etsy.com/listing/${g.listingId}" target="_blank">
      <img src=${gs.getGiftImage(g.listingId).results[0].url_570xN } width="270" height="200" hspace="15" style="border-radius: 10%">
      </a>
      <h3>${g.price} ${g.currencyCode}</h3>
      <!--  This paragraph tag sets the hidden static elements which keep the description blocks uniformly sized. -->
      <div class="text"><p style="width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 12em;
	width: 18em;
	line-height: 1.2em;
	" >${g.description}</p></div>
    </div>
    </c:forEach>
</div>
	<div align="center" >
		<h3>Search By More KeyWords</h3>
		<p>
		<form action="/etsy-results">
			<table>
				<tr>
					<td>Search Param:*</td>
					<td><input type="text" name="keywords1" placeholder="topaz" required /></td>
				</tr>
				<tr>
					<td>Search Param:</td>
					<td><input type="text" name="keywords2"/></td>
				</tr>
				<tr>
					<td>Search Param:</td>
					<td><input type="text" name="keywords3" /></td>
				</tr>
				<tr>
					<td>Search Param:</td>
					<td><input type="text" name="keywords4" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form>
	</div>

</div>
</body>


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