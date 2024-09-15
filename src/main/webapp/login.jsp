<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--	<meta charset="utf-8">--%>
<%--	<link rel="shortcut icon" href="favicon.ico">--%>
<%--	<title>CHCILY</title>--%>
<%--	<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>

<%--	<!-- MATERIAL DESIGN ICONIC FONT -->--%>
<%--	<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">--%>

<%--	<!-- STYLE CSS -->--%>
<%--	<link rel="stylesheet" href="css/loginstyle.css">--%>
<%--</head>--%>

<%--<body>--%>

<%--<div class="wrapper" style="background-image: url('img/bg-registration-form-2.jpg');">--%>
<%--	<div class="inner">--%>
<%--		<form action="login" method="post">--%>
<%--			<h3>SIGN IN</h3>--%>
<%--			<c:if test="${error != null}">--%>
<%--				<div class="alert alert-danger text-center" style="width: fit-content; margin: 0 auto;color: red">--%>
<%--						${error}--%>
<%--				</div>--%>
<%--			</c:if>--%>
<%--				<div class="form-wrapper">--%>
<%--					<label >Username</label>--%>
<%--					<input id="username" name="username" type="text" class="form-control" required>--%>
<%--				</div>--%>

<%--			<div class="form-wrapper">--%>
<%--				<label >Password</label>--%>
<%--				<input id="password" name="password" type="password" class="form-control" required>--%>
<%--			</div>--%>
<%--			<div class="forgot-pass">--%>
<%--				<a href="forget-password.jsp">Forgot Password?</a>--%>
<%--			</div>--%>
<%--			<button>LOGIN</button>--%>
<%--			<div class="sign-up">--%>
<%--				Not a member?--%>
<%--				<a href="registration.jsp">signup now</a>--%>
<%--			</div>--%>
<%--		</form>--%>
<%--	</div>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Chicly - Login</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>
<body>
<div class="login-container">
	<div class="login-logo">
		<img src="img/logo.png" alt="Chicly Logo">
	</div>
	<div class="login-form">
		<h2>Sign-in</h2>
		<form method="POST" action="loginServlet">
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" class="form-control" id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" class="form-control" id="password" name="password" required>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block">
					<i class="fas fa-sign-in-alt"></i> Login
				</button>
			</div>
			<div class="form-group text-center">
				<a href="forget-password.jsp">Forgot Password?</a>
			</div>
			<div class="sign-up form-group text-center">
				<p>Not a member? <a href="registration.jsp">
					signup now <i class="fas fa-user-plus"></i></a></p>
			</div>
		</form>
	</div>
</div>
</body>
</html>
