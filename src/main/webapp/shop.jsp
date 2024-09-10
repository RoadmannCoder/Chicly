<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- Shop Section Begin -->
<section class="shop spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="shop__sidebar">
                    <!-- Filter Form -->
                    <form id="filter-form" method="GET" action="/filterProducts">
<%--                        <!-- Price Filter -->--%>
<%--                        <div class="sidebar__filter">--%>
<%--                            <div class="section-title">--%>
<%--                                <h4>Shop by price</h4>--%>
<%--                            </div>--%>
<%--                            <div class="filter-range-wrap">--%>
<%--                                <div--%>
<%--                                        class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"--%>
<%--                                        data-min="${param.minPrice != null ? param.minPrice : '20'}"--%>
<%--                                        data-max="${param.maxPrice != null ? param.maxPrice : '2000'}"--%>
<%--                                ></div>--%>
<%--                                <div class="range-slider">--%>
<%--                                    <div class="price-input">--%>
<%--                                        <p>Price:</p>--%>
<%--                                        <input type="text" name="minPrice" id="minamount" value="${param.minPrice != null ? param.minPrice : ''}"  />--%>
<%--                                        <input type="text" name="maxPrice" id="maxamount" value="${param.maxPrice != null ? param.maxPrice : ''}"  />--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>

                        <!-- Size Filter -->
                        <div class="sidebar__sizes">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list">
                                <label for="xxs">
                                    xxs
                                    <input type="checkbox" id="xxs" name="size" value="xxs" ${param.size == 'xxs' ? 'checked' : ''} />
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>

                        <!-- Color Filter -->
                        <div class="sidebar__color">
                            <div class="section-title">
                                <h4>Shop by color</h4>
                            </div>
                            <div class="color__list">
                                    <label for="black">
                                        Black
                                        <input type="radio" id="black" name="color" value="black" ${param.color == 'black' ? 'checked' : ''} />
                                        <span class="checkmark"></span>
                                    </label>
                                    <label for="blue">
                                        Blue
                                        <input type="radio" id="blue" name="color" value="blue" ${param.color == 'black' ? 'checked' : ''} />
                                        <span class="checkmark"></span>
                                    </label>

                            </div>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-outline-danger">Apply Filters</button>
                    </form>
                </div>
            </div>

            <div class="col-lg-9 col-md-9">
                <div class="row" id="product-list">
                    <!-- Products will be updated here -->
                    <c:forEach var="subProduct" items="${subProducts}">
                        <div class="col-lg-4 col-md-6">
                            <div class="product__item">
                                <div
                                        class="product__item__pic set-bg"
                                        data-setbg="${subProduct.imageURL}"
                                >
                                    <div class="label new">New</div>
                                    <ul class="product__hover">
                                        <li>
                                            <a href="${subProduct.imageURL}" class="image-popup">
                                                <span class="arrow_expand"></span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#"><span class="icon_heart_alt"></span></a>
                                        </li>
                                        <li>
                                            <a href="#"><span class="icon_bag_alt"></span></a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="#">${subProduct.productName}</a></h6>
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

<div class="container mt-4">
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <!-- Previous Page Link -->
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="filterProducts?page=${currentPage - 1}&category=${selectedCategory}&productName=${fn:escapeXml(param.productName)}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&color=${param.color}&size=${param.size}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <!-- Current Page Display -->
            <li class="page-item active">
                <span class="page-link">Page ${currentPage}</span>
            </li>

            <!-- Next Page Link -->
            <c:if test="${(currentPage * pageSize) < totalSubProducts}">
                <li class="page-item">
                    <a class="page-link" href="filterProducts?page=${currentPage + 1}&category=${selectedCategory}&productName=${fn:escapeXml(param.productName)}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&color=${param.color}&size=${param.size}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
