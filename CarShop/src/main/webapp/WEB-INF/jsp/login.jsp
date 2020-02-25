<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tags/captcha.tld" prefix="cap" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../static/head.jspf" %>
<body>

<div class="site-wrap">
    <%@ include file="../static/header.jsp" %>

    <div class="site-blocks-cover" style="min-height: 150vh">
        <div class="container mt-5" >
            <div class="row">
                <div class="col-md-6 mx-auto">
                    <!--Panel 8-->
                    <!--Body-->
                    <div class="errorMsg alert alert-danger m-3" role="alert" style="display:none;">
                        This is a danger alert—check it out!
                    </div>
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
                                                <input type="hidden" name="from" value="${requestScope.from}">
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
                    <!--/.Panel 8-->

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