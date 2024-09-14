<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/addSubCategory.css">
</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="adminDashboard.jsp" class="btn btn-black">Back to Dashboard</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Add New SubCategory</h2>
    </div>

    <form action="addSubCategory" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label for="category">Main Category</label>
            <select class="form-control" id="category" name="category" required>
                <option value="">Select Main Category</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="subcategory">SubCategory Name</label>
            <input type="text" class="form-control" id="subcategory" name="subcategory" placeholder="Enter stock quantity" required>
        </div>


        <button id="addBtn" type="submit" class="btn btn-teal btn-block mt-2">Add SubCategory</button>
    </form>
</div>

</body>
</html>
