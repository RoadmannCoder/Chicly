<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

<div class="login-container">
    <!-- Replace with your actual logo -->
    <img src="img/logo.png" alt="Project Logo">
    <h2>Welcome Back!</h2>
    <!-- Error Message Display -->
    <p class="error-message">
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <%= errorMessage != null ? errorMessage : "" %>
    </p>
    <form action="login" method="post">
        <input type="text" id="username" name="username" placeholder="Enter Username" required>

        <input type="password" id="password" name="password" placeholder="Enter Password" required>

        <input type="submit" value="Login">
    </form>
    <button onclick="window.location.href='register.jsp'">Register</button>
</div>

</body>
</html>
