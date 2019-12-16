<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<title>Best Gift Finder</title>
<!--  TODO Brian to add this to shared JSP. -->
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

.desLim {
	width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 10.8em;
	width: 18em;
	line-height: 1.4em;
}

.desLim:hover {
	overflow: visble;
}
</style>
<body>
	<div class="w3-top">
		<div class="w3-white w3-xlarge"
			style="max-width: 1200px; margin: auto">
			<div class="w3-right w3-padding-16">Possible Link</div>
			<div class="w3-center w3-padding-16">Best Gift Finder</div>

		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<form action="/start-search" method="post">
		<table align="center" style="border: 1px solid black; border-radius: 10px;">
			<tr>
				<th align="left">LogIn</th>

			</tr>
			<tr>
				<th align="left"><label for="uemail"><b>Email
							Address</b></label></th>
				<th><input type="text" placeholder="Email Address"
					name="emailAddress" required></th>
			</tr>
			<tr>
				<td align="left"><label for="psw"><b>Password</b></label></td>
				<td><input type="password" placeholder="Enter Password"
					name="psw" required></td>
			</tr>
			<tr>
				<th align="left"><button type="submit">Login</button></th>
			</tr>
			<tr>
				<th align="left"><a class="btn btn-primary" href="/new-account"><button
							type="submit">New Account</button></a></th>
			</tr>
		</table>
	</form>
</body>
</html>