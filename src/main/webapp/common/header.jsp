<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    /* Custom Dropdown Menu */
    .user-dropdown {
        position: relative;
        display: inline-block;
    }

    .user-menu {
        font-size: 14px;
        color: #333;
        text-decoration: none;
        padding: 10px;
        display: flex;
        align-items: center;
        cursor: pointer;
    }

    /* User icon from Font Awesome or similar */
    .user-menu .icon_user {
        font-size: 18px; /* Adjust icon size */
        margin-right: 5px;
        color: #333;
    }

    .user-menu .arrow-down {
        margin-left: 5px;
        border: solid #333;
        border-width: 0 2px 2px 0;
        display: inline-block;
        padding: 2px;
        transform: rotate(45deg);
        -webkit-transform: rotate(45deg);
    }

    .user-submenu {
        display: none;
        position: absolute;
        background-color: #fff;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        min-width: 160px;
        z-index: 1;
        right: 0; /* Aligns the dropdown to the right */
    }

    .user-submenu li {
        list-style-type: none;
        padding: 10px;
        border-bottom: 1px solid #eee;
        text-align: right;
    }

    .user-submenu li a {
        text-decoration:none ;
        text-align: right;
        color: #333;
        font-size: 14px;
        display: block;
    }

    .user-submenu li a:hover {
        background-color: #f1f1f1;
        color: #000;
    }

    /* Show dropdown on hover */
    .user-dropdown:hover .user-submenu {
        display: block;
    }

</style>
<c:set var="cart" value="${sessionScope.cart}" />
<c:set var="currentPage" value="${pageContext.request.servletPath}" />
<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__close">+</div>
    <ul class="offcanvas__widget">
        <li><span class="icon_search search-switch"></span></li>
        <li><a href="#"><span class="icon_heart_alt"></span>
            <div class="tip">2</div>
        </a></li>
        <li><a href="/cart"><span class="icon_bag_alt"></span>
            <c:choose>
                <c:when test="${cart.totalCartItems>0}">
                    <div class="tip">${cart.totalCartItems}</div>
                </c:when>
                <c:otherwise>
                    <div class="tip">0</div>
                </c:otherwise>
            </c:choose>
        </a></li>
    </ul>
    <div class="offcanvas__logo">
        <a href="/"><img src="../img/logo.png" width="98" height="31" alt=""></a>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__auth">
        <c:choose>
            <c:when test="${sessionScope.user==null}">
                <a href="login">Login</a>
                <a href="registration.jsp">Register</a>
            </c:when>
            <c:otherwise>
                <div class="user-dropdown">
                    <a href="#" class="user-menu">
                        <i class="fa fa-user fa-2x"></i> <!-- User Icon -->
                        <span class="arrow-down"></span>
                    </a>
                    <ul class="user-submenu">
                        <li><a href="userProfile.jsp">Profile</a></li>
                        <li><a href="/orders">My Orders</a></li>
                        <li><a href="/wishlist">Wishlist</a></li>
                        <li><a href="/logout">Logout</a></li>
                    </ul>
                </div>

            </c:otherwise>
        </c:choose>
    </div>
</div>

<!-- Header Section Begin -->
<header class="header">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-3 col-lg-2">
                <div class="header__logo">
                    <a href="/"><img src="../img/logo.png" width="98" height="31" alt=""></a>
                </div>
            </div>
            <div class="col-xl-6 col-lg-7">
                <nav class="header__menu">
                    <ul>
                        <li class="${currentPage eq '/index.jsp' ? 'active' :''}"><a href="/">Home</a></li>
<%--                        <li><a href="#">Women’s</a></li>--%>
<%--                        <li><a href="#">Men’s</a></li>--%>
                        <li class="${currentPage eq '/shop.jsp' ? 'active' :''}"><a href="filterProducts">Shop</a></li>
<%--                        <li><a href="./contact.html">Contact</a></li>--%>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__right">
                    <div class="header__right__auth">
                        <c:choose>
                            <c:when test="${sessionScope.user==null}">
                                <a href="login">Login</a>
                                <a href="registration.jsp">Register</a>
                            </c:when>
                            <c:otherwise>
                                <div class="user-dropdown">
                                    <a href="#" class="user-menu">
                                        <i class="fa fa-user fa-2x"></i> <!-- User Icon -->
                                        <span class="arrow-down"></span>
                                    </a>
                                    <ul class="user-submenu">
                                        <li><a href="userProfile.jsp">Profile</a></li>
                                        <li><a href="/orders">My Orders</a></li>
                                        <li><a href="/wishlist">Wishlist</a></li>
                                        <li><a href="/logout">Logout</a></li>
                                    </ul>
                                </div>

                            </c:otherwise>
                        </c:choose>
                    </div>
                    <ul class="header__right__widget">
                        <li><span class="icon_search search-switch"></span></li>
                        <li><a href="#"><span class="icon_heart_alt"></span>
                            <div class="tip">2</div>
                        </a></li>
                        <li><a href="/cart"><span class="icon_bag_alt"></span>
                            <c:choose>
                                <c:when test="${cart.totalCartItems>0}">
                                    <div class="tip">${cart.totalCartItems}</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="tip">0</div>
                                </c:otherwise>
                            </c:choose>
                        </a></li>
                        <li>
                            <c:choose>
                                <c:when test="${empty sessionScope.user}">
<%--                                    <span>$ 0.00</span>--%>
                                </c:when>
                                <c:otherwise>
                                    <span>$ ${sessionScope.user.creditLimit}</span>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="canvas__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>



<!-- Header Section End -->
