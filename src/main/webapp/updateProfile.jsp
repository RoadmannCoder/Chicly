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
        .form-group {
            margin-bottom: 15px;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>
</head>
<jsp:include page="common/header.jsp" />
<body>
<div class="container mt-5">
    <h2 class="mb-4">User Profile</h2>
    <form method="POST" action="updatecustomer">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">User Information</h4>
                <div class="form-group">
                    <label for="userName">Username:</label>
                    <input type="text" class="form-control" id="userName" name="userName" onblur="checkUserName();" value="${sessionScope.user.account.userName}">
                    <span class="error-message" id="usernameerror"></span>
                </div>
                <div class="card-text">
                    <label for="firstName">First Name:</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="${sessionScope.user.firstName}">
                </div>
                <div class="card-text">
                    <label for="lastName">Last Name:</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${sessionScope.user.lastName}">
                </div>
                <div class="card-text">
                    <label for="creditLimit">Credit Limit:</label>
                    <input type="number" class="form-control" id="creditLimit" name="creditLimit" step="0.01" value="${sessionScope.user.creditLimit}" onblur="checkCreditLimit();">
                    <span class="error-message" id="crediterror"></span>
                </div>
                <div class="card-text">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" id="email" name="email"  onblur="checkEmail();" value="${sessionScope.user.email}">
                    <span class="error-message" id="emailerror"></span>
                </div>
                <div class="card-text">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" onblur="checkPhoneNumber();" value="${sessionScope.user.phoneNumber}">
                    <span class="error-message" id="phoneerror"></span>
                </div>
                <div class="card-text">
                    <label for="job">Phone Number:</label>
                    <input type="text" class="form-control" id="job" name="job" value="${sessionScope.user.job}">
                </div>
                <div class="input-field">
                    <label for="city">City:</label>
                    <select id="city" name="city" class="form-control" value="${sessionScope.user.address.city}">
                    </select>
                </div>
                <div class="card-text">
                    <label for="street">Street:</label>
                    <input type="text" class="form-control" id="street" name="street" value="${sessionScope.user.address.street}">
                </div>
                <div class="card-text">
                    <label for="zip">Zip:</label>
                    <input type="text" class="form-control" id="zip" name="zip" value="${sessionScope.user.address.zip}">
                </div>
                <div class="card-text">
                    <label for="description">Description:</label>
                    <input type="text" class="form-control" id="description" name="description" value="${sessionScope.user.address.description}">
                </div>
            </div>
        </div>
        <button id="updateBtn" type="submit"  class="btn btn-primary" onmouseover="checkCondition()">
            <i class="fas fa-edit"></i> Update profile
        </button>
        <p data-email="${sessionScope.user.email}" id="emailSession" hidden="hidden"></p>
        <p data-phone="${sessionScope.user.phoneNumber}" id="phoneNumberSession" hidden="hidden"></p>
        <p data-username="${sessionScope.user.account.userName}" id="userNameSession" hidden="hidden"></p>
        <p data-city="${sessionScope.user.address.city}" id="citySession" hidden="hidden"></p>
    </form>


</div>
<script src="js/updateProfile.js"></script>

</body>
<jsp:include page="common/footer.jsp"/>
</html>
