<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Products - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/viewProducts.css">
</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="adminDashboard.jsp" class="btn btn-black">Back to Dashboard</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Manage Products</h2>
    </div>

    <div class="row">
        <!-- Sidebar for Filters -->
        <div class="col-md-3">
            <h4>Filter Products</h4>
            <form id="filterForm">
                <div class="form-group">
                    <label for="subcategory">Subcategory</label>
                    <select class="form-control" id="subcategory" name="subcategory">
                        <option value="">Select Subcategory</option>
                        <c:forEach var="subcategory" items="${sessionScope.subcategories}">
                            <option value="${subcategory.name}">${subcategory.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="button" id="filterBtn" class="btn btn-black btn-block" onclick="filterProducts()">Apply Filters</button>
                <button type="button" class="btn btn-black btn-block" onclick="addNewProduct()">Add New Product</button>

            </form>
        </div>

        <!-- Main Content -->
        <div class="col-md-9">
            <div class="row mb-12">
                <div class="col-md-9">
                    <input type="text" id="searchId" name="searchId"class="form-control" placeholder="Search by Product ID">
                </div>
                <div class="col-md-3">
                    <button class="btn btn-black btn-block" onclick="resetFilters()">Reset</button>
                </div>
                <div class="col-md-12">
                    <button id="searchBtn" class="btn btn-teal btn-block mt-2" onclick="searchProducts()">Search</button>
                </div>

            </div>

            <!-- Product List -->
            <div class="row">
                <c:if test="${empty subProducts}">
                    <div class="col-md-12">
                        <p class="text-center text-muted">No products found.</p>
                    </div>
                </c:if>

                <c:forEach var="subProduct" items="${subProducts}">
                    <div class="col-md-4 product-list">
                        <div class="card mb-4">
                            <img src="${subProduct.imageURL}" class="card-img-top" alt="${subProduct.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${subProduct.productName}</h5>
                                <p class="card-text">Price: $${subProduct.price}</p>
                                <p class="card-text">Colour: ${subProduct.color}</p>
                                <p class="card-text">Size: ${subProduct.size}</p>
                                <p class="card-text">Quantity: ${subProduct.stock}</p>
                                <a href="updateSubProduct?subproduct_Id=${subProduct.id}" class="btn btn-black">Update</a>
                                <a href="deleteSubProduct?subproduct_Id=${subProduct.id}" class="btn btn-red">Delete</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script>
    function searchProducts() {
        const id = document.getElementById('searchId').value;
        var searchBtn = document.getElementById("searchBtn");
        if (id==="") {
            searchBtn.disabled = true;
        }else{
            searchBtn.disabled = false;
            window.location.href = 'productView?searchId=' + encodeURIComponent(id);
        }

    }

    function filterProducts() {
        const subcategory = document.getElementById('subcategory').value;
        var searchBtn = document.getElementById("searchBtn");
        if(subcategory ===""){
            searchBtn.disabled = true;
        }else{
            window.location.href = 'productView?subcategory=' + encodeURIComponent(subcategory);
            searchBtn.disabled = false;
        }

    }
    function addNewProduct() {
        window.location.href = 'addSubProduct';
    }
    function resetFilters() {
        // Redirect to the servlet to refresh the customer list
        window.location.href = 'productView';
    }

</script>
</body>
</html>