<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/addProduct.css">
</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="productView" class="btn btn-black">Back to Products</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Add New Product</h2>
    </div>

    <form action="addProduct" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="mainProduct">Main Product</label>
            <select class="form-control" id="mainProduct" name="mainProduct" required>
                <option value="">Select Main Product</option>
                <c:forEach var="mainProduct" items="${mainProducts}">
                    <option value="${mainProduct.id}">${mainProduct.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="color">Color</label>
            <select class="form-control" id="color" name="color" required>
                <option value="">Select Color</option>
                <c:forEach var="color" items="${colors}">
                    <option value="${color.name()}">${color.name()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="size">Size</label>
            <select class="form-control" id="size" name="size" required>
                <option value="">Select Size</option>
                <c:forEach var="size" items="${sizes}">
                    <option value="${size.name()}">${size.name()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="quantity">Stock</label>
            <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Enter stock quantity" onblur="checkQuantity();" required>
            <span class="error-message" id="quantityerror"></span>

        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" class="form-control" id="price" name="price" placeholder="Enter price" step="0.01" onblur="checkPrice();" required>
            <span class="error-message" id="priceerror"></span>

        </div>

        <div class="form-group">
            <label for="image">Product Image</label>
            <input type="file" class="form-control-file" id="image" name="image" accept="image/*" onchange="previewImage(event)" required>
            <img id="imagePreview" src="" alt="Image Preview" style="display:none; margin-top:10px; max-width: 200px;">
        </div>

        <button id="addBtn" type="submit" class="btn btn-teal btn-block mt-2">Add Product</button>
    </form>
</div>
<script src="admin-dashboard/js/addProduct.js"></script>

</body>
</html>
