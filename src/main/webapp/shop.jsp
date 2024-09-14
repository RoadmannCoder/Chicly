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
    <link rel="stylesheet" href="css/shop.css" type="text/css">


</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>
<jsp:include page="common/header.jsp" />

<!-- Shop Section Begin -->
<section class="shop spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="shop__sidebar">
                    <!-- Filter Form -->
                    <form id="filter-form" method="GET" action="/filterProducts">
                        <!-- Price Filter -->
                        <div class="sidebar__filter">
                            <div class="section-title">
                                <h4>Shop by price</h4>
                            </div>
                            <div class="filter-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"></div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <p>Price:</p>
                                        <input type="text" name="minPrice" id="minamount" value="${param.minPrice != null ? param.minPrice : 20}" />
                                        <input type="text" name="maxPrice" id="maxamount" value="${param.maxPrice != null ? param.maxPrice : 1500}" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Category Filter -->
                        <div class="sidebar__sizes mb-4">
                            <div class="section-title">
                                <h4>Shop by category</h4>
                            </div>
                            <div class="size__list">
                                <c:forEach var="category" items="${categories}">
                                    <label for="${category.name}">
                                            ${category.name}
                                        <input type="radio" name="category" id="${category.name}" value="${category.name}" ${param.category == category.name ? 'checked' : ''}>
                                        <span class="checkmark"></span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <!-- Size Filter -->
                        <div class="sidebar__sizes mb-4">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list">
                                <label for="small">
                                    Small
                                    <input type="radio" name="size" id="small" value="small" ${param.size == 'small' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="medium">
                                    Medium
                                    <input type="radio" name="size" id="medium" value="medium" ${param.size == 'medium' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="large">
                                    Large
                                    <input type="radio" name="size" id="large" value="large" ${param.size == 'large' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xlarge">
                                    XLarge
                                    <input type="radio" name="size" id="xlarge" value="xlarge" ${param.size == 'xlarge' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>

                        <!-- Color Filter -->
                        <div class="sidebar__color mb-4">
                            <div class="section-title">
                                <h4>Shop by color</h4>
                            </div>
                            <div class="color__checkbox">
                                <label for="red">
                                    <input type="radio" name="color" id="red" value="red" ${param.color == 'red' ? 'checked' : ''} />
                                    <span class="checkmark red-bg"></span>
                                </label>
                                <label for="black">
                                    <input type="radio" name="color" id="black" value="black" ${param.color == 'black' ? 'checked' : ''} />
                                    <span class="checkmark black-bg"></span>
                                </label>
                                <label for="white">
                                    <input type="radio" name="color" id="white" value="white" ${param.color == 'white' ? 'checked' : ''} />
                                    <span class="checkmark white-bg"></span>
                                </label>
                                <label for="blue">
                                    <input type="radio" name="color" id="blue" value="blue" ${param.color == 'blue' ? 'checked' : ''} />
                                    <span class="checkmark blue-bg"></span>
                                </label>
                                <label for="green">
                                    <input type="radio" name="color" id="green" value="green" ${param.color == 'green' ? 'checked' : ''} />
                                    <span class="checkmark green-bg"></span>
                                </label>
                                <label for="violet">
                                    <input type="radio" name="color" id="violet" value="violet" ${param.color == 'violet' ? 'checked' : ''} />
                                    <span class="checkmark violet-bg"></span>
                                </label>
                                <label for="yellow">
                                    <input type="radio" name="color" id="yellow" value="yellow" ${param.color == 'yellow' ? 'checked' : ''} />
                                    <span class="checkmark yellow-bg"></span>
                                </label>
                                <label for="brown">
                                    <input type="radio" name="color" id="brown" value="brown" ${param.color == 'brown' ? 'checked' : ''} />
                                    <span class="checkmark brown-bg"></span>
                                </label>
                            </div>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-outline-danger w-auto filter-btn">Apply Filters</button>
                    </form>
                </div>
            </div>
            <div class="col-lg-9 col-md-9">
                <div class="product-list-wrapper">
                    <div class="row" id="product-list">
                        <!-- Products will be updated here -->
                        <c:forEach var="subProduct" items="${subProducts}">
                            <div class="col-lg-4 col-md-6">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg" data-setbg="${subProduct.imageURL}">
                                            <%--                                        <div class="label new">New</div>--%>
                                        <c:if test="${subProduct.isNewArrival}">
                                            <div class="label new">New</div>
                                        </c:if>
                                        <ul class="product__hover">
                                            <li><a href="${subProduct.imageURL}" class="image-popup"><span class="arrow_expand"></span></a></li>
                                            <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                            <li><a class="buttonAddToCart" data-id="${subProduct.id}" data-name="${subProduct.productName}" data-price="${subProduct.price}" data-image="${subProduct.imageURL}" data-stock="${subProduct.stock}"><span class="icon_bag_alt"></span></a></li>
                                        </ul>
                                    </div>
                                    <div class="product__item__text">
                                        <h6><a href="/product-details?product=${subProduct.id}" class="product-detail-button">${subProduct.productName}</a></h6>
                                            <%--                                        <div class="rating">--%>
                                            <%--                                            <c:forEach var="i" begin="1" end="5">--%>
                                            <%--                                                <i class="fa fa-star"></i>--%>
                                            <%--                                            </c:forEach>--%>
                                            <%--                                        </div>--%>
                                        <div class="product__price">${subProduct.price}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- Pagination -->
                    <div class="pagination-wrapper">
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <!-- Previous Page Link -->
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="filterProducts?page=${currentPage - 1}&category=${param.category}&productName=${fn:escapeXml(param.productName)}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&color=${param.color}&size=${param.size}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>

                                <!-- Current Page Display -->
                                <li class="page-item active">
                                    <span class="page-link">${currentPage}</span>
                                </li>

                                <!-- Next Page Link -->
                                <c:if test="${(currentPage * pageSize) < totalSubProducts}">
                                    <li class="page-item">
                                        <a class="page-link" href="filterProducts?page=${currentPage + 1}&category=${param.category}&productName=${fn:escapeXml(param.productName)}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&color=${param.color}&size=${param.size}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>


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
<script>
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
                name: productName,
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
                        .text('Product added to cart successfully!')
                        .fadeIn().delay(3000).fadeOut();

                    // Optionally, update the cart UI with the updated cart count
                    $('.icon_bag_alt').siblings('.tip').text(response.cartItemCount);
                    alert("Product added to cart successfully!");

                    // Optionally, update the cart UI or display cart details
                    // Example: $('#cart-count').text(response.cartItemCount);
                },
                error: function (xhr, status, error) {
                    $('#notification')
                        .removeClass('alert-success')
                        .addClass('alert-danger')
                        .text('Error adding product to cart. Please try again.')
                        .fadeIn().delay(3000).fadeOut();
                    alert("Error adding product to cart. Please try again.");
                }
            });
        });
    });

</script>
</body>
</html>
