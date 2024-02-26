<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="resources/css/login.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="login-container">
		<div class="logo-home">
			<button class="Home-btn" onclick="location.href='./'">
				<img src="resources/images/lg.png" class="logo">
			</button>
		</div>
		<div class="login-box">
			<h2>Login</h2>
			<form action="loginProc" method="post">
				<div class="user-box">
					<input type="text" id="email" name="m_email" required=""> <label
						for="email">Username</label>
				</div>
				<div class="user-box">
					<input type="password" id="password" name="m_password" required="">
					<label for="password">Password</label>
				</div>

				<button type="submit">
					<span></span>
					<span></span>
					<span></span>
					<span></span>
					LOGIN
				</button>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	let msg = "${msg}";
	if (msg != "") {
		alert(msg);
	}
</script>
</html>