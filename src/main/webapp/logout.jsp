<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <script type="text/javascript">
        // Clear localStorage on logout
        localStorage.clear();
        localStorage.removeItem("cartService");

        // Optionally, you can also clear sessionStorage if used
        sessionStorage.clear();

        // Redirect the user to the homepage or login page
        window.location.href = "/";
    </script>
</head>
<body>

</body>
</html>
