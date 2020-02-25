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
                    <form id="reg_form" action="reg" method="post" enctype="multipart/form-data">

                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-user" aria-hidden="true"></i>
                            <label data-error="wrong" data-success="right" for="modalRUsername">
                                Your username</label>
                            <input id="modalRUsername" value="${sessionScope.usernameReg}"
                                   class="form-control form-control-sm validate" name="username">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-users" aria-hidden="true"></i>
                            <label data-error="wrong" data-success="right" for="modalRName">
                                Real Name</label>
                            <input type="text" id="modalRName" value="${sessionScope.real_name}"
                                   name="real_name" class="form-control form-control-sm validate">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-users" aria-hidden="true"></i>
                            <label data-error="wrong" data-success="right" for="modalRSurname">
                                Surname</label>
                            <input type="text" id="modalRSurname" value="${sessionScope.surname}"
                                   name="surname" class="form-control form-control-sm validate">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                            <label data-error="wrong" data-success="right" for="modalREMail">
                                Your email</label>
                            <input type="email" id="modalREMail" value="${sessionScope.emailReg}"
                                   name="email" class="form-control form-control-sm validate">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                            <label data-error="wrong" data-success="right" for="modalRPassword">
                                Your password</label>
                            <input type="password" id="modalRPassword" name="password"
                                   class="form-control form-control-sm validate">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                            <label data-error="wrong" data-success="right" for="modalRPasswordRep">
                                Repeat password</label>
                            <input type="password" id="modalRPasswordRep" name="passwordRep"
                                   class="form-control form-control-sm validate">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-picture-o" aria-hidden="true"></i>
                            <label data-error="wrong" data-success="right" for="modalRPhoto">
                                Photo Profile</label>
                            <input type="file" id="modalRPhoto" name="photo"
                                   class="form-control form-control-sm validate">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <div class="d-flex justify-content-center">
                                <cap:captcha/>
                            </div>
                            <div class="d-flex justify-content-center">
                                <label data-error="wrong" data-success="right" for="modalRPasswordRep">
                                    Input captcha
                                </label>
                            </div>
                            <div class="col-md-6 mx-auto">
                                <input type="text" id="inpCaptcha" name="key_captcha"
                                       class="form-control form-control-sm validate">
                            </div>
                        </div>
                        <div class="md-form form-sm mb-3 ml-4">
                            <input class="form-check-input" type="checkbox" value="true" id="defaultCheck1" name="news">
                            <label class="form-check-label" for="defaultCheck1">
                                Receive newsletter from us
                            </label>
                        </div>
                        <div class="text-center form-sm mb-3">
                            <button id="sign_in_btn" class="btn btn-info">
                                Sign up
                                <i class="fa fa-sign-in" aria-hidden="true"></i>
                            </button>
                        </div>

                    </form>
                    <!--Footer-->
                    <div class="modal-footer">
                        <div class="options text-right">
                            <p class="pt-1">Already have an account?
                                <a href="#" class="icons-btn d-inline-block bag ml-4" data-toggle="modal"
                                   data-target="#modalLRForm">
                                    Log In
                                </a>
                            </p>
                        </div>
                    </div>

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