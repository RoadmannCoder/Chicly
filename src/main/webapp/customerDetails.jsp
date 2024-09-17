<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/customerDetails.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="customerView" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Profiles</a>
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
                <th>Status</th>
                <th>Created Date</th>
                <th>Destination</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>
                        <select id="status-${order.id}" class="form-control">
                            <option value="PENDING" ${order.status == 'PENDING' ? 'selected' : ''}>PENDING</option>
                            <option value="WAITING" ${order.status == 'WAITING' ? 'selected' : ''}>WAITING</option>
                            <option value="COMPLETED" ${order.status == 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                            <option value="CANCELLED" ${order.status == 'CANCELLED' ? 'selected' : ''}>CANCELLED</option>
                        </select>
                    </td>
                    <td>${order.createdAt}</td>
                    <td>${order.destination}</td>
                    <td>
                        <button class="btn btn-primary" onclick="updateStatus(${order.id})">Update</button>
                        <button class="btn btn-secondary" onclick="viewOrderDetails(${order.id})">View Order Details</button>
                    </td>
                </tr>
                <tr id="order-items-${order.id}" style="display: none;">
                    <td colspan="5">
                        <div class="order-items-container">
                            <h5>Order Items for Order ${order.id}</h5>
                            <ul id="items-list-${order.id}">
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty orders}">
        <p>No orders found for this customer.</p>
    </c:if>
</div>

<script>
    function updateStatus(orderId) {
        // Get the selected value from the dropdown for the given order ID
        var newStatus = document.getElementById('status-' + orderId).value;

        $.ajax({
            url: 'OrderStatusController',
            type: 'POST',
            data: {
                orderId: orderId,
                status: newStatus
            },
            success: function(response) {
                alert("Order status updated successfully");
            },
            error: function(xhr, status, error) {
                alert("Failed to update order status");
            }
        });
    }
    function viewOrderDetails(orderId) {
        var itemsBlock = document.getElementById('order-items-' + orderId);
        var itemsList = document.getElementById('items-list-' + orderId);

        if (itemsBlock.style.display === "none") {
            $.ajax({
                url: 'OrderItemsController',
                type: 'GET',
                data: { orderId: orderId },
                success: function(response) {
                    itemsList.innerHTML = '';
                    if (response.length > 0) {
                        response.forEach(function(item) {
                            var listItem = '<li>' +
                                'Product: ' + item.productName +
                                ' | Quantity: ' + item.quantity +
                                ' | Price: ' + item.price +
                                '</li>';
                            itemsList.innerHTML += listItem;
                        });
                    } else {
                        itemsList.innerHTML = '<li>No items found for this order.</li>';
                    }
                    itemsBlock.style.display = "table-row";
                },
                error: function(xhr, status, error) {
                    alert("Failed to load order items");
                }
            });
        } else {
            itemsBlock.style.display = "none";
        }
    }


</script>

</body>
</html>
