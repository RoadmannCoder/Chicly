<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="header__menu">
    <div class="container">
        <ul class="category-list">
            <li><a href="filterProducts?category=all" class="${empty selectedCategory || selectedCategory eq 'all' ? 'active' : ''}">All Categories</a></li>
            <c:forEach var="category" items="${categories}">
                <li><a href="filterProducts?category=${category.id}" class="${category.id eq selectedCategory ? 'active' : ''}">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</nav>


<!-- Product Section Begin -->
<section class="product spad">
    <div class="container">
        <div class="row property__gallery">
            <c:forEach var="subProduct" items="${subProducts}">
                <div class="col-lg-3 col-md-4 col-sm-6 mix">
                    <div class="product__item">
                        <div class="product__item__pic" style="background-image: url('${subProduct.imageURL}');">
                            <c:if test="${subProduct.stock == 0}">
                                <div class="label stockout">Out of stock</div>
                            </c:if>
                            <ul class="product__hover">
                                <li><a href="${subProduct.imageURL}" class="image-popup"><span class="arrow_expand"></span></a></li>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="#">${subProduct.product.name}</a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price">$ ${subProduct.price}</div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- Product Section End -->