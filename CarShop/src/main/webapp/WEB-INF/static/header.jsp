<header>
    <div id="messageServerError" class="server-error">
        <c:if test="${not empty sessionScope.errorMessage}">
        <c:out value="${sessionScope.errorMessage}"/>
    </c:if>  
</div>
<div id="messageServerSuccess" class="server-success">
   <c:if test="${not empty sessionScope.successMessage}">
   <c:out value="${sessionScope.successMessage}"/>
</c:if>
</div>

<div class="site-navbar bg-white py-2">

    <div class="search-wrap">
        <div class="container">
            <a href="#" class="search-close js-search-close"><span class="icon-close2"></span></a>
            <form action="/shop" method="get">
                <input type="text" class="form-control" name="name_of_product" placeholder="Search keyword and hit enter...">
            </form>
        </div>
    </div>


    <div class="container">
        <div class="d-flex align-items-center justify-content-between">
            <div class="logo">
                <div class="site-logo">
                    <a href="/" class="js-logo-clone">CarShop</a>
                </div>
            </div>
            <div class="main-nav d-none d-lg-block">
                <nav class="site-navigation text-right text-md-center" role="navigation">
                    <ul class="site-menu js-clone-nav d-none d-lg-block">
                        <li class="has-children active">
                            <a href="/"><fmt:message key = "message.header.home"/></a>
                            <ul class="dropdown">
                                <li><a href="/">Menu One</a></li>
                                <li><a href="#">Menu Two</a></li>
                                <li><a href="#">Menu Three</a></li>
                                <li class="has-children">
                                    <a href="#">Sub Menu</a>
                                    <ul class="dropdown">
                                        <li><a href="#">Menu One</a></li>
                                        <li><a href="#">Menu Two</a></li>
                                        <li><a href="#">Menu Three</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>

                        <li><a href="/shop"><fmt:message key = "message.header.shop"/></a></li>
                        <li><a href="#"><fmt:message key = "message.header.new_arrivals"/></a></li>
                        <li><a href="/contact"><fmt:message key = "message.header.contacts"/></a></li>
                               <li>
                                 <tag:lang/>
                          </li>
                    </ul>
                </nav>
            </div>
            <div class="icons">
                <a href="#" class="icons-btn d-inline-block js-search-open"><span class="icon-search"></span></a>
                <a href="#" class="icons-btn d-inline-block"><span class="icon-heart-o"></span></a>
                 <a href="/cart" class="icons-btn d-inline-block bag">
                    <span class="icon-shopping-bag" ></span>
                    <span id="cart_numbers" class="number">
                         <c:if test="${fn:length(sessionScope.products_cart) == 0}">0</c:if>
                         <c:if test="${fn:length(sessionScope.products_cart) > 0}">${sessionScope.count_cart}</c:if>
                    </span>
                </a>
            
            <tag:auth_info/>

            <a href="#" class="site-menu-toggle js-menu-toggle ml-3 d-inline-block d-lg-none">
                <span class="icon-menu"></span>
            </a>
        </div>
    </div>
</div>
</div>

<!--Modal: Login / Register Form-->
<div class="modal fade" id="modalLRForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
aria-hidden="true">
<div class="modal-dialog modal-dialog-centered cascading-modal" role="document">
<!--Content-->
<div class="modal-content">
    <!--Modal cascading tabs-->
    <div class="modal-c-tabs">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs md-tabs tabs-2 light-blue darken-3" role="tablist">
            <li class="nav-item">
                <a id="login_switch" class="nav-link active" data-toggle="tab" href="#panel7" role="tab"><i
                    class="fa fa-user-o"></i>
                Login</a>
            </li>
        </ul>
        <!-- Tab panels -->
        <div class="tab-content">
            <!--Panel 7-->
            <div class="tab-pane fade in show active" id="panel7" role="tabpanel">
                <!--Body-->
                <div class="errorMsg alert alert-danger m-3" role="alert" style="display:none;">
                    This is a danger alert—check it out!
                </div>
                <form id="login_form" action="login" method="post">
                    <div class="modal-body mb-1">
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-envelope"></i>
                            <label data-error="wrong" data-success="right" for="modalLUsername">
                            Your username or email</label>
                            <input id="modalLUsername" name="username" class="form-control form-control-sm" value="${sessionScope.usernameLogin}">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-key"></i>
                            <label data-error="wrong" data-success="right" for="modalLPassword">
                            Your password</label>
                            <input type="password" id="modalLPassword" name="password" class="form-control form-control-sm">
                        </div>
                        <div class="text-center mt-2">
                            <button id="login_btn" class="btn btn-info" onclick="loginValidationJs();">
                                Log in
                                <i class="fa fa-sign-in" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <!--Footer-->
                <div class="modal-footer">
                    <div class="options text-center text-md-right mt-1">
                        <p>Not a member? <a href="/reg" class="blue-text">Sign Up</a></p>
                        <p>Forgot <a href="#" class="blue-text">Password?</a></p>
                    </div>
                    <button type="button" class="btn btn-outline-info waves-effect ml-auto"
                    data-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>

</div>
</div>
<!--/.Content-->
</div>
</div>
</header>