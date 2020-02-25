<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="WEB-INF/static/head.jspf" %>
<body>

<div class="site-wrap">

    <%@ include file="WEB-INF/static/header.jsp" %>

    <div class="site-blocks-cover" data-aos="fade">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="site-block-cover-content">
                        <h2 class="sub-title"><fmt:message key = "message.main.head"/></h2>
                        <h1><fmt:message key = "message.main.head2"/></h1>
                        <p><a href="#" class="btn btn-black rounded-0"><fmt:message key = "message.main.shop_now"/></a></p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-5 ml-auto d-flex align-items-center mt-4 mt-md-0">
                    <img src="style/images/model_3.png" alt="Image" class="img-fluid">
                </div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="title-section mb-5">
                <h2 class="text-uppercase"><span class="d-block">Discover</span> The Collections</h2>
            </div>
            <div class="row align-items-stretch">
                <div class="col-lg-8">
                    <div class="product-item sm-height full-height bg-gray">
                        <img src="style/images/model_4.png" alt="Image" class="img-fluid">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="product-item sm-height bg-gray mb-4">
                        <img src="style/images/model_5.png" alt="Image" class="img-fluid">
                    </div>
                    <div class="product-item sm-height bg-gray">
                        <img src="style/images/model_6.png" alt="Image" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="title-section mb-5 col-12">
                    <h2 class="text-uppercase">Popular Products</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 item-entry mb-4">
                    <a href="#" class="product-item md-height bg-gray d-block">
                        <img src="style/images/prod_2.png" alt="Image" class="img-fluid">
                    </a>
                    <div class="row">
                        <div class="col-8">
                            <h2 class="item-title"><a href="#">Ford Mustang</a></h2>
                            <strong class="item-price">$13000.00</strong>
                            <div class="star-rating">
                                <span class="icon-star2 text-warning"></span>
                                <span class="icon-star2 text-warning"></span>
                                <span class="icon-star2 text-warning"></span>
                                <span class="icon-star2 text-warning"></span>
                                <span class="icon-star2 text-warning"></span>
                            </div>
                        </div>
                        <div class="col-4">
                            <button class="btn btn-outline-secondary float-right">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 item-entry mb-4">
                    <a href="#" class="product-item md-height bg-gray d-block">
                        <img src="style/images/prod_3.png" alt="Image" class="img-fluid">
                    </a>
                    <div class="row">
                        <div class="col-8">
                            <h2 class="item-title"><a href="#">Lexus RX-350</a></h2>
                            <strong class="item-price">
                                <del>$24000.00</del>
                                $17000.00</strong>
                        </div>
                        <div class="col-4">
                            <button class="btn btn-outline-secondary float-right">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 item-entry mb-4">
                    <a href="#" class="product-item md-height bg-gray d-block">
                        <img src="style/images/car_1.png" alt="Image" class="img-fluid">
                    </a>
                    <div class="row">
                        <div class="col-8">
                            <h2 class="item-title"><a href="#">Porsche Cayenne</a></h2>
                            <strong class="item-price">
                                <del>$18000.00</del>
                                $11000.00
                            </strong>
                        </div>
                        <div class="col-4">
                            <button class="btn btn-outline-secondary float-right">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="site-blocks-cover inner-page py-5" data-aos="fade">
        <div class="container">
            <div class="row">
                <div class="col-md-6 ml-auto order-md-2 align-self-start">
                    <div class="site-block-cover-content">
                        <h2 class="sub-title">#New Summer Collection 2019</h2>
                        <h1>New Shoes</h1>
                        <p><a href="#" class="btn btn-black rounded-0">Shop Now</a></p>
                    </div>
                </div>
                <div class="col-md-6 order-1 align-self-end">
                    <img src="style/images/model_6.png" alt="Image" class="img-fluid">
                </div>
            </div>
        </div>
    </div>
<%@ include file="WEB-INF/static/footer.jsp" %>
</div>
</div>
<script src="style/js/server-msg.js"></script>

<script src="style/js/main.js"></script>
<!--Validation scripts-->
<script src="style/js/validation/js_validation.js"></script>
<script src="style/js/validation/jquery_validation.js"></script>

</body>
</html>