<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body >
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>
<jsp:include page="common/header.jsp" />
<!-- Header Section End -->
<jsp:include page="common/VNotification.jsp"/>
<jsp:include page="common/WNotification.jsp"/>
<!-- Categories Section Begin -->
<section class="categories">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6 p-0">
                <div class="categories__item categories__large__item set-bg"
                     data-setbg="img/categories/female.jpg">
                    <div class="categories__text">
                        <h1>Women’s fashion</h1>
                        <p>Embrace your unique fashion journey and express yourself with our stunning collection. From chicly everyday essentials to bold statement pieces, we have everything you need.</p>
                        <a href="filterProducts?gender=female">Shop now</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/clothes.jpg">
                            <div class="categories__text">
                                <h4>Clothing</h4>
                                <a href="filterProducts?category=Clothing">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/male.jpg">
                            <div class="categories__text">
                                <h4>Men 's fashion</h4>
                                <a href="filterProducts?gender=male ">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/footwear.jpg">
                            <div class="categories__text">
                                <h4>Footwear</h4>
                                <a href="filterProducts?category=Footwear">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/accessories.jpg">
                            <div class="categories__text">
                                <h4>Accessories</h4>
                                <a href="filterProducts?category=Accessories">Shop now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Categories Section End -->


<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4">
                <div class="section-title">
                    <h4>New Arrivals</h4>
                </div>
            </div>
        </div>
        <div class="row property__gallery">
            <c:forEach var="subProduct" items="${subProducts}">
            <div class="col-lg-3 col-md-4 col-sm-6 mix women">
                <div class="product__item">
                    <div class="product__item__pic set-bg" data-setbg="${subProduct.imageURL}">
                    <c:if test="${subProduct.isNewArrival}">
                        <div class="label new">New</div>
                    </c:if>
                    <ul class="product__hover">
                        <li><a href="${subProduct.imageURL}" class="image-popup"><span class="arrow_expand"></span></a></li>
<%--                        <li><a href="#"><span class="icon_heart_alt"></span></a></li>--%>
                        <li><a class="buttonAddToCart" data-id="${subProduct.id}" data-name="${subProduct.productName}" data-price="${subProduct.price}" data-image="${subProduct.imageURL}" data-stock="${subProduct.stock}"><span class="icon_bag_alt"></span></a></li>
                    </ul>
                </div>
                <div class="product__item__text">
                    <h6><a href="/product-details?product=${subProduct.id}">${subProduct.productName}</a></h6>
                    <div class="product__price">$ ${subProduct.price}</div>
                </div>
            </div>
        </div>
        </c:forEach>
        </div>
    </div>
</section>
<!-- Product Section End -->

<!-- Banner Section Begin -->
<section class="banner set-bg" data-setbg="img/banner/banner-1.jpg">
    <div class="container">
        <div class="row">
            <div class="col-xl-7 col-lg-8 m-auto">
                <div class="banner__slider owl-carousel">
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>The Chloe Collection</span>
                            <h1>The Project Jacket</h1>
                            <a href="filterProducts?searchkeyword=jacket">Shop now</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>The Chloe Collection</span>
                            <h1>The Project Jacket</h1>
                            <a href="filterProducts?searchkeyword=jacket">Shop now</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>The Chloe Collection</span>
                            <h1>The Project Jacket</h1>
                            <a href="filterProducts?searchkeyword=jacket">Shop now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Banner Section End -->




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
<script src="js/product-display.js"></script>
<script src="js/saveCart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>

    var user = '<c:out value="${sessionScope.user}" />';
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

                    VshowNotification("Product Added To Cart");

                    // Optionally, update the cart UI or display cart details
                    // Example: $('#cart-count').text(response.cartItemCount);
                },
                error: function (xhr, status, error) {
                    $('#notification')
                        .removeClass('alert-success')
                        .addClass('alert-danger')
                        .text('Error adding product to cart. Please try again.')
                        .fadeIn().delay(3000).fadeOut();
                    WshowNotification("Error adding product to cart. Please try again.");
                }
            });
        });
    });



    // Function to load CartService from localStorage
    function loadCart() {
        if (!user) {
             const cart = localStorage.getItem("cartService");
            if (cart) {
                const cartData = JSON.parse(cart);
                console.log("Restoring CartService from localStorage:", cartData);
                if (cartData.cartItems) {
                    $('.icon_bag_alt').siblings('.tip').text(cartData.cartItems.length); // Assuming cartItems is an array
                }


                // Send the cart data to the server to restore in the session
                $.ajax({
                    url: "/cartlocal",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(cartData),
                    success: function (response) {
                        console.log("CartService successfully restored on the server.");
                        $('.icon_bag_alt').siblings('.tip').text(response.cartItemCount);

                    },
                    error: function (xhr, status, error) {
                        console.error("Error restoring CartService:", error);

                    }
                });
            }

        }else {
            localStorage.clear();
        }




    }

    // Function to save CartService to localStorage
    function saveCart() {
        // Send an AJAX request to get the CartService from the session
        $.ajax({
            url: "/cartlocal",
            type: "GET",
            success: function (response) {
                // Save the entire CartService object to localStorage
                localStorage.setItem("cartService", JSON.stringify(response.cart));
                $('.icon_bag_alt').siblings('.tip').text(response.cartItemCount);

                console.log("CartService successfully saved to localStorage.");
            },
            error: function (xhr, status, error) {
                console.error("Error saving CartService:", error);
            }
        });
    }
    // Load CartService from localStorage when the page loads
    // Save CartService to localStorage before the page is unloaded
    window.addEventListener("beforeunload", function (event) {
        saveCart();
    });
    window.onload = function() {
            if (user){
                localStorage.removeItem("cartService");
                localStorage.clear();
            }else {
                loadCart();
            }

    };
</script>

</body>
</html>