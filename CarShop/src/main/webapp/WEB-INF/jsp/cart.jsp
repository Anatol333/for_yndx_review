<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../static/head.jspf" %>
<body>


<div class="site-wrap">
    <%@ include file="../static/header.jsp" %>

    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="/">Home</a> <span class="mx-2 mb-0">/</span><strong class="text-black">Cart</strong></div>
        </div>
      </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row mb-5">
                <form class="col-md-12" method="post">
                    <div class="site-blocks-table">
                        <c:if test="${fn:length(sessionScope.products_cart) == 0}">
                            <h2>${sessionScope.error_cart}</h2>
                        </c:if>
                        <c:if test="${fn:length(sessionScope.products_cart) > 0}">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th class="product-thumbnail">Image</th>
                                    <th class="product-name">Product</th>
                                    <th class="product-price">Price</th>
                                    <th class="product-quantity">Quantity</th>
                                    <th class="product-remove">Remove</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${sessionScope.products_cart}">
                                        <tr>
                                            <td class="product-thumbnail">
                                                <img src="style/images/car_1.png" alt="Image" class="img-fluid">
                                            </td>
                                            <td class="product-name">
                                                <h2 class="h5 text-black">${product.key.name}</h2>
                                            </td>
                                            <td>${product.key.price}$</td>
                                            <td>
                                                <div class="input-group mb-3" style="max-width: 120px;">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-outline-primary" type="button" onclick="minus_in_cart(${product.key.id})">&minus;
                                                        </button>
                                                    </div>
                                                    <input type="text" class="form-control text-center cart-${product.key.id}" value="${product.value}" placeholder=""
                                                           aria-label="Example text with button addon"
                                                           aria-describedby="button-addon1">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-outline-primary" onclick="plus_in_cart(${product.key.id})" type="button">&plus;
                                                        </button>
                                                    </div>
                                                </div>

                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-primary height-auto btn-sm" 
                                                    onclick="remove_in_cart(${product.key.id})" >X</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </form>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="row mb-5">
                        <div class="col-md-6">
                            <a href="/shop"><button class="btn btn-outline-primary btn-sm btn-block">Continue Shopping</button></a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label class="text-black h4" for="coupon">Coupon</label>
                            <p>Enter your coupon code if you have one.</p>
                        </div>
                        <div class="col-md-8 mb-3 mb-md-0">
                            <input type="text" class="form-control py-3" id="coupon" placeholder="Coupon Code">
                        </div>
                        <div class="col-md-4">
                            <button class="btn btn-primary btn-sm px-4">Apply Coupon</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 pl-5">
                    <div class="row justify-content-end">
                        <div class="col-md-7">
                            <div class="row">
                                <div class="col-md-12 text-right border-bottom mb-5">
                                    <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
                                </div>
                            </div>
                            <div class="row mb-5">
                                <div class="col-md-6">
                                    <span class="text-black">Total</span>
                                </div>
                                <div class="col-md-6 text-right">
                                    <strong id="cart_price" class="text-black">
                                        <c:if test="${sessionScope.cart_price == null}">0.0</c:if>
                                        <c:if test="${sessionScope.cart_price != null}">$${sessionScope.cart_price}</c:if>
                                    </strong>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <form action="/order" method="post">
                                        <button type="submit" class="btn btn-primary btn-lg btn-block">Proceed To Checkout</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="../static/footer.jsp" %>
</div>

<script src="style/js/plugin/jquery-3.3.1.min.js"></script>
<script src="style/js/plugin/jquery-ui.js"></script>
<script src="style/js/plugin/popper.min.js"></script>
<script src="style/js/plugin/bootstrap.min.js"></script>
<script src="style/js/plugin/owl.carousel.min.js"></script>
<script src="style/js/plugin/jquery.magnific-popup.min.js"></script>
<script src="style/js/plugin/aos.js"></script>
<script src="style/js/server-msg.js"></script>

<script src="style/js/main.js"></script>
<!--Validation scripts-->
<script src="style/js/validation/js_validation.js"></script>
<script src="style/js/validation/jquery_validation.js"></script>

</body>
</html>