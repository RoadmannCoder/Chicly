<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/adminLogin.css"> <!-- Custom CSS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="card p-4 shadow-lg" style="width: 400px;">
        <div class="text-center">
            <img src="img/logo.png" alt="Chicly Logo" class="mb-4" style="width: 100px;">
        </div>
        <h3 class="text-center mb-4">Admin Login</h3>
        <form action="adminlogincontroller" method="post">
            <c:if test="${error != null}">
                <div class="alert alert-danger text-center" style="width: fit-content; margin: 0 auto;color: red">
                        ${error}
                </div>
            </c:if>
            <div class="form-group mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required>
            </div>
            <div class="form-group mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-dark btn-block">
                    <i class="fas fa-sign-in-alt"></i> Login
                </button>
            </div>
        </form>
    </div>
</div>

<script >

</script>
</body>
</html>
