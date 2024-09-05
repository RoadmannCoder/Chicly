<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>

<form action="FrontController" method="post">
    <input type="hidden" name="controller" value="login">

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <button type="submit">Login</button>
</form>

<p style="color: red;">
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <%= errorMessage != null ? errorMessage : "" %>
</p>
</body>
</html>
