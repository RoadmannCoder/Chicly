<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <style>
        .btn-black {
            background-color: #000;
            color: #fff;
        }

        .btn-black:hover {
            background-color: #444;
            color: #fff;
        }

        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }

        .order-history-title {
            font-family: 'Montserrat', sans-serif;
            font-weight: 700;
            font-size: 32px;
            color: #ca1515;
            background-color: #f8f9fa;
            padding: 10px 20px;
            border-radius: 50px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            display: inline-block;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        /* Modal Styles */
        .modal-body {
            max-height: 400px;
            overflow-y: auto;
        }

        .btn-cancelled {
            background-color: #6c757d;
            cursor: not-allowed;
        }

        .btn-cancelled:hover {
            background-color: #6c757d;
        }
    </style>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="/"><i class="fa fa-home"></i> Home</a>
                    <span class="order-history-title">Order history</span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container mt-5">
    <div class="row mt-4">
        <c:if test="${not empty orders}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Created Date</th>
                    <th>Destination</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.createdAt}</td>
                        <td>${order.destination}</td>
                        <td>${order.status}</td>
                        <td>
                            <a href="OrderTrackingController?orderId=${order.id}" class="btn btn-primary">View Order Details</a>
                            <c:choose>
                                <c:when test="${order.status == 'CANCELLED'}">
                                    <button class="btn btn-cancelled" disabled>Cancelled</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-danger cancel-order" data-order-id="${order.id}">Cancel Order</button>
                                </c:otherwise>
                            </c:choose>
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
</div>

<div class="modal fade" id="orderDetailsModal" tabindex="-1" role="dialog" aria-labelledby="orderDetailsModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="orderDetailsModalLabel">Order Items</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <tbody id="order-items-body">
                    <!-- Order items will be injected here -->
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Cancellation Confirmation Modal -->
<div class="modal fade" id="cancelOrderModal" tabindex="-1" role="dialog" aria-labelledby="cancelOrderModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cancelOrderModalLabel">Confirm Cancellation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to cancel this order? This action cannot be undone.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" id="confirm-cancel">Confirm</button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

<script>
    $(document).ready(function () {
        var orderIdToCancel = null;

        // View order details (order items)
        $('.view-details').on('click', function () {
            var orderId = $(this).data('order-id');
            $.ajax({
                url: 'OrderItemsController',
                type: 'GET',
                data: { orderId: orderId },
                success: function (response) {
                    var orderItemsBody = $('#order-items-body');
                    orderItemsBody.empty();
                    if (response.length > 0) {
                        response.forEach(function (item) {
                            orderItemsBody.append('<tr>' +
                                '<td>' + item.productName + '</td>' +
                                '<td>' + item.quantity + '</td>' +
                                '<td>' + item.price + '</td>' +
                                '</tr>');
                        });
                    } else {
                        orderItemsBody.append('<tr><td colspan="3">No items found for this order.</td></tr>');
                    }
                    $('#orderDetailsModal').modal('show');
                },
                error: function () {
                    alert('Failed to fetch order details.');
                }
            });
        });

        // Trigger cancel order confirmation modal
        $('.cancel-order').on('click', function () {
            orderIdToCancel = $(this).data('order-id');
            $('#cancelOrderModal').modal('show');
        });

        // Confirm cancel order
        $('#confirm-cancel').on('click', function () {
            if (orderIdToCancel) {
                $.ajax({
                    url: 'OrderStatusController',
                    type: 'POST',
                    data: {
                        orderId: orderIdToCancel,
                        status: 'CANCELLED'
                    },
                    success: function () {
                        alert('Order canceled successfully.');
                        location.reload();
                    },
                    error: function () {
                        alert('Failed to cancel the order.');
                    }
                });
                $('#cancelOrderModal').modal('hide');
            }
        });
    });
</script>

<jsp:include page="common/footer.jsp"/>
</body>
</html>