<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<c:choose>
		<c:when test="${not empty param.successMessage}">
			<div class="alert alert-success alert-dismissible fade show" role="alert" style="font-size: 1.1em; font-weight: bold;">
				<i class="fas fa-check-circle"></i> ${param.successMessage}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="removeQueryParam()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
		<c:when test="${not empty param.errorMessage}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert" style="font-size: 1.1em; font-weight: bold;">
				<i class="fas fa-exclamation-triangle"></i> ${param.errorMessage}
				<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="removeQueryParam()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
	</c:choose>
	<div class="login-logo">
		<img src="img/logo.png" alt="Chicly Logo">
	</div>
	<div class="login-form">
		<form action="login" method="post">
			<h3>SIGN IN</h3>
			<c:if test="${error != null}">
				<div class="alert alert-danger text-center" style="width: fit-content; margin: 0 auto;color: red">
						${error}
				</div>
			</c:if>
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

			<div class="sign-up form-group text-center">
				<p>Not a member? <a href="registration.jsp">
					signup now <i class="fas fa-user-plus"></i></a></p>
			</div>
		</form>
	</div>
</div>
<script>
	function removeQueryParam() {
		const url = window.location.protocol + "//" + window.location.host + window.location.pathname;
		window.history.replaceState({}, document.title, url);
	}
</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
