<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
        .dropdown-checkbox {
            position: relative;
        }
        .dropdown-menu-checkbox {
            display: none;
            position: absolute;
            z-index: 1000;
            width: 100%;
            background-color: #fff;
            box-shadow: 0 0.25rem 0.75rem rgba(0, 0, 0, 0.1);
            padding: 10px;
            max-height: 200px;
            overflow-y: auto;
        }
        .dropdown-checkbox.open .dropdown-menu-checkbox {
            display: block;
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
        .form-group {
            margin-bottom: 15px;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
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
                    <a href="userProfile.jsp"><i class="fa icon_profile"></i> User Profile</a>
                    <span class="order-history-title">Update Profile</span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container mt-5">
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
    <form method="POST" action="updatecustomer">
        <div class="card">
            <div class="card-body">
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
                <div class="form-group dropdown-checkbox">
                    <label for="interestsDropdown">Interests:</label>
                    <button class="form-control" id="interestsDropdown" type="button" onclick="toggleDropdown()">Select Interests</button>
                    <div class="dropdown-menu-checkbox" id="dropdownMenuCheckbox">
                        <c:forEach var="interest" items="${availableInterests}">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="interest${interest.id}" name="interests" value="${interest.id}"
                                <c:forEach var="interestItem" items="${sessionScope.user.interests}">
                                <c:if test="${interestItem.id == interest.id}">
                                       checked
                                </c:if>
                                </c:forEach>>
                                <label class="form-check-label" for="interest${interest.id}">
                                        ${interest.name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
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
                    <label for="job">Job:</label>
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
        <br>
        <button id="updateBtn" type="submit"  class="site-btn" onmouseover="checkCondition()">
            <i class="fas fa-edit"></i> Update profile
        </button>
        <p data-email="${sessionScope.user.email}" id="emailSession" hidden="hidden"></p>
        <p data-phone="${sessionScope.user.phoneNumber}" id="phoneNumberSession" hidden="hidden"></p>
        <p data-username="${sessionScope.user.account.userName}" id="userNameSession" hidden="hidden"></p>
        <p data-city="${sessionScope.user.address.city}" id="citySession" hidden="hidden"></p>
    </form>


</div>
<script src="js/updateProfile.js"></script>
<script>
    function toggleDropdown() {
        document.querySelector('.dropdown-checkbox').classList.toggle('open');
    }

    // Close the dropdown if clicked outside of it
    document.addEventListener('click', function(event) {
        var isClickInside = document.querySelector('.dropdown-checkbox').contains(event.target);
        if (!isClickInside) {
            document.querySelector('.dropdown-checkbox').classList.remove('open');
        }
    });
    function removeQueryParam() {
        const url = window.location.protocol + "//" + window.location.host + window.location.pathname;
        window.history.replaceState({}, document.title, url);
    }
</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
