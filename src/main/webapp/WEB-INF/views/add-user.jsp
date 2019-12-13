<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/sketchy/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-N8DsABZCqc1XWbg/bAlIDk7AS/yNzT5fcKzg/TwfmTuUqZhGquVmpb5VvfmLcMzp"
	crossorigin="anonymous">
</head>
<body>

	<h1>Add User</h1>

	<form action="/add-user" method="post">
	Email: <input type="email" name="emailAddress" placeholder="Email Address" required> <br>
	Password: <input type="password" id="password" onkeyup= "confirmPassword()" name="passWord" placeholder="Password" required> <br>
	Confirm Password: <input type="password" id="confirm_password" onkeyup= "confirmPassword()" placeholder="Confirm Password" required><br>
	<span id="message"></span> <br>
	<input class="btn btn-primary" type="submit" id="submit" value="Add User" disabled>
    <input class="btn btn-secondary" type = "reset" value = "Clear">
	</form>
	
<script>
function confirmPassword() {
	  if (document.getElementById("password").value === document.getElementById("confirm_password").value) {
	    document.getElementById("message").style.color = "green";
	    document.getElementById("message").innerHTML = "Passwords are matching";
	    document.getElementById("submit").disabled = false;
	  } else {
	    document.getElementById("message").style.color = "red";
	    document.getElementById("message").innerHTML = "Passwords are not matching! You will not be able to submit unless the passwords match";
	    document.getElementById("submit").disabled = true;
	  }
}
</script>
</body>
</html>