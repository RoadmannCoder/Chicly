<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/addCategory.css">
</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="adminDashboard.jsp" class="btn btn-black">Back to Dashboard</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Add New Category</h2>
    </div>
    <form action="addCategory" method="post" enctype="multipart/form-data">


        <div class="form-group">
            <label for="categoryname">Category</label>
            <input type="text" class="form-control" id="categoryname" name="categoryname" placeholder="Enter category name"  required>

        </div>

        <button id="addBtn" type="submit" class="btn btn-teal btn-block mt-2">Add Category</button>
    </form>
</div>

</body>
</html>
