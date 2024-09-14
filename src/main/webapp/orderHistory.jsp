<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .order-list {
            cursor: pointer;
        }
        .order-list:hover {
            background-color: #f8f9fa;
        }
        .btn-black {
            background-color: #000;
            color: #fff;
        }
        .btn-black:hover {
            background-color: #444;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Order History</h2>

    <!-- Back Button -->
    <div class="mb-4">
        <a href="adminDashboard.jsp" class="btn btn-black">Back to Dashboard</a>
    </div>

    <!-- Order List -->
    <div class="row">
        <!-- Example order -->
        <c:forEach var="order" items="${orders}">
            <div class="col-md-4 order-list" onclick="showOrderDetails(${order.id})">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Order #${order.id}</h5>
                        <p class="card-text">Customer: ${order.customerName}</p>
                        <p class="card-text">Date: ${order.orderDate}</p>
                        <p class="card-text">Total: $${order.totalAmount}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    function showOrderDetails(orderId) {
        // Redirect to order details page
        window.location.href = 'orderDetails.jsp?orderId=' + orderId;
    }
</script>
</body>
</html>
