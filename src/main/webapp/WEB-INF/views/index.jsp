<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="index.css">
</head>

<body>
<p class="tip"> </p>
<div class="cont">
  <div class="form sign-in">
  <form action="/login-user" method="post">
    <h2>Welcome!!!</h2>
    <h2>${message}</h2>
    <label>
      <span>Email</span>
      <input type="text" name="emailAddress" required> 
    </label>
    <label>
      <span>Password</span>
      <input type="password" name="passWord" required>
    </label>
    <button class="submit" type="submit">Sign In</button>
    </form>
  </div>
  <div class="sub-cont">
    <div class="img">
      <div class="img__text m--up">
        <h2>New here?</h2>
        <p>Sign up and discover new gifts!</p>
      </div>
      <div class="img__text m--in">
        <h2>Have An Account?</h2>
        <p>If you already has an account, just sign in!</p>
      </div>
      <div class="img__btn">
        <span class="m--up">Sign Up</span>
        <span class="m--in">Sign In</span>
      </div>
    </div>
    <div class="form sign-up">
    <form action="/add-user" method="post">
      <h2>Time to feel like home</h2>
      <label>
        <span>Email</span>
        <input type="email" name="emailAddress" required>
      </label>
      <label>
        <span>Password</span>
        <input type="password" id="password" onkeyup= "confirmPassword()" name="passWord" required>
      </label>
      <label>
        <span>Confirm Password</span>
        <input type="password" id="confirm_password" onkeyup= "confirmPassword()" required>
      </label>
      <label>
      <span id="message"></span>
      </label>
      <button class="submit" type="submit" id="submit" value="Add user" disabled>Sign Up</button>
      </form>
    </div>
  </div>
</div>


<script>


document.querySelector('.img__btn').addEventListener('click', function() {
	  document.querySelector('.cont').classList.toggle('s--signup');
	});
	
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