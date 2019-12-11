<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gift Results</title>

<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/lux/bootstrap.min.css"
	rel="stylesheet">
</head>
<style>

parent{
display:grid;


}

body {
  background-image: url('img_girl.jpg');

}

.content-table {
    border-radius: 25px;
    background-color: #cccccc;
	float: left;
	font-size: 0.9em;
	width: 250px;
	height: 480px;
	overflow: hidden;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
	margin: 5px;
}

th, td {
	padding: 10px;
}

.limTit {
	width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	max-height: 1.8em;
	line-height: 1.8em;
}
.limTit:hover {
	overflow: visible;
	background-color: #cccccc;

}

.lim {
	width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	max-height: 10.8em;
	line-height: 1.8em;
}

.lim:hover {
	overflow: visible;
	display: block;
	z-index: 10;
}

.imgProp {
	display: table-cell;
	position: top;
}
</style>

<body>

	<h1>Refinement Search Logic</h1>
	<h1>Search By Etsy</h1>
	<form action="/etsy-results">
		Keywords: <input type="text" name="keywords" /> Max Price: <input
			type="number" name="max_price" /> <input type="submit"
			value="Submit" />
	</form>

	<c:forEach var="g" items="${ currentGiftList }">
		<table class="content-table" >
			<tr>
				<td><div class="limTit">${g.title}</div></td>
			</tr>
			<tr>
				<th><img class="imgProp"
					src="${gs.getGiftImage(g.listingId).results[0].url_570xN }"
					width="270" height="200" hspace="15" /></th>
			</tr>

			<tr>
				<td>${g.price}${g.currencyCode}</td>
			</tr>
			<tr>
				<td><div class="lim">${g.description}</div></td>
			</tr>












			<%-- <tr>
				<th><img class="imgProp"
					src="${gs.getGiftImage(g.listingId).results[0].url_570xN }"
					width="270" height="200" hspace="20" /><br>
					<div class="limTit">${g.title}</div><br>${g.price}${g.currencyCode}<br>
					<div class="lim">${g.description}</div>
					</th>
				<th></th>
				<th></th>
			</tr> --%>
		</table>

	</c:forEach>




</body>
</html>