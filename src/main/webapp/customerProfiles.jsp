<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Profiles - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/customerProfiles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="adminDashboard.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Dashboard</a>
    </div>
    <!-- Header Container -->
    <div class="header-container">
        <h2 class="header-font">Customer Profiles</h2>
    </div>

    <!-- Search and Filter Form -->
    <div class="row mb-4">
        <div class="col-md-3">
            <input type="text" id="searchUsername" class="form-control mb-2" placeholder="Search by Username">
        </div>
        <div class="col-md-3">
            <input type="text" id="searchEmail" class="form-control mb-2" placeholder="Search by Email">
        </div>
        <div class="col-md-3">
            <input type="text" id="searchId" class="form-control mb-2" placeholder="Search by ID">
        </div>
        <div class="col-md-3">
            <button class="btn btn-black btn-block" onclick="resetFilters()">
                <i class="fas fa-redo"></i> Reset
            </button>
        </div>
    </div>
    <div class="row mb-4">
        <div class="col-md-12">
            <button class="btn btn-teal btn-block" onclick="searchCustomers()">
                <i class="fas fa-search"></i> Search
            </button>
        </div>
    </div>

    <!-- Customer List -->
    <div class="row">
        <c:if test="${empty customers}">
            <div class="col-md-12">
                <p class="text-center text-muted">No customer profiles found.</p>
            </div>
        </c:if>

        <c:forEach var="customer" items="${customers}">
            <div class="col-md-4 customer-list">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">${customer.firstName} ${customer.lastName}</h5>
                        <p class="card-text">Username: ${customer.userName}</p>
                        <p class="card-text">Email: ${customer.email}</p>
                        <p class="card-text">Phone: ${customer.phoneNumber}</p>
                        <a href="customerDetails?userName=${customer.userName}" class="btn btn-black">
                            <i class="fas fa-eye"></i> View Details</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    function searchCustomers() {
        const username = document.getElementById('searchUsername').value;
        const email = document.getElementById('searchEmail').value;
        const id = document.getElementById('searchId').value;

        let queryString = '';
        if (username) queryString += 'username=' + encodeURIComponent(username) + '&';
        if (email) queryString += 'email=' + encodeURIComponent(email) + '&';
        if (id) queryString += 'id=' + encodeURIComponent(id) + '&';

        window.location.href = 'customerView?userName='+username+'&email='+email+'&Id='+id;
    }

    function resetFilters() {
        // Redirect to the servlet to refresh the customer list
        window.location.href = 'customerView';
    }
</script>
</body>
</html>
