<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Chicly</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- MATERIAL DESIGN ICONIC FONT -->
	<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

	<!-- STYLE CSS -->
	<link rel="stylesheet" href="css/loginstyle.css">
</head>

<body>
<c:if test="${error != null}">
	<div class="alert alert-danger text-center" style="width: fit-content; margin: 0 auto;">
			${error}
	</div>
</c:if>
<div class="wrapper" style="background-image: url('img/bg-registration-form-2.jpg');">
	<div class="inner">
		<form action="login" method="post">
			<h3>SIGN IN</h3>

				<div class="form-wrapper">
					<label >Username</label>
					<input id="username" name="username" type="text" class="form-control" required>
				</div>

			<div class="form-wrapper">
				<label >Password</label>
				<input id="password" name="password" type="password" class="form-control" required>
			</div>
			<div class="forgot-pass">
				<a href="forget-password.jsp">Forgot Password?</a>
			</div>
			<button>LOGIN</button>
			<div class="sign-up">
				Not a member?
				<a href="registaration.jsp">signup now</a>
			</div>
		</form>
	</div>
</div>

</body>
</html>