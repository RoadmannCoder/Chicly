<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/customerDetails.css">
</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="customerView" class="btn btn-black">Back to Profiles</a>
    </div>

    <!-- Header Container -->
    <div class="header-container">
        <h2 class="header-font">Customer Details</h2>
    </div>

    <!-- Customer Details -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">${customer.firstName} ${customer.lastName}</h5>
            <p class="card-text"><strong>Username:</strong> ${customer.userName}</p>
            <p class="card-text"><strong>Email:</strong> ${customer.email}</p>
            <p class="card-text"><strong>Phone:</strong> ${customer.phoneNumber}</p>
            <p class="card-text"><strong>Address:</strong> ${customer.street}, ${customer.city}, ${customer.zip}</p>
            <p class="card-text"><strong>Description:</strong> ${customer.description}</p>
        </div>
    </div>

    <!-- Orders List -->
    <h3 class="mb-4">Order History</h3>
    <c:if test="${not empty orders}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Product</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.productName}</td>
                    <td>${order.quantity}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.orderDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty orders}">
        <p>No orders found for this customer.</p>
    </c:if>
</div>

</body>
</html>
