<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>

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
    </style>

</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

   <jsp:include page="common/header.jsp"/>

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="index.jsp"><i class="fa fa-home"></i> Home</a>
                        <span>Shopping cart</span>
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
                        <a href="#" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->

    <!-- Instagram Begin -->
    <div class="instagram">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-1.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-2.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-3.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-4.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-5.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-4 p-0">
                    <div class="instagram__item set-bg" data-setbg="img/instagram/insta-6.jpg">
                        <div class="instagram__text">
                            <i class="fa fa-instagram"></i>
                            <a href="#">@ ashion_shop</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Instagram End -->
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
                    console.log("CartService successfully saved to localStorage.");
                },
                error: function (xhr, status, error) {
                    console.error("Error saving CartService:", error);
                }
            });
        }

        // DOM Content Loaded event
        document.addEventListener('DOMContentLoaded', function () {

            // Function to handle quantity update
            function updateQuantity(input, isIncrement) {
                let quantity = parseInt(input.value);
                const pricePerUnit = parseFloat(input.getAttribute('data-price'));
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

                // Update the total price for this product row
                const totalPrice = pricePerUnit * quantity;
                const totalPriceElement = input.closest('tr').querySelector('.total-value');
                totalPriceElement.textContent = totalPrice.toFixed(2);  // Update total for this product

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