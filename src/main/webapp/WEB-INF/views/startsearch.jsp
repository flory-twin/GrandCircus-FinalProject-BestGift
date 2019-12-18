<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<title>Best Gift: Start Search</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Karma">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.w3-bar-block .w3-bar-item {
	padding: 20px

}

</style>

<body>

<div class="w3-sidebar w3-bar-block w3-card w3-animate-left" style="display:none; width:25%" id="leftMenu">
  <button onclick="closeLeftMenu()" class="w3-bar-item w3-button w3-xlarge">Close &times;</button>
	<table>
  <tr>
    <th>Time</th>
    <th>Keywords Used</th>
  </tr>
  	<c:forEach var="sh" items="${shr.findSearchByUser(user)}">
  <tr>
    <td><a href="/gift-history?historyLogId=${sh.historyLogId}">${sh.createdAt}</a></td>
    <c:forEach var="kw" items="${sh.query.getAllKeywordsAsStrings()}">
    <td>
    <a href="etsy-results?keywords1=${kw}">${kw}</a>
    </td>
    </c:forEach>
  </tr>
  	</c:forEach>
	</table>
</div>

<div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display:none;right:0;" id="rightMenu">
  <button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large">Close &times;</button>
  <a href="#" class="w3-bar-item w3-button">Link 1</a>
  <a href="#" class="w3-bar-item w3-button">Link 2</a>
  <a href="#" class="w3-bar-item w3-button">Link 3</a>
</div>

<div>
  <button class="w3-button w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
  <button class="w3-button w3-xlarge w3-right" onclick="openRightMenu()">&#9776;</button>
  <div class="w3-container">
  </div>
</div>

<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">
	<div>  
  <c:forEach var="g" items="${giftHistory.searchResult.gifts}">  
    <div class="w3-quarter">
      <img src=${gs.getGiftImage(g.listingId).results[0].url_570xN } width="270" height="200" hspace="15"  style="width:90%; float:left; margin: 5px;">
      <h3>${g.price} ${g.currencyCode}</h3>
      <!--  This paragraph tag sets the hidden static elements which keep the description blocks uniformly sized. -->
      <div class="text"><p style="width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 10.8em;
	width: 18em;
	line-height: 1.7em;
	" >${g.description}</p></div>
    </div>
    </c:forEach>
</div>
	<div class="w3-third">
		<h3>Search By More KeyWords</h3>
		<p>
		<form action="/etsy-results">
			<table>
				<tr>
					<td>Search Param:*</td>
					<td><input type="text" name="keywords1" placeholder="blue" required /></td>
				</tr>
				<tr>
					<td>Search Param:</td>
					<td><input type="text" name="keywords2" placeholder="top hat"/></td>
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