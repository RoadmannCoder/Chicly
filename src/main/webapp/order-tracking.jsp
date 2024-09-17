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
    <!-- CSS Files -->
    <!--==== Google Fonts ====-->
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900%7CRoboto+Condensed:300,400,700" rel="stylesheet">
    
    <!--==== Bootstrap css file ====-->
    <link rel="stylesheet" href="order/css/bootstrap.min.css">

    <!--==== Font-Awesome css file ====-->
    <link rel="stylesheet" href="order/css/font-awesome.min.css">

    <!-- Owl Carusel css file -->
    <link rel="stylesheet" href="order/plugins/owl-carousel/owl.carousel.min.css">

    <!-- Magnific-Popup css file -->
    <link rel="stylesheet" href="order/plugins/Magnific-Popup/magnific-popup.css">

    <!-- animate css file -->
    <link rel="stylesheet" href="order/plugins/animate-css/animate.min.css">

    <!-- swiper -->
    <link rel="stylesheet" href="order/plugins/swiper/swiper.min.css">

    <!--==== Style css file ====-->
    <link rel="stylesheet" href="order/css/style.css">

    <!--==== Responsive css file ====-->
    <link rel="stylesheet" href="order/css/responsive.css">

    <!--==== Custom css file ====-->
    <link rel="stylesheet" href="order/css/custom.css">
</head>

<body>
  <jsp:include page="common/header.jsp"/>


    <!-- End of offcanvas menu-->

    <!-- page title -->
    <section class="page-title-inner" data-bg-img='order/img/page-titlebg.png'>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- page title inner -->
                    <div class="page-title-wrap">
                        <div class="page-title-heading"><h1 class="h2">Order Tracking<span>Tracking</span></h1></div>
                        <ul class="list-unstyled mb-0">
                            <li><a href="/">home</a></li>
                            <li><a href="#">Order</a></li>
                            <li class="active"><a href="#">Tracking</a></li>
                        </ul>
                    </div>
                    <!-- End of page title inner -->
                </div>
            </div>
        </div>
    </section>
    <!-- End of page title -->
    
    <!-- tracking wrap -->
    <section class="pt-100 pb-100">
        <div class="container">
            <div class="row track-lines">
                <div class="col-md-3 col-sm-3">
                    <!-- single tracking inner -->
                    <div class="single-tracking-inner text-center line-1">
                        <div class="single-tracking-img">
                            <img src="order/img/icons/torder.png" alt="">
                        </div>
                        <h5 class="lato">Order Placed</h5>
                        <p>${requestScope.order.createdAt}</p>
                    </div>
                    <!-- End of single tracking inner -->
                </div>

                 <div class="col-md-3 col-sm-3">
                    <!-- single tracking inner -->
                    <div class="single-tracking-inner text-center ">
                        <div class="single-tracking-img">
                            <img src="order/img/icons/tpacked.png" alt="">
                        </div>
                        <h5 class="lato">Packed</h5>
                        <p>${requestScope.order.createdAt}</p>
                    </div>
                    <!-- End of single tracking inner -->
                </div>

                <div class="col-md-3 col-sm-3">
                    <!-- single tracking inner -->
                    <div class="single-tracking-inner text-center">
                        <div class="single-tracking-img">
                            <img src="order/img/icons/ttransit.png" alt="">
                        </div>
                        <h5 class="lato">In Transit</h5>
                        <p>${requestScope.date}</p>
                    </div>
                    <!-- End of single tracking inner -->
                </div>

                <div class="col-md-3 col-sm-3">
                    <!-- single tracking inner -->
                    <div class="single-tracking-inner text-center">
                        <div class="single-tracking-img">
                            <img src="order/img/icons/tdeliverd.png" alt="">
                        </div>
                        <h5 class="lato">Deliverd</h5>
                        <p>${requestScope.date}</p>
                    </div>
                    <!-- End of single tracking inner -->
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <!--order deliverd date -->
                    <div class="order-deliverd-date text-center">
                        <p>Your Order Will Deliver on <span>${requestScope.date}</span></p>
                    </div>
                    <!--End of order deliverd date -->
                </div>
                <div class="col-md-12">
                    <!-- order details wrap -->
                    <div class="order-details-wrap">
                        <div class="order-details-heading">
                            <h5 class="lato">Order Detail:</h5>
                        </div>
                        <div class="order-address-details">
                            <div class="order-no">
                                <p>Order No: <span>${requestScope.order.id}</span></p>
                            </div>
                            <div class="row">
                                <div class="col-md-11 offset-md-1">
                                    <!-- order address inner -->
                                    <div class="order-address-inner clearfix">
                                        <div class="shipping-to-area">
                                            <h5>Shipping To:</h5>
                                            <p>${requestScope.order.customer.address.city}</p>
                                            <p>${requestScope.order.customer.address.street},<br>${requestScope.order.customer.address.zip}</p>
                                        </div>

                                        <div class="billing-to-area">
                                            <h5>Products List:</h5>
                                            <c:forEach var="item" items="${requestScope.order.orderItems}">
                                            <p>${item.subProduct.product.name}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <!-- End of order address inner -->
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <!-- order details wrap -->
                </div>
            </div>
        </div>
    </section>
    <!-- End of tracking wrap -->

 <jsp:include page="common/footer.jsp"/>
        <!-- End of footer area -->
        

        <!-- add add to cart notifacition -->
    
        <!-- JS Files -->
       <!-- ==== JQuery 3.3.1 js file==== -->
       <script src="order/js/jquery-3.3.1.min.js"></script>
    
       <!-- ==== Bootstrap js file==== -->
       <script src="order/js/bootstrap.bundle.min.js"></script>
    
       <!-- ==== JQuery Waypoint js file==== -->
       <script src="order/plugins/waypoints/jquery.waypoints.min.js"></script>
    
       <!-- ==== Parsley js file==== -->
       <script src="order/plugins/parsley/parsley.min.js"></script>
    
       <!-- ==== Ratina js file==== -->
       <script src="order/plugins/retinajs/retina.min.js"></script>
    
       <!--===parallax js file===-->
       <script src="order/plugins/parallax/parallax.js"></script>
    
       <!--=== hori parallax js file===-->
       <script src="order/plugins/parallax/parallaxh.min.js"></script>
    
       <!-- ==== Owl Carousel js file==== -->
       <script src="order/plugins/owl-carousel/owl.carousel.min.js"></script>
    
       <!-- ====Magnific-Popup js file==== -->
       <script src="order/plugins/Magnific-Popup/jquery.magnific-popup.min.js"></script>
    
       <!-- ====Counter js file=== -->
       <script src="order/plugins/waypoints/jquery.counterup.min.js"></script>
    
       <!-- ====packery==== -->
       <script src="order/plugins/isotope/packery.pkgd.min.js"></script>
    
       <!-- ====swiper==== -->
       <script src="order/plugins/swiper/swiper.min.js"></script>
    
       <!-- ====Count down js==== -->
       <script src="order/plugins/countdown/jquery.countdown.min.js"></script>
    
       <!-- ====zoom js==== -->
       <script src="order/plugins/Magnific-Popup/jquery.elevateZoom-3.0.8.min.js"></script>
    
        <!-- ====tweenMax==== -->
       <script src="order/plugins/tweenmax/TweenMax.min.js"></script>
    
       <!-- ====text animation==== -->
        <script src="order/plugins/text-animation/anime.min.js"></script>

        <!-- ====google map api key====-->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2D8wrWMY3XZnuHO6C31uq90JiuaFzGws"></script>
    
       <!-- ==== Script js file==== -->
       <script src="order/js/scripts.js"></script>
    
       <!-- ==== Custom js file==== -->
       <script src="order/js/custom.js"></script>
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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
    </body>
    </html>