<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:choose>
                <c:when test="${not empty sessionScope.loggedUser}">
                  <div href="/profile" class="icons-btn d-inline-block bag ml-5">
                      <img src="style/images/avatar/${sessionScope.loggedUser.username}.jpg" alt="Image" class="img-fluid" 
                      style="width: 50px; height: 50px;">
                  </div>
                  <a href="/profile" class="icons-btn d-inline-block bag">
                            ${sessionScope.loggedUser.username}
                  </a>
                  <a href="/sign_out" class="icons-btn d-inline-block bag ml-3">
                    <i class="fa fa-times-circle" aria-hidden="true"></i>
                  </a>
                </c:when>    
                <c:otherwise>
                    <a href="#" class="icons-btn d-inline-block bag ml-4" data-toggle="modal"
                            data-target="#modalLRForm">
                            <i class="fa fa-sign-in fa-lg" aria-hidden="true"></i>
                    </a>
                </c:otherwise>
</c:choose>