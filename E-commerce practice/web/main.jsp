<%-- 
    Document   : main
    Created on : Jan 24, 2018, 8:20:05 PM
    Author     : Hazem
--%>

<%@page import="util.User"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bx slider css files -->
        <link rel="stylesheet" href="css/jquery.bxslider.min.css"/>
        <!-- css files -->
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/web.css"/>
        <!-- Font awesome iconic librery -->
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/lightbox2/css/lightbox.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
        
    </head>
    <body>
        
        
        
        
    <!-- Header -->
    <header class="header1">
	<!-- Header desktop -->
	<div class="container-menu-header">
            <div class="wrap_header">
                <!-- Logo -->
                <a href="index.html" class="logo">
                        <img src="images/icons/logo.png" alt="IMG-LOGO">
                </a>
				<!-- Menu -->
				<!-- Header Icon -->
                <div class="header-icons">

                    <c:if test = "${sessionScope.user != null}">
                        welcom:<small style="color: green"> ${sessionScope.user.getName()}</small>
                        <img style="width: 50px;height: 50px;border-radius: 25px" src="images/users/${sessionScope.user.getId()}.png">
                        <form action="ProfUser" method="get">
                            <input type="hidden" name="prof" value="load"/>
                            <input type="hidden" name="userId" value="${sessionScope.user.getId()}"/>
                            <input type="submit" value="showProfile"/>
                        </form>
                        <form method="post" action="Logout">
                            <input type="submit" value="Logout">
                        </form>
                    </c:if>

                    <c:if test = "${sessionScope.user == null}">
                        welcom:<small style="color: green"> </small>
                        <a href="Login.jsp">login</a>
                        <a href="SignUp.jsp">signup</a>

                    </c:if> 

                    <span class="linedivide1"></span>

                    <div class="header-wrapicon2">
                        <img src="images/icons/icon-header-02.png" class="header-icon1 js-show-header-dropdown" alt="ICON">
                        <span class="header-icons-noti">${sessionScope.countproducts}</span>
                        <!-- Header cart noti -->
                        <div class="header-cart header-dropdown">
                            <ul class="header-cart-wrapitem">

                                <c:forEach var="product" items="${sessionScope.mycart}">
                                <li class="header-cart-item">
                                    <div class="header-cart-item-img">
                                        <img style="width: 80px;height: 80px;" src="images/products/${product.getProductId()}.png" alt="IMG">
                                    </div>
                                    <div class="header-cart-item-txt">
                                            <p  class="header-cart-item-name">
                                                    ${product.getCategoryName()}
                                            </p>
                                            <p  class="header-cart-item-name">
                                                    ${product.getProductName()}
                                            </p>

                                            <span class="header-cart-item-info">
                                                    ${product.getProductQuantity()} x ${product.getProductPrice()} EP
                                            </span>
                                    </div>
                                </li>
                                </c:forEach>
                                
                            </ul>

                            <div class="header-cart-total">
                                ${sessionScope.countprices} EP
                            </div>

                            <div class="header-cart-buttons">
                                <div class="header-cart-wrapbtn">
                                    <!-- Button -->
                                    <form method="post" action="myCart.jsp" >
                                        <button type="submit" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">Shopping Cart</button> 
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!--/header-->
           
    <!-- Slide1 -->
    <section class="slide1">
        <div class="wrap-slick1">
            <div class="slick1">
                <div class="item-slick1 item1-slick1" style="background-image: url(images/nav.jpg);">
                    <div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
                        <span class="caption1-slide1 m-text1 t-center animated visible-false m-b-15" data-appear="fadeInDown">
                                New Collection 2018
                        </span>

                        <h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
                                New products
                        </h2>

                        <div id="nav" class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="zoomIn">
                                <!-- Button -->
                                <a class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4" href="#linktoproducts">
                                        Shop Now
                                </a>
                        </div>
                    </div>
                </div>

				<div class="item-slick1 item2-slick1" style="background-image: url(images/nav.jpg);">
					<div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
						<span class="caption1-slide1 m-text1 t-center animated visible-false m-b-15" data-appear="rollIn">
							New Collection 2018
						</span>

						<h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="lightSpeedIn">
							New productss
						</h2>

						<div id="nav" class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="slideInUp">
							<!-- Button -->
							<a class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4" href="#linktoproducts">
								Shop Now
							</a>
						</div>
					</div>
				</div>

				<div class="item-slick1 item3-slick1" style="background-image: url(images/nav.jpg);">
					<div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
						<span class="caption1-slide1 m-text1 t-center animated visible-false m-b-15" data-appear="rotateInDownLeft">
							New Collection 2018
						</span>

						<h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="rotateInUpRight">
							New products
						</h2>

						<div id="nav" class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="rotateIn">
							<!-- Button -->
							<a  class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4" href="#linktoproducts">
								Shop Now
							</a>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
        <!-- Slide1 -->
        
        <!-- Featured Products -->
	<section class="newproduct bgwhite p-t-45 p-b-105">
		<div class="container">
			<div class="sec-title p-b-60">
				<h3 class="m-text5 t-center">
					Featured Products
				</h3>
			</div>

			<!-- Slide2 -->
			<div class="wrap-slick2">
				<div class="slick2">
                                    <c:forEach var="category" items="${applicationScope.products1}">
                                        <div class="item-slick2 p-l-15 p-r-15">
						<!-- Block2 -->
						<div class="block2">
							<div class="block2-img wrap-pic-w of-hidden pos-relative block2-labelsale">
								
                                                            <img style="width: 270px;height: 
                                                                 360px" src="images/products/${category.value.get(0).getId()}.png" alt="IMG-PRODUCT">

								<div class="block2-overlay trans-0-4">
									<a href="#" class="block2-btn-addwishlist hov-pointer trans-0-4">
										<i class="icon-wishlist icon_heart_alt" aria-hidden="true"></i>
										<i class="icon-wishlist icon_heart dis-none" aria-hidden="true"></i>
									</a>

									<div class="block2-btn-addcart w-size1 trans-0-4">
										<!-- Button -->
										
                                                                                <form name="frm" method="get" action="cart">
                                                                                    <input type="hidden" name = "cart" value="${category.value.get(0).getId()}">
                                                                                    <input type="hidden" name = "productname" value="${category.value.get(0).getName()}">
                                                                                    <input type="hidden" name = "productprice" value="${category.value.get(0).getPrice()}">
                                                                                    <input type="hidden" name = "categoryname" value="${category.key.getName()}">
                                                                                    <input type="hidden" name = "availablequantity" value="${category.value.get(0).getQuantities()}">
                                                                                    <button type="submit" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" >add to cart   <i class="fa fa-shopping-cart"></i></button>

                                                                                </form>
									</div>
								</div>
							</div>

							<div class="block2-txt p-t-20">
								<p href="product-detail.html" class="block2-name dis-block s-text3 p-b-5">
									${category.value.get(0).getName()}
								</p>

								<span class="block2-oldprice m-text7 p-r-5">
									${category.value.get(0).getPrice() + category.value.get(0).getPrice() * .1}
								</span>

								<span class="block2-newprice m-text8 p-r-5">
									${category.value.get(0).getPrice()} E.P
								</span>
							</div>
						</div>
					</div>
                                    </c:forEach>

					
				</div>
			</div>

		</div>
	</section>
        
        
        
        
        
        
        
        
        <p><input style="width: 400px;border: 1px solid #040505;" type="text" id="quicksearch" placeholder="Search by name or description..." /></p>
        <div id="filters" class="button-group">
            <button class="button is-checked" data-filter="*">show all</button>
            <c:forEach var="type" items="${applicationScope.products1}">
                <button class="button" data-filter=".${type.key.getName()}">${type.key.getName()}</button>
            </c:forEach>
                
        </div>
        
        <div class="grid">
            <a name="linktoproducts"></a>
            <c:forEach var="product" items="${applicationScope.products1}">
                <div class="cnt">
                <!--<h2>${product.key.getName()}</h2>-->
                    
                <c:forEach var="item" items="${product.value}">
                    
                    
                    
                    <div class="item-slick2 p-l-15 p-r-15 element-item ${product.key.getName()}">
						<!-- Block2 -->
                    <div class="block2">
                            <div class="block2-img wrap-pic-w of-hidden pos-relative">

                                <img style="width: 270px;height: 
                                     360px" src="images/products/${item.getId()}.png" alt="IMG-PRODUCT">

                                    <div class="block2-overlay trans-0-4">
                                            <a href="#" class="block2-btn-addwishlist hov-pointer trans-0-4">
                                                    <i class="icon-wishlist icon_heart_alt" aria-hidden="true"></i>
                                                    <i class="icon-wishlist icon_heart dis-none" aria-hidden="true"></i>
                                            </a>

                                            <div class="block2-btn-addcart w-size1 trans-0-4">
                                                    <!-- Button -->

                                                    <form name="frm" method="get" action="cart">
                                                        <input type="hidden" name = "cart" value="${item.getId()}">
                                                        <input type="hidden" name = "productname" value="${item.getName()}">
                                                        <input type="hidden" name = "productprice" value="${item.getPrice()}">
                                                        <input type="hidden" name = "categoryname" value="${product.key.getName()}">
                                                        <input type="hidden" name = "availablequantity" value="${item.getQuantities()}">
                                                        <button type="submit" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" >add to cart   <i class="fa fa-shopping-cart"></i></button>

                                                    </form>
                                            </div>
                                    </div>
                            </div>
                            <div class="block2-txt p-t-20">
                                    <p href="product-detail.html" class="block2-name dis-block s-text3 p-b-5">
                                            ${item.getName()}
                                    </p>
                                    <p href="product-detail.html" class="block2-name dis-block s-text3 p-b-5">
                                            ${item.getDescription()}
                                    </p>
                                    
                                    <p href="product-detail.html" class="block2-name dis-block s-text3 p-b-5">
                                            ${item.getQuantities()}
                                    </p>
                                    

                                    <span class="block2-newprice m-text8 p-r-5">
                                            ${item.getPrice()} E.P
                                    </span>
                            </div>
                    </div>
            </div>


                            
                            
                            
                            
                </c:forEach>
                </div>
                <br>
            </c:forEach>
            
        </div>
        <form method="post" action="myCart.jsp" >
            <button type="submit" class="btn btn-info btn-lg">Shopping Cart</button> 
        </form>
        
        <footer class="bg6 p-t-45 p-b-43 p-l-45 p-r-45">
		<div class="flex-w p-b-90">
			<div class="w-size6 p-t-30 p-l-15 p-r-15 respon3">
				<h4 class="s-text12 p-b-30">
					GET IN TOUCH
				</h4>

				<div>
					<p class="s-text7 w-size27">
						Any questions? Let us know in store at 8th floor, 379 Hudson St, New York, NY 10018 or call us on (+1) 96 716 6879
					</p>

					<div class="flex-m p-t-30">
						<a href="#" class="fs-18 color1 p-r-20 fa fa-facebook"></a>
						<a href="#" class="fs-18 color1 p-r-20 fa fa-instagram"></a>
						<a href="#" class="fs-18 color1 p-r-20 fa fa-pinterest-p"></a>
						<a href="#" class="fs-18 color1 p-r-20 fa fa-snapchat-ghost"></a>
						<a href="#" class="fs-18 color1 p-r-20 fa fa-youtube-play"></a>
					</div>
				</div>
			</div>

			<div class="w-size7 p-t-30 p-l-15 p-r-15 respon4">
				<h4 class="s-text12 p-b-30">
					Categories
				</h4>

				<ul>
					<li class="p-b-9">
						<a href="#" class="s-text7">
							Men
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							Women
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							Dresses
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							Sunglasses
						</a>
					</li>
				</ul>
			</div>

			<div class="w-size7 p-t-30 p-l-15 p-r-15 respon4">
				<h4 class="s-text12 p-b-30">
					Links
				</h4>

				<ul>
					<li class="p-b-9">
						<a href="#" class="s-text7">
							Search
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							About Us
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							Contact Us
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							Returns
						</a>
					</li>
				</ul>
			</div>

			<div class="w-size7 p-t-30 p-l-15 p-r-15 respon4">
				<h4 class="s-text12 p-b-30">
					Help
				</h4>

				<ul>
					<li class="p-b-9">
						<a href="#" class="s-text7">
							Track Order
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							Returns
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							Shipping
						</a>
					</li>

					<li class="p-b-9">
						<a href="#" class="s-text7">
							FAQs
						</a>
					</li>
				</ul>
			</div>

			<div class="w-size8 p-t-30 p-l-15 p-r-15 respon3">
				<h4 class="s-text12 p-b-30">
					Newsletter
				</h4>

				<form>
					<div class="effect1 w-size9">
						<input class="s-text7 bg6 w-full p-b-5" type="text" name="email" placeholder="email@example.com">
						<span class="effect1-line"></span>
					</div>

					<div class="w-size2 p-t-20">
						<!-- Button -->
						<button class="flex-c-m size2 bg4 bo-rad-23 hov1 m-text3 trans-0-4">
							Subscribe
						</button>
					</div>

				</form>
			</div>
		</div>

		<div class="t-center p-l-15 p-r-15">
			<a href="#">
				<img class="h-size2" src="images/icons/paypal.png" alt="IMG-PAYPAL">
			</a>

			<a href="#">
				<img class="h-size2" src="images/icons/visa.png" alt="IMG-VISA">
			</a>

			<a href="#">
				<img class="h-size2" src="images/icons/mastercard.png" alt="IMG-MASTERCARD">
			</a>

			<a href="#">
				<img class="h-size2" src="images/icons/express.png" alt="IMG-EXPRESS">
			</a>

			<a href="#">
				<img class="h-size2" src="images/icons/discover.png" alt="IMG-DISCOVER">
			</a>

			<div class="t-center s-text8 p-t-20">
				Copyright © 2018 All rights reserved. | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
			</div>
		</div>
	</footer>



	<!-- Back to top -->
	<div class="btn-back-to-top bg0-hov" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="fa fa-angle-double-up" aria-hidden="true"></i>
		</span>
            
        </div>

	<!-- Container Selection1 -->
	<div id="dropDownSelect1"></div>
        
            
        <!-- Our Scripts -->
        <script src="js/jquery-3.1.0.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/jquery.bxslider.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/custom.js"></script>
        <script src='http://npmcdn.com/isotope-layout@3/dist/isotope.pkgd.js'></script>
        <script  src="js/index.js"></script>
        <script src="js/io.js"></script>
        
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js" type="text/javascript"></script> 
        <script src="jquery.localscroll.js" type="text/javascript"></script> 
        <script src="jquery.scrollTo.js" type="text/javascript"></script>
        
        <!--===============================================================================================-->
	<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/bootstrap/js/popper.js"></script>
	<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/select2/select2.min.js"></script>
	<script type="text/javascript">
		$(".selection-1").select2({
			minimumResultsForSearch: 20,
			dropdownParent: $('#dropDownSelect1')
		});
	</script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/lightbox2/js/lightbox.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
		$('.block2-btn-addcart').each(function(){
			var nameProduct = $(this).parent().parent().parent().find('.block2-name').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to cart !", "success");
			});
		});

                $(document).ready(function() {
                   $('#nav').localScroll({duration:800});
                });
	</script>

<!--===============================================================================================-->
	<script src="js/main.js"></script>

        <!-- End Projects -->
        
        
    </body>
</html>
