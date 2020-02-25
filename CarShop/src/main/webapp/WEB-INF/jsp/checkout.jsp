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
          <div class="col-md-12 mb-0"><a href="/">Home</a> <span class="mx-2 mb-0">/</span> <a href="/cart">Cart</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Ordering</strong></div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">
        <c:if test="${sessionScope.error_order != null}">
          <h2>${sessionScope.error_order}</h2>
        </c:if>
        <c:if test="${fn:length(sessionScope.order_products) == 0}">
          <h2>Order set is empty. Add something to the cart.</h2>
        </c:if>
        <c:if test="${fn:length(sessionScope.order_products) > 0}">
          <div class="row d-flex justify-content-center align-items-center">
            <div class="col-md-6">
              
              <div class="row mb-5">
                <div class="col-md-12">
                  <h2 class="h3 mb-3 text-black">Your Order</h2>
                  <div class="p-3 p-lg-5 border">
                    <table class="table site-block-order-table mb-5">
                      <thead>
                        <th>Product</th>
                        <th>Total</th>
                      </thead>
                      <tbody>
                        <c:forEach var="product" items="${sessionScope.order_products}">
                          <tr>
                            <td>${product.name}<strong class="mx-2">x</strong>${product.numbers}</td>
                            <td>$${product.price}</td>
                          </tr>
                        </c:forEach>
                           <tr>
                            <td class="text-black font-weight-bold"><strong>Order Total</strong></td>
                            <td class="text-black font-weight-bold"><strong>$${sessionScope.order_price}</strong></td>
                          </tr>            
                      </tbody>
                    </table>
                    <form action="/purchase" method="get">
                      
                    <div class="border p-3 mb-3">
                    <h3 class="h6 mb-0"><a class="d-block" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapsebank">Pay Type</a></h3>
                  
                      <div class="py-2">
                        <select class="form-control" name="pay_type">
                       
                              <option value="card">Card (free delivery)</option>
                              <option value="post_office">Post Office (pay after receiving)</option>
                       
                        </select>
                      </div>
                    </div>

                    <div class="form-group">
                      <button type="submit" class="btn btn-primary btn-lg btn-block">Place Order</button>
                    
                    </div>
                    </form>
                  </div>
                </div>
              </div>

            </div>
          </div>
        </c:if>
        <!-- </form> -->
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