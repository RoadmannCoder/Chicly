<%@ page import="java.math.BigDecimal, java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/registration.css">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <title>Chicly Registration Form</title>
</head>
<body>
<%--<jsp:useBean id="customerBean" class="com.chickly.DTO.CustomerBean" scope="request" />--%>
<%--<jsp:setProperty name="customerBean" property="*" />--%>

<div class="container">
    <header><img src="img/logo.png" width="98" height="31" alt=""></header>
    <form method="POST" action="register" id="registeration-form">
        <div class="form first">
            <div class="details personal">
                <div class="fields">
                    <div class="input-field">
                        <label>First Name</label>
                        <input type="text" id="firstName" name="firstName" required>
                    </div>
                    <div class="input-field">
                        <label>Last Name</label>
                        <input type="text" id="lastName" name="lastName" required>
                    </div>
                    <div class="input-field">
                        <label>Credit Limit</label>
                        <input type="number" id="creditLimit" name="creditLimit" onblur="checkCreditLimit();" required>
                        <span class="error-message" id="crediterror"></span>
                    </div>
                    <div class="input-field">
                        <label>Date of Birth</label>
                        <input type="date" id="dateOfBirth" name="dateOfBirth" required>
                    </div>
                    <div class="input-field">
                        <label>Email</label>
                        <input type="text" id="email" name="email" onblur="checkEmail();" required>
                        <span class="error-message" id="emailerror"></span>
                    </div>
                    <div class="input-field">
                        <label>Phone Number</label>
                        <input type="number" id="phoneNumber" name="phoneNumber" onblur="checkPhoneNumber();" required>
                        <span class="error-message" id="phoneerror"></span>
                    </div>
                </div>
            </div>
            <div class="details ID">
                <div class="fields">

                    <div class="input-field">
                        <label>Username</label>
                        <input type="text" id="userName" name="userName" onblur="checkUserName();"required>
                        <span class="error-message" id="usernameerror"></span>
                    </div>
                    <div class="input-field">
                        <label>Password</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="input-field">
                        <label>Job</label>
                        <input type="text" id="job" name="job" required>
                    </div>
                    <div class="input-field">
                        <label>Street</label>
                        <input type="text" id="street" name="street" required>
                    </div>
                    <div class="input-field">
                        <label>City</label>
                        <input type="text" id="city" name="city" required>
                    </div>
                    <div class="input-field">
                        <label>Description</label>
                        <input type="text" id="description" name="description" required>
                    </div>
                    <div class="input-field">
                        <label>Zip Code</label>
                        <input type="number" id="zip" name="zip" required>
                    </div>

                    <button class="nextBtn" type="submit" id="registerBtn" onmouseover="checkCondition()">
                        <span class="btnText">Register</span>
                        <i class="uil uil-navigator"></i>
                    </button>

                </div>
            </div>
        </div>
    </form>

</div>

<script src="js/register.js"></script>
</body>
</html>
