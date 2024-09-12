<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    document.addEventListener('click', function(event) {
        const userDropdown = document.querySelector('.user-dropdown');
        const dropdownMenu = document.querySelector('.dropdown-menu');
        if (!userDropdown.contains(event.target)) {
            dropdownMenu.style.display = 'none';
        }
    });
</script>
<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__close">+</div>
    <ul class="offcanvas__widget">
        <li><span class="icon_search search-switch"></span></li>
        <li><a href="#"><span class="icon_heart_alt"></span>
            <div class="tip">2</div>
        </a></li>
        <li><a href="#"><span class="icon_bag_alt"></span>
            <div class="tip">2</div>
        </a></li>
    </ul>
    <div class="offcanvas__logo">
        <a href="/"><img src="../img/logo.png" width="98" height="31" alt=""></a>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__auth">
        <a href="/login">Login</a>
        <a href="registration.jsp">Register</a>
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
                        <li class="active"><a href="/">Home</a></li>
                        <li><a href="#">Women’s</a></li>
                        <li><a href="#">Men’s</a></li>
                        <li><a href="filterProducts">Shop</a></li>
                        <li><a href="./contact.html">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__right">
                    <div class="header__right__auth">
                        <c:choose>
                            <c:when test="${empty sessionScope.user}">
                                <a href="login">Login</a>
                                <a href="registration.jsp">Register</a>
                            </c:when>
                            <c:otherwise>
                                <div class="user-dropdown">
                                    <a href="javascript:void(0);" id="user-icon">
                                        <img src="https://cdn-icons-png.flaticon.com/512/149/149071.png" alt="User Icon" style="width: 30px; height: 30px;">
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#"><img src="https://cdn-icons-png.flaticon.com/512/64/64572.png" alt="Orders"> Orders</a></li>
                                        <li><a href="#"><img src="https://cdn-icons-png.flaticon.com/512/709/709496.png" alt="Wishlist"> Wishlist</a></li>
                                        <li><a href="#"><img src="https://cdn-icons-png.flaticon.com/512/126/126467.png" alt="Account Info"> Account Info</a></li>
                                        <li><a href="#"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828479.png" alt="Logout"> Logout</a></li>
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
                        <li><a href="#"><span class="icon_bag_alt"></span>
                            <div class="tip">2</div>
                        </a></li>
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
