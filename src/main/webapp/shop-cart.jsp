<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="favicon.ico">
    <title>CHICLY</title>

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
    <style>
        /*start of Notification Style*/
        .notification {
            position: fixed;
            top: 0;
            width: 100%;
            background-color: #f44336; /* Red background for error */
            color: white;
            text-align: center;
            padding: 15px;
            z-index: 1000; /* Make sure it appears on top */
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 16px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease;
        }

        .notification.hidden {
            transform: translateY(-100%); /* Slide out of view */
        }

        .close-btn {
            margin-left: 20px;
            background: none;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
        }
        /*end of Notification Style*/
        .pro-qtyy {
            display: flex;
            align-items: center;
            justify-content: center;
            width: fit-content;
        }

        .qty-btn {
            background-color: #f1f1f1;
            border: none;
            border-radius: 50%; /* Makes the buttons circular */
            width: 40px; /* Equal width and height for a perfect circle */
            height: 40px;
            font-size: 18px; /* Adjust font size to fit inside the circle */
            font-weight: bold;
            color: black;
            text-align: center;
            line-height: 40px; /* Centers the text vertically */
            cursor: pointer;
            margin: 0 5px;
            transition: background-color 0.3s ease; /* Smooth hover effect */
        }

        .qty-btn:focus {
            outline: none;
        }

        .qty-btn:hover {
            background-color: #e0e0e0; /* Change background on hover */
        }

        .quantity-input {
            width: 50px;
            height: 40px; /* Match the button height for symmetry */
            text-align: center;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
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
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

   <jsp:include page="common/header.jsp"/>
<!--Notification Section -->
    <div id="notification" class="notification hidden">
        <span id="notification-message"></span>
        <button id="close-notification" class="close-btn">&times;</button>
    </div>
    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> Home</a>
                        <span class="order-history-title">Shopping Cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    <c:set var="cart" value="${sessionScope.cart}" />
    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <c:if test="${cart.totalCartItems>0}">
                            <tbody>
                            <c:forEach var="entry" items="${cart.items}">
                                <tr data-product-id="${entry.key.id}">
                                    <td class="cart__product__item">
                                        <img src="${entry.key.imageURL}" style="width: 90px; height: 90px;" alt="">
                                        <div class="cart__product__item__title">
                                            <h6>${entry.key.productName}</h6>

                                        </div>
                                    </td>
                                    <td class="cart__price">$ <span class="price-value">${entry.key.price}</span></td>
                                    <td class="cart__quantity">
                                        <div class="pro-qtyy">
                                            <button class="qty-btn minus-btn">-</button>
                                                <input type="text" value="${entry.value}" class="quantity-input" min="1" max="${entry.key.stock}" data-max="${entry.key.stock}" data-price="${entry.key.price}"  readonly>
                                            <button class="qty-btn plus-btn">+</button>
                                        </div>
                                    </td>
                                    <td class="cart__total">$ <span class="total-value">${entry.key.price}</span></td>
                                    <td class="cart__close"><span class="icon_close"></span></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="filterProducts">Continue Shopping</a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn update__btn">
                        <a href="#"><span class="icon_loading"></span> Update cart</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Cart total</h6>
                        <ul>
                            <li>TotalQuantity <span>${cart.totalQuantity}</span></li>
                            <li>Subtotal <span>$ ${cart.totalPrice}</span></li>
                        </ul>
                        <a href="/checkout" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->


<jsp:include page="common/footer.jsp"/>

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/jquery.countdown.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.nicescroll.min.js"></script>
    <script src="js/main.js"></script>
    <script>
        /*Function Notification*/
        var errorMessage = '<c:out value="${requestScope.errorMessage}" escapeXml="true" />';
        function showNotification(message) {
            const notification = document.getElementById('notification');
            const notificationMessage = document.getElementById('notification-message');

            // Set the message and show the notification
            notificationMessage.textContent = message;
            notification.classList.remove('hidden');

            // Automatically hide after 3 seconds
            setTimeout(() => {
                notification.classList.add('hidden');
            }, 5000);
        }
        /*Function Notification*/

        // Function to calculate and update the subtotal and total quantity
        function updateSubtotalAndQuantity() {
            let subtotal = 0;
            let totalQuantity = 0;

            // Loop through each product row and calculate totals
            document.querySelectorAll('.quantity-input').forEach(input => {
                const quantity = parseInt(input.value);
                const pricePerUnit = parseFloat(input.getAttribute('data-price'));


                subtotal += quantity * pricePerUnit;
                totalQuantity += quantity;
            });

            // Update the subtotal and total quantity in the HTML
            document.querySelector('.cart__total__procced li:nth-child(1) span').textContent = totalQuantity; // Update total quantity
            document.querySelector('.cart__total__procced li:nth-child(2) span').textContent = '$ ' + subtotal.toFixed(2); // Update subtotal
        }

        // Function to handle product removal
        function removeProduct(productRow, productId) {
            const requestData = {
                productId: productId
            };

            // Send AJAX request to servlet to remove product from the cart
            const xhr = new XMLHttpRequest();
            xhr.open('POST', 'cart', true);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const data = JSON.parse(xhr.responseText);
                    if (data.success) {
                        // Remove the product row from the table
                        productRow.remove();

                        // Recalculate and update the cart count, subtotal, and total quantity
                        updateSubtotalAndQuantity();

                        // Optionally update cart count in header (if needed)
                        $('.icon_bag_alt').siblings('.tip').text(data.cartItemCount);
                        saveCart();
                    } else {
                        alert('Failed to remove the product.');
                    }
                }
            };
            xhr.send(JSON.stringify(requestData));
        }
        function saveCart() {
            // Send an AJAX request to get the CartService from the session
            $.ajax({
                url: "/cartlocal",
                type: "GET",
                success: function (response) {
                    // Save the entire CartService object to localStorage
                    localStorage.setItem("cartService", JSON.stringify(response.cart));
                    localStorage.setItem("cartPrevious", JSON.stringify(response.cart));
                    console.log("CartService successfully saved to localStorage.");
                },
                error: function (xhr, status, error) {
                    console.error("Error saving CartService:", error);
                }
            });
        }

        // DOM Content Loaded event
        document.addEventListener('DOMContentLoaded', function () {
            if (errorMessage) {
                showNotification(errorMessage);
            }

            // Function to handle quantity update
            function updateQuantity(input, isIncrement) {
                let quantity = parseInt(input.value);
                const pricePerUnit = parseFloat(input.getAttribute('data-price'));
                const productRow = input.closest('tr'); // Get the closest 'tr' element
                const productId = productRow.getAttribute('data-product-id');
                let maxStock = parseInt(input.getAttribute('data-max'));
                console.log(maxStock);
                // Increment or decrement the quantity
                if (isIncrement) {

                    if (quantity < maxStock) {
                        quantity += 1;
                    }


                } else {
                    quantity = quantity > 1 ? quantity - 1 : 1; // Prevent going below 1
                }

                // Update the input value
                input.value = quantity;
                const requestData = {
                    productId: productId,
                    quantity: quantity
                };
                console.log(requestData);
                // Update the total price for this product row

                const xhr = new XMLHttpRequest();
                xhr.open('POST', 'cartupdate', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        const data = JSON.parse(xhr.responseText);
                        if (data.success) {
                            // Update the total price for this product row
                            const totalPrice = pricePerUnit * quantity;
                            const totalPriceElement = input.closest('tr').querySelector('.total-value');
                            totalPriceElement.textContent = totalPrice.toFixed(2);

                            // Recalculate and update the overall subtotal and total quantity
                            document.querySelector('.cart__total__procced li:nth-child(1) span').textContent = data.totalQuantity;
                            document.querySelector('.cart__total__procced li:nth-child(2) span').textContent = '$ ' + data.totalPrice.toFixed(2);
                        } else {
                            alert('Failed to update the product quantity.');
                        }
                    }
                };
                xhr.send(JSON.stringify(requestData));
                  // Update total for this product


                // Recalculate and update the overall subtotal and total quantity
                updateSubtotalAndQuantity();
            }

            // Get all quantity input fields and their corresponding buttons
            document.querySelectorAll('.quantity-input').forEach(input => {
                // Handle "+" button click
                const plusBtn = input.nextElementSibling;
                plusBtn.addEventListener('click', function () {
                    updateQuantity(input, true); // Increment
                });

                // Handle "-" button click
                const minusBtn = input.previousElementSibling;
                minusBtn.addEventListener('click', function () {
                    updateQuantity(input, false); // Decrement
                });

                // Also listen for manual input changes
                input.addEventListener('input', function () {
                    let quantity = parseInt(input.value);
                    let maxStock = parseInt(input.getAttribute('data-max'));
                    if (isNaN(quantity) || quantity < 1) {
                        quantity = 1;
                        input.value = 1;
                    } else if (quantity > maxStock) {
                        quantity = maxStock; // Cap it to the stock value
                    }

                    const pricePerUnit = parseFloat(input.getAttribute('data-price'));
                    const totalPrice = pricePerUnit * quantity;
                    const totalPriceElement = input.closest('tr').querySelector('.total-value');
                    totalPriceElement.textContent = totalPrice.toFixed(2);

                    updateSubtotalAndQuantity();
                });
            });

            // Get all "remove" buttons
            const removeButtons = document.querySelectorAll('.cart__close span');
            removeButtons.forEach(button => {
                button.addEventListener('click', function () {
                    const productRow = button.closest('tr');
                    const productId = productRow.getAttribute('data-product-id'); // Get productId

                    removeProduct(productRow, productId);
                });
            });
        });

    </script>

</body>

</html>