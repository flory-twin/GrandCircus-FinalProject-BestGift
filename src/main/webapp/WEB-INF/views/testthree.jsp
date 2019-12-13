<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<title>Best Gift Test Output</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif}
.w3-bar-block .w3-bar-item {padding:20px}
</style>
<body>
<nav class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:40%;min-width:300px" id="mySidebar">
  <a href="javascript:void(0)" onclick="w3_close()"
  class="w3-bar-item w3-button">Close Menu</a>
  <a href="" onclick="w3_close()" class="w3-bar-item w3-button">Search 17:29</a>
  <a href="" onclick="w3_close()" class="w3-bar-item w3-button">Search 03:46</a>
  <a href="" onclick="w3_close()" class="w3-bar-item w3-button">Search 19:29</a>
  <a href="" onclick="w3_close()" class="w3-bar-item w3-button">Search 01:46</a>
</nav>
<div class="w3-top">
  <div class="w3-white w3-xlarge" style="max-width:1200px;margin:auto">
    <div class="w3-button w3-padding-16 w3-left" onclick="w3_open()">(>")>-+</div>
    <div class="w3-right w3-padding-16">Possible Link</div>
    <div class="w3-center w3-padding-16">Best Gift Finder</div>
    
    
    





  </div>
</div>
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">  
  <c:forEach var="g" items="${ currentGiftList }">  
    <div class="w3-quarter">
      <img src=${gs.getGiftImage(g.listingId).results[0].url_570xN } width="270" height="200" hspace="15"  style="width:90%; float:left; margin: 5px;">
      <h3>${g.price} ${g.currencyCode}</h3>
      <p style="width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 10.8em;
	width: 18em;
	line-height: 1.7em;
	" >${g.description}</p>
    </div>
   
    
    </c:forEach>

  <footer class="w3-row-padding w3-padding-32">
    <div class="w3-third">
      <h3>Search By More KeyWords</h3>
      <form action="/etsy-results">
      	<c:forEach var="kw" items="${ shr.findByMaxCreatedAt().getQuery().getAllKeywordsAsStrings() }" varStatus="s">
      		Search Param: <input type="text" name="keywords${ s.count }" value="${ kw }"/> <br>
      	</c:forEach>
      	Search Param: <input type="text" name="keywords1" /> <br>
      	<input type="submit" value="Search"/>
      </form>
      
      
    </div>
  
    <div class="w3-third">
      <h3>Past Favorite Items</h3>
      <ul class="w3-ul w3-hoverable">
        <li class="w3-padding-16">
          <img src="" class="w3-left w3-margin-right" style="width:50px">
          <span class="w3-large">Cute Catz</span><br>
          <span>meow meow meow meow meow</span>
        </li>
        <li class="w3-padding-16">
          <img src="" class="w3-left w3-margin-right" style="width:50px">
          <span class="w3-large">tree frog</span><br>
          <span>In the tree looking at you</span>
        </li> 
      </ul>
    </div>



    <div class="w3-third w3-serif">
      <h3>Interested Keywords</h3>
      <p>
      	<c:forEach var="kw" items="${ shr.findByMaxCreatedAt().getQuery().getAllKeywordsAsStrings()}">
      		<span class="w3-tag w3-black w3-margin-bottom">${ kw }</span>
      	</c:forEach>
      	
      </p>
    </div>
  </footer>
</div>

<script>

function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
}
 
function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
}
</script>

</body>
</html>
