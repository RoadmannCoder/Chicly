<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">

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
        /* Styling for table headers */
        .table th {
            background-color: #ca1515; /* Example background color */
            color: white; /* Text color */
            font-weight: bold;
            text-align: center;
        }

        /* Ensure that <td> aligns with <th> */
        .table td, .table th {
            text-align: center; /* Align text to center */
            vertical-align: middle; /* Aligns content vertically in the middle */
        }

        /* Table layout to keep structure consistent */
        .table {
            table-layout: auto; /* Ensures column widths are auto-adjusted */
            width: 100%; /* Table takes full width */
        }
        .order-history-title {
            font-family: 'Montserrat', sans-serif; /* Use a nice modern font */
            font-weight: 700; /* Make the text bold */
            font-size: 32px; /* Larger font size */
            color: #ca1515; /* A bold red color matching the theme */
            background-color: #f8f9fa; /* Light background for contrast */
            padding: 10px 20px; /* Add padding for spacing */
            border-radius: 50px; /* Rounded corners */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            display: inline-block; /* Keeps it inline but with block properties */
            text-transform: uppercase; /* Makes the text uppercase */
            letter-spacing: 2px; /* Adds spacing between letters */
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
                    <span class="order-history-title">Order History</span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container mt-5">
    <div class="row">
        <c:if test="${not empty orders}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Created Date</th>
                    <th>Destination</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.createdAt}</td>
                        <td>${order.destination}</td>
                        <td>${order.status}</td>
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

<script>
</script>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
