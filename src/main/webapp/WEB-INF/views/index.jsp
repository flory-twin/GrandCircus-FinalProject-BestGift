<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/sketchy/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-N8DsABZCqc1XWbg/bAlIDk7AS/yNzT5fcKzg/TwfmTuUqZhGquVmpb5VvfmLcMzp"
	crossorigin="anonymous">

<style>
form {
	border: 3px solid #f1f1f1;
}

/* Full-width inputs */
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

/* Set a style for all buttons */
button {
	background-color: #4d524d;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

/* Add a hover effect for buttons */
button:hover {
	opacity: 0.8;
}

/* Extra style for the cancel button (red) */
.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #36f1f4;
}

/* Center the avatar image inside this container */
.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

/* Avatar image */
img.avatar {
	width: 40%;
	border-radius: 50%;
}

/* Add padding to containers */
.container {
	padding: 16px;
}

/* The "Forgot password" text */
span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>
<body>
	<form action="/gift-results" method="post">
		<div class="imgcontainer">
			<img src="img_avatar2.png" alt="Login" class="avatar">
		</div>
		
		<div class="container">
			<label for="uemail"><b>Email Address</b></label> 
			<input type="text" placeholder="Email Address" name="emailAddress" required> 
			<label for="psw"><b>Password</b></label> 
			<input type="password" placeholder="Enter Password" name="psw" required>
			<button type="submit">Login</button>
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<a class="btn btn-primary" href="/new-account">New Account</a>
		</div>
	</form>
		
</body>
</html>