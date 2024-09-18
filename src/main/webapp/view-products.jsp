<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Products - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/viewProducts.css">
</head>
<body>
<div class="container mt-5">
    <div class="mb-4">
        <a href="AdminDashBoardController" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Dashboard</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Manage Products</h2>
    </div>
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
    <div class="row">
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
                <button type="button" id="filterBtn" class="btn btn-black btn-block" onclick="filterProducts()">
                    <i class="fas fa-filter"></i> Apply Filters</button>
                <button type="button" class="btn btn-teal btn-block mt-2" onclick="addNewProduct()">
                    <i class="fas fa-plus"></i> Add New Product</button>

            </form>
        </div>

        <div class="col-md-9">
            <div class="row mb-12">
                <div class="col-md-9">
                    <input type="text" id="searchId" name="searchId"class="form-control" placeholder="Search by Product ID">
                </div>
                <div class="col-md-3">
                    <button class="btn btn-black btn-block" onclick="resetFilters()">
                        <i class="fas fa-redo"></i> Reset</button>
                </div>
                <div class="col-md-12">
                    <button id="searchBtn" class="btn btn-teal btn-block mt-2" onclick="searchProducts()">
                        <i class="fas fa-search"></i> Search</button>
                </div>

            </div>

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
                                <a href="updateSubProduct?subproduct_Id=${subProduct.id}" class="btn btn-black">
                                    <i class="fas fa-edit"></i> Update
                                </a>
                                <a href="deleteSubProduct?subproduct_Id=${subProduct.id}" class="btn btn-red">
                                    <i class="fas fa-trash"></i> Delete
                                </a>
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
        window.location.href = 'addsubProduct';
    }
    function resetFilters() {
        window.location.href = 'productView';
    }
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