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
                                        <input type="text" name="maxPrice" id="maxamount" value="${param.maxPrice != null ? param.maxPrice : 300}" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Category Filter -->
                        <div class="sidebar__categories mb-4">
                            <div class="section-title">
                                <h4>Shop by category</h4>
                            </div>
                            <div class="category__list">
                                <c:forEach var="category" items="${categories}">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" id="${category.id}" name="category" value="${category.id}" ${param.category == category.id ? 'checked' : ''} />
                                        <label class="form-check-label" for="${category.id}">${category.name}</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <!-- Size Filter -->
                        <div class="sidebar__sizes mb-4">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="small" name="size" value="small" ${param.size == 'small' ? 'checked' : ''} />
                                    <label class="form-check-label" for="small">Small</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="medium" name="size" value="medium" ${param.size == 'medium' ? 'checked' : ''} />
                                    <label class="form-check-label" for="medium">Medium</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="large" name="size" value="large" ${param.size == 'large' ? 'checked' : ''} />
                                    <label class="form-check-label" for="large">Large</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="xlarge" name="size" value="xlarge" ${param.size == 'xlarge' ? 'checked' : ''} />
                                    <label class="form-check-label" for="xlarge">XLarge</label>
                                </div>
                            </div>
                        </div>

                        <!-- Color Filter -->
                        <div class="sidebar__color mb-4">
                            <div class="section-title">
                                <h4>Shop by color</h4>
                            </div>
                            <div class="color__list">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="black" name="color" value="black" ${param.color == 'black' ? 'checked' : ''} />
                                    <label class="form-check-label" for="black">Black</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="white" name="color" value="white" ${param.color == 'white' ? 'checked' : ''} />
                                    <label class="form-check-label" for="white">White</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="blue" name="color" value="blue" ${param.color == 'blue' ? 'checked' : ''} />
                                    <label class="form-check-label" for="blue">Blue</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="red" name="color" value="red" ${param.color == 'red' ? 'checked' : ''} />
                                    <label class="form-check-label" for="red">Red</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="green" name="color" value="green" ${param.color == 'green' ? 'checked' : ''} />
                                    <label class="form-check-label" for="green">Green</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="violet" name="color" value="violet" ${param.color == 'violet' ? 'checked' : ''} />
                                    <label class="form-check-label" for="violet">Violet</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="yellow" name="color" value="yellow" ${param.color == 'yellow' ? 'checked' : ''} />
                                    <label class="form-check-label" for="yellow">Yellow</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="brown" name="color" value="brown" ${param.color == 'brown' ? 'checked' : ''} />
                                    <label class="form-check-label" for="brown">Brown</label>
                                </div>
                            </div>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-outline-danger w-100">Apply Filters</button>
                    </form>
                </div>
            </div>

            <div class="col-lg-9 col-md-9">
                <div class="row" id="product-list">
                    <!-- Products will be updated here -->
                    <c:forEach var="subProduct" items="${subProducts}">
                        <div class="col-lg-4 col-md-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="${subProduct.imageURL}">
                                    <div class="label new">New</div>
                                    <ul class="product__hover">
                                        <li><a href="${subProduct.imageURL}" class="image-popup"><span class="arrow_expand"></span></a></li>
                                        <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                        <li><a class="buttonAddToCart" data-id="${subProduct.id}" data-name="${subProduct.productName}" data-price="${subProduct.price}" data-image="${subProduct.imageURL}" data-stock="${subProduct.stock}"><span class="icon_bag_alt"></span></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="/product-details?product=${subProduct.id}" class="product-detail-button"
                                           >${subProduct.productName}</a></h6>
                                    <div class="rating">
                                        <c:forEach var="i" begin="1" end="5">
                                            <i class="fa fa-star"></i>
                                        </c:forEach>
                                    </div>
                                    <div class="product__price">${subProduct.price}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Notification Area -->
<div id="notification" class="alert" style="display: none;"></div>

<div class="container mt-4">
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

<jsp:include page="common/footer.jsp"/>

<!-- Search Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search End -->

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
