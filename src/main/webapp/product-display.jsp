<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Product Filter</title>--%>
<%--    <link rel="stylesheet" href="path/to/your/styles.css">--%>
<%--    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>
<%--    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>--%>
<%--    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="shop">--%>
<%--    <!-- Sidebar Section Begin -->--%>
<%--    <div class="shop__sidebar">--%>
<%--        <form method="get" action="filterProducts">--%>
<%--            <div class="sidebar__filter">--%>
<%--                <div class="section-title">--%>
<%--                    <h4>Shop by price</h4>--%>
<%--                </div>--%>
<%--                <div class="filter-range-wrap">--%>
<%--                    <div--%>
<%--                            class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"--%>
<%--                            data-min="33"--%>
<%--                            data-max="99"--%>
<%--                    ></div>--%>
<%--                    <div class="range-slider">--%>
<%--                        <div class="price-input">--%>
<%--                            <p>Price:</p>--%>
<%--                            <input type="text" id="minamount" name="minPrice" value="${param.minPrice}" />--%>
<%--                            <input type="text" id="maxamount" name="maxPrice" value="${param.maxPrice}" />--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <a href="#" onclick="this.closest('form').submit();">Filter</a>--%>
<%--            </div>--%>

<%--            <div class="sidebar__sizes">--%>
<%--                <div class="section-title">--%>
<%--                    <h4>Shop by size</h4>--%>
<%--                </div>--%>
<%--                <div class="size__list">--%>
<%--                    <label for="xxs">--%>
<%--                        xxs--%>
<%--                        <input type="checkbox" id="xxs" name="size" value="xxs" ${param.size == 'xxs' ? 'checked' : ''} />--%>
<%--                        <span class="checkmark"></span>--%>
<%--                    </label>--%>
<%--                    <!-- Add other sizes similarly -->--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="sidebar__color">--%>
<%--                <div class="section-title">--%>
<%--                    <h4>Shop by color</h4>--%>
<%--                </div>--%>
<%--                <div class="color__list">--%>
<%--                    <label for="black">--%>
<%--                        Black--%>
<%--                        <input type="checkbox" id="black" name="color" value="black" ${param.color == 'black' ? 'checked' : ''} />--%>
<%--                        <span class="checkmark"></span>--%>
<%--                    </label>--%>
<%--                    <!-- Add other colors similarly -->--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--    <!-- Sidebar Section End -->--%>

<%--    <!-- Product Section Begin -->--%>
<%--    <div class="shop__products">--%>
<%--        <section class="product spad">--%>
<%--            <div class="container">--%>
<%--                <div class="row property__gallery">--%>
<%--                    <c:forEach var="subProduct" items="${subProducts}">--%>
<%--                        <div class="col-lg-3 col-md-4 col-sm-6 mix">--%>
<%--                            <div class="product__item">--%>
<%--                                <div class="product__item__pic" style="background-image: url('${subProduct.imageURL}');">--%>
<%--                                    <c:if test="${subProduct.stock == 0}">--%>
<%--                                        <div class="label stockout">Out of stock</div>--%>
<%--                                    </c:if>--%>
<%--                                    <ul class="product__hover">--%>
<%--                                        <li><a href="${subProduct.imageURL}" class="image-popup"><span class="arrow_expand"></span></a></li>--%>
<%--                                        <li><a href="#"><span class="icon_heart_alt"></span></a></li>--%>
<%--                                        <li><a href="#"><span class="icon_bag_alt"></span></a></li>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>
<%--                                <div class="product__item__text">--%>
<%--                                    <h6><a href="#">${subProduct.productName}</a></h6>--%>
<%--                                    <div class="product__price">$ ${subProduct.price}</div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </section>--%>
<%--    </div>--%>
<%--    <!-- Product Section End -->--%>

<%--    <!-- Pagination controls -->--%>
<%--    <div class="pagination">--%>
<%--        <c:if test="${currentPage > 1}">--%>
<%--            <a href="filterProducts?page=${currentPage - 1}&category=${selectedCategory}&productName=${param.productName}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&color=${param.color}&size=${param.size}">Previous</a>--%>
<%--        </c:if>--%>
<%--        <span>Page ${currentPage}</span>--%>
<%--        <c:if test="${(currentPage * pageSize) < totalSubProducts}">--%>
<%--            <a href="filterProducts?page=${currentPage + 1}&category=${selectedCategory}&productName=${param.productName}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&color=${param.color}&size=${param.size}">Next</a>--%>
<%--        </c:if>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script>--%>
<%--    $(function() {--%>
<%--        $(".price-range").slider({--%>
<%--            range: true,--%>
<%--            min: 33,--%>
<%--            max: 99,--%>
<%--            values: [${param.minPrice != '' ? param.minPrice : 33}, ${param.maxPrice != '' ? param.maxPrice : 99}],--%>
<%--            slide: function(event, ui) {--%>
<%--                $("#minamount").val(ui.values[0]);--%>
<%--                $("#maxamount").val(ui.values[1]);--%>
<%--            }--%>
<%--        });--%>
<%--        $("#minamount").val($(".price-range").slider("values", 0));--%>
<%--        $("#maxamount").val($(".price-range").slider("values", 1));--%>
<%--    });--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>
