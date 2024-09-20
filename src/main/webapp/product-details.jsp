<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="favicon.ico">
    <title>CHCILY</title>

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
        .notification {
            position: fixed;
            top: 0;
            width: 100%;
            background-color: #2df800; /* Red background for error */
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
        .pro-qtyy {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 120px;
            border: 1px solid #ddd;
            border-radius: 30px;
            overflow: hidden;
            background-color: #f9f9f9;
        }

        .pro-qtyy input {
            width: 50px;
            text-align: center;
            border: none;
            font-size: 16px;
            background-color: transparent;
            color: #333;
            font-weight: bold;
        }

        .qtybtn {
            background-color: #fff;
            border: none;
            cursor: pointer;
            padding: 8px 15px;
            font-size: 18px;
            font-weight: bold;
            user-select: none;
            transition: all 0.3s ease;
            color: #666;
        }

        .qtybtn:hover {
            background-color: #007bff;
            color: #fff;
        }

        .qtybtn:active {
            transform: scale(0.95);
        }

        .qtybtn.dec {
            border-radius: 50px 0 0 50px;
        }

        .qtybtn.inc {
            border-radius: 0 50px 50px 0;
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
                        <a href="/"><i class="fa fa-home"></i> Home</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    <div id="notification" class="notification hidden">
        <span id="notification-message"></span>
        <button id="close-notification" class="close-btn">&times;</button>
    </div>


    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__left product__thumb nice-scroll">

                            <a class="pt" href="#product-2">
                                <img src="${product.imageURL}" alt="">
                            </a>
                        </div>
                        <div class="product__details__slider__content">
                            <div class="product__details__pic__slider owl-carousel">
                                <img data-hash="product-1" class="product__big__img" src="${product.imageURL}" alt="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="product__details__text">
                        <h3>${product.productName}</h3>
                        <div class="product__details__price">$ ${product.price} </div>
                        <p>${product.description}</p>
                        <div class="product__details__button">
                            <div class="quantity">
                                <span>Quantity:</span>
                                <div class="pro-qtyy">
                                    <button type="button" class="qtybtn dec">-</button>
                                    <input type="text" id="quantity" value="1" min="1" max="${product.stock}" readonly>
                                    <button type="button" class="qtybtn inc">+</button>
                                </div>
                            </div>
                            <a class="cart-btn" onclick="addToCart(${product.id}, '${product.productName}', '${product.imageURL}', ${product.price}, ${product.stock})"><span class="icon_bag_alt"></span> Add to cart</a>
<%--                            <ul>--%>
<%--                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>--%>
<%--                                <li><a href="#"><span class="icon_adjust-horiz"></span></a></li>--%>
<%--                            </ul>--%>
                        </div>
                        <div class="product__details__widget">
                            <ul>
                                <li>
                                    <span>Availability:</span>
                                    <div class="stock__checkbox">
                                             ${product.stock}  In Stock
<%--                                            <input type="checkbox" id="stockin">--%>
<%--                                            <span class="checkmark"></span>--%>
                                    </div>
                                </li>
<%--                                <li>--%>
<%--                                    <span>Available color:</span>--%>
<%--                                    <div class="color__checkbox">--%>
<%--                                        <label for="red">--%>
<%--                                            <input type="radio" name="color__radio" id="red" checked>--%>
<%--                                            <span class="checkmark"></span>--%>
<%--                                        </label>--%>
<%--                                        <label for="black">--%>
<%--                                            <input type="radio" name="color__radio" id="black">--%>
<%--                                            <span class="checkmark black-bg"></span>--%>
<%--                                        </label>--%>
<%--                                        <label for="grey">--%>
<%--                                            <input type="radio" name="color__radio" id="grey">--%>
<%--                                            <span class="checkmark grey-bg"></span>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </li>--%>

                                <li>
                                    <span>Available Color:</span>
                                    <div class="size__btn">
                                        <label for="xs-btn" class="active">
                                            <input type="radio" id="xs-btn">
                                            ${product.color}
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <span>Available size:</span>
                                    <div class="size__btn">
                                        <label for="xs-btn" class="active">
                                            <input type="radio" id="xs-btn">
                                            ${product.size}
                                        </label>
                                    </div>
                                </li>
<%--                                <li>--%>
<%--                                    <span>Promotions:</span>--%>
<%--                                    <p>Free shipping</p>--%>
<%--                                </li>--%>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Description</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <h6>Description</h6>
                                <p>${product.description}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="related__title">
                        <h5>RELATED PRODUCTS</h5>
                    </div>
                </div>
<%--                relatedProducts--%>
                <c:forEach var="relatedProduct" items="${relatedProducts}">
                    <c:if test="${relatedProduct.id != product.id}">
                    <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="${relatedProduct.imageURL}">
                            <ul class="product__hover">
                                <li><a href="${relatedProduct.imageURL}" class="image-popup"><span class="arrow_expand"></span></a></li>
<%--                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>--%>
                                <li><a class="buttonAddToCart" data-id="${relatedProduct.id}" data-name="${relatedProduct.productName}" data-price="${relatedProduct.price}" data-image="${relatedProduct.imageURL}" data-stock="${relatedProduct.stock}"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="/product-details?product=${relatedProduct.id}">${relatedProduct.productName}</a></h6>
                            <div class="product__price">$ ${relatedProduct.price}</div>
                        </div>
                    </div>
                </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->


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
        var user = '<c:out value="${sessionScope.user}" escapeXml="true" />';
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
        $(document).ready(function () {
            // Event listener for all Add to Cart buttons
            $(".buttonAddToCart").click(function (e) {
                e.preventDefault(); // Prevent the default action of the anchor tag

                // Get product details from data attributes
                const productId = $(this).data("id");
                const productName = $(this).data("name");
                const productPrice = $(this).data("price");
                const productImage = $(this).data("image");
                const productStock = $(this).data("stock");

                // Create an object to send to the server
                const productData = {
                    id: productId,
                    productName: productName,
                    price: productPrice,
                    imageURL: productImage,
                    stock: productStock,
                    quantity: 1 // Default to 1, or you can let the user input the quantity
                };

                // Send product details via Ajax to the backend (Servlet)
                $.ajax({
                    url: '/filterProducts', // URL of the servlet that handles adding to cart
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(productData),
                    success: function (response) {
                        $('#notification')
                            .removeClass('alert-danger')
                            .addClass('alert-success')
                            .text(response.message)
                            .fadeIn().delay(3000).fadeOut();
                        // Optionally, update the cart UI with the updated cart count
                        $('.icon_bag_alt').siblings('.tip').text(response.cartItemCount);
                        saveCart();
                        showNotification("Product Added To Cart");
                        // Optionally, update the cart UI or display cart details
                        // Example: $('#cart-count').text(response.cartItemCount);
                    },
                    error: function (xhr, status, error) {
                        $('#notification')
                            .removeClass('alert-success')
                            .addClass('alert-danger')
                            .text('Error adding product to cart. Please try again.')
                            .fadeIn().delay(3000).fadeOut();
                        showNotification("Error adding product to cart. Please try again.");
                    }
                });
            });
        });


        document.addEventListener("DOMContentLoaded", function() {
            const stock = ${product.stock};  // Available stock from your product object
            let quantity = 1;  // Initial quantity
            // Update quantity when +/- buttons are clicked
            document.querySelector(".inc").addEventListener("click", function() {
                if (quantity < stock) {
                    quantity++;
                    document.getElementById("quantity").value = quantity;
                }
            });

            document.querySelector(".dec").addEventListener("click", function() {
                if (quantity > 1) {
                    quantity--;
                    document.getElementById("quantity").value = quantity;
                }
            });
        });

        function addToCart(productId, productName, productImage, productPrice, maxStock) {
            const quantity = document.getElementById('quantity').value;

            if (quantity > maxStock) {
                showNotification('Quantity exceeds available stock.');
                return;
            }

            // Create data to send to servlet
            const data = {
                id: productId,
                productName: productName,
                imageURL: productImage,
                price: productPrice,
                stock:maxStock,
                quantity: quantity
            };


            $.ajax({
                type: 'POST',
                url: '/product-details',
                data: JSON.stringify(data),
                contentType: 'application/json',
                success: function(response) {
                    if (response.status === "success") {
                        $('.icon_bag_alt').siblings('.tip').text(response.cartItemCount);
                            console.log(user);
                            saveCart();

                        showNotification("Product Added To Cart");
                    } else if (response.status === "fail") {
                        showNotification(response.message);
                    }

                },
                error: function() {
                    alert('Error adding product to cart.');
                }
            });
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
    </script>

</body>

</html>