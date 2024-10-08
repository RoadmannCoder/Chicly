<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Ashion Template" />
    <meta name="keywords" content="Ashion, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Ashion | Template</title>

    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Cookie&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
      rel="stylesheet"
    />

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css" />
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css" />
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css" />
    <link rel="stylesheet" href="css/style.css" type="text/css" />
  </head>

  <body>
    <!-- Page Preloder -->
    <div id="preloder">
      <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
      <div class="offcanvas__close">+</div>
      <ul class="offcanvas__widget">
        <li><span class="icon_search search-switch"></span></li>
        <li>
          <a href="#"
            ><span class="icon_heart_alt"></span>
            <div class="tip">2</div>
          </a>
        </li>
        <li>
          <a href="#"
            ><span class="icon_bag_alt"></span>
            <div class="tip">2</div>
          </a>
        </li>
      </ul>
      <div class="offcanvas__logo">
        <a href="./index.html"><img src="img/logo.png" alt="" /></a>
      </div>
      <div id="mobile-menu-wrap"></div>
      <div class="offcanvas__auth">
        <a href="#">Login</a>
        <a href="#">Register</a>
      </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->

    <!-- Shop Section Begin -->
    <section class="shop spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-3">
            <div class="shop__sidebar">
              <div class="sidebar__filter">
                <div class="section-title">
                  <h4>Shop by price</h4>
                </div>
                <div class="filter-range-wrap">
                  <div
                    class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                    data-min="33"
                    data-max="99"
                  ></div>
                  <div class="range-slider">
                    <div class="price-input">
                      <p>Price:</p>
                      <input type="text" id="minamount" />
                      <input type="text" id="maxamount" />
                    </div>
                  </div>
                </div>
                <a href="#">Filter</a>
              </div>
              <div class="sidebar__sizes">
                <div class="section-title">
                  <h4>Shop by size</h4>
                </div>
                <div class="size__list">
                  <label for="xxs">
                    xxs
                    <input type="checkbox" id="xxs" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="xs">
                    xs
                    <input type="checkbox" id="xs" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="xss">
                    xs-s
                    <input type="checkbox" id="xss" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="s">
                    s
                    <input type="checkbox" id="s" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="m">
                    m
                    <input type="checkbox" id="m" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="ml">
                    m-l
                    <input type="checkbox" id="ml" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="l">
                    l
                    <input type="checkbox" id="l" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="xl">
                    xl
                    <input type="checkbox" id="xl" />
                    <span class="checkmark"></span>
                  </label>
                </div>
              </div>
              <div class="sidebar__color">
                <div class="section-title">
                  <h4>Shop by size</h4>
                </div>
                <div class="size__list color__list">
                  <label for="black">
                    Blacks
                    <input type="checkbox" id="black" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="whites">
                    Whites
                    <input type="checkbox" id="whites" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="reds">
                    Reds
                    <input type="checkbox" id="reds" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="greys">
                    Greys
                    <input type="checkbox" id="greys" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="blues">
                    Blues
                    <input type="checkbox" id="blues" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="beige">
                    Beige Tones
                    <input type="checkbox" id="beige" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="greens">
                    Greens
                    <input type="checkbox" id="greens" />
                    <span class="checkmark"></span>
                  </label>
                  <label for="yellows">
                    Yellows
                    <input type="checkbox" id="yellows" />
                    <span class="checkmark"></span>
                  </label>
                </div>
              </div>
            </div>
          </div>
          <c:forEach var="subProduct" items="${subProducts}">
          <div class="col-lg-9 col-md-9">
            <div class="row">

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
                      <h6><a href="#">${subProduct.name}</a></h6>
                      <div class="rating">
                        <c:forEach var="i" begin="1" end="5">
                          <i class="fa fa-star"></i>
                        </c:forEach>
                      </div>
                      <div class="product__price">$ ${subProduct.price}</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </section>
    <!-- Shop Section End -->


    <!-- Footer Section Begin -->
    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-md-6 col-sm-7">
            <div class="footer__about">
              <div class="footer__logo">
                <a href="./index.html"><img src="img/logo.png" alt="" /></a>
              </div>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt cilisis.
              </p>
              <div class="footer__payment">
                <a href="#"><img src="img/payment/payment-1.png" alt="" /></a>
                <a href="#"><img src="img/payment/payment-2.png" alt="" /></a>
                <a href="#"><img src="img/payment/payment-3.png" alt="" /></a>
                <a href="#"><img src="img/payment/payment-4.png" alt="" /></a>
                <a href="#"><img src="img/payment/payment-5.png" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="col-lg-2 col-md-3 col-sm-5">
            <div class="footer__widget">
              <h6>Quick links</h6>
              <ul>
                <li><a href="#">About</a></li>
                <li><a href="#">Blogs</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">FAQ</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-2 col-md-3 col-sm-4">
            <div class="footer__widget">
              <h6>Account</h6>
              <ul>
                <li><a href="#">My Account</a></li>
                <li><a href="#">Orders Tracking</a></li>
                <li><a href="#">Checkout</a></li>
                <li><a href="#">Wishlist</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4 col-md-8 col-sm-8">
            <div class="footer__newslatter">
              <h6>NEWSLETTER</h6>
              <form action="#">
                <input type="text" placeholder="Email" />
                <button type="submit" class="site-btn">Subscribe</button>
              </form>
              <div class="footer__social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-youtube-play"></i></a>
                <a href="#"><i class="fa fa-instagram"></i></a>
                <a href="#"><i class="fa fa-pinterest"></i></a>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            <div class="footer__copyright__text">
              <p>
                Copyright &copy;
                <script>
                  document.write(new Date().getFullYear());
                </script>
                All rights reserved | This template is made with
                <i class="fa fa-heart" aria-hidden="true"></i> by
                <a href="https://colorlib.com" target="_blank">Colorlib</a>
              </p>
            </div>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
          </div>
        </div>
      </div>
    </footer>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
      <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
          <input type="text" id="search-input" placeholder="Search here....." />
        </form>
      </div>
    </div>
    <!-- Search End -->

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script> <!-- Ensure jQuery is loaded first -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/jquery.countdown.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.nicescroll.min.js"></script>
    <script src="js/main.js"></script> <!-- jQuery dependent script -->
  </body>
</html>
