<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Google Font -->
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
    <link rel="stylesheet" href="css/shop.css" type="text/css">
    <style>
        body {
            background-color: #f9f9f9;
        }
        .card {
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        .card-body {
            padding: 20px;
        }
        .card-title {
            font-size: 24px;
            color: #333;
        }
        p {
            font-size: 18px;
        }
        .site-btn {
             font-size: 14px;
             color: #ffffff;
             background: #ca1515;
             font-weight: 600;
             border: none;
             text-transform: uppercase;
             display: inline-block;
             padding: 12px 30px;
             border-radius: 50px;
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
<jsp:include page="common/header.jsp" />
<body>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="/"><i class="fa fa-home"></i> Home</a>
                    <span class="order-history-title">User Profile</span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">User Information</h4>
            <p><strong>Username:</strong> ${sessionScope.user.account.userName}</p>
            <p><strong>First Name:</strong> ${sessionScope.user.firstName}</p>
            <p><strong>Last Name:</strong> ${sessionScope.user.lastName}</p>
            <p><strong>Credit Limit:</strong> ${sessionScope.user.creditLimit}</p>
            <p><strong>Email:</strong> ${sessionScope.user.email}</p>
            <p><strong>Phone Number:</strong> ${sessionScope.user.phoneNumber}</p>
            <p><strong>Job:</strong> ${sessionScope.user.job}</p>
            <p><strong>Address:</strong> ${sessionScope.user.address.street}, ${sessionScope.user.address.city}, ${sessionScope.user.address.zip}, ${sessionScope.user.address.description}</p>
        </div>
    </div>

    <!-- Update Button -->
    <div class="mt-4">
        <a href="updatecustomer" class="site-btn">
            <i class="fas fa-edit"></i> Update Profile</a>
    </div>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
