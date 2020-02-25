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
          <div class="col-md-12 mb-0"><a href="/"><fmt:message key = "message.header.home"/></a> 
            <span class="mx-2 mb-0">/</span>
            <strong class="text-black"><fmt:message key = "message.header.shop"/></strong></div>
        </div>
      </div>
    </div>

    <form action="/shop" method="get">
    <div class="site-section">
        <div class="container">

            <div class="row">
                <div class="title-section mb-5 col-7">
                    <h2 class="text-uppercase"><fmt:message key = "message.shop.product_list"/></h2>
                </div>
                <div class="col-2">
                     <input type="number" id="lenPage" name="page_len" value="${requestScope.page_len}" placeholder="Page length">
                 </div>
                <div class="title-section mb-5 col-3">
                    <a href="#" class="icons-btn d-inline-block bag ml-4" data-toggle="modal"
                                data-target="#modalLRFormFilter">
                                <i class="fa fa-filter" aria-hidden="true"></i> <fmt:message key = "message.shop.filters"/>
                    </a>
                     <a href="">
                     <i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
                       <fmt:message key = "message.shop.sort"/>
                      </a>
                      
                        <select class="selectpicker" name="sort_type" onchange="this.form.submit()">
                            <c:choose>
                               <c:when test="${requestScope.sort_type == 'Name'}">
                                     <option >Name</option>
                               </c:when>
                               <c:otherwise>
                                    <option>Name</option>
                               </c:otherwise>
                           </c:choose>
                                                      <c:choose>
                               <c:when test="${requestScope.sort_type == 'Name desc'}">
                                     <option >Name desc</option>
                               </c:when>
                               <c:otherwise>
                                    <option>Name desc</option>
                               </c:otherwise>
                           </c:choose>
                           <c:choose>
                               <c:when test="${requestScope.sort_type == 'Price'}">
                                     <option selected>Price</option>
                               </c:when>
                               <c:otherwise>
                                    <option>Price</option>
                               </c:otherwise>
                            </c:choose>
                           <c:choose>
                               <c:when test="${requestScope.sort_type == 'Price desc'}">
                                     <option selected>Price desc</option>
                               </c:when>
                               <c:otherwise>
                                    <option>Price desc</option>
                               </c:otherwise>
                             </c:choose>
                        </select>

                </div>
                <!--Modal: Login / Register Form-->
                <div class="modal fade" id="modalLRFormFilter" tabindex="-1" role="dialog" aria-labelledby="modalFilters"
                aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered cascading-modal" role="document">
                        <!--Content-->
                        <div class="modal-content">
                            <!--Modal cascading tabs-->
                            <div class="modal-c-tabs">
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs md-tabs tabs-2 light-blue darken-3" role="tablist">
                                    <li class="nav-item">
                                        <a id="filter-switch" class="nav-link active" data-toggle="tab" href="#panel7" role="tab">
                                         <fmt:message key = "message.shop.filters"/>
                                        </a>
                                    </li>
                                </ul>

                        
                                    <div class="card">
                                        <article class="card-group-item">
                                            <header class="card-header">
                                                <h6 class="title">Product name</h6>
                                            </header>
                                            <div class="filter-content">
                                                <div class="card-body">
                                                    <input type="text" name="name_of_product" class="form-control" 
                                                    value="${requestScope.name_of_product}" placeholder="Product name">
                                                </div>
                                            </div>
                                        </article>
                                        <article class="card-group-item">
                                            <header class="card-header">
                                                <h6 class="title">Range input </h6>
                                            </header>
                                            <div class="filter-content">
                                                <div class="card-body">
                                                <div class="form-row">
                                                <div class="form-group col-md-6">
                                                  <label>Min</label>
                                                  <input type="number" name="price_from" class="form-control" id="inputEmail4" 
                                                  value="${requestScope.price_from}" placeholder="$0">
                                                </div>
                                                <div class="form-group col-md-6 text-right">
                                                  <label>Max</label>
                                                  <input type="number" name="price_to" class="form-control" 
                                                  value="${requestScope.price_to}" placeholder="$1,0000">
                                                </div>
                                                </div>
                                                </div> <!-- card-body.// -->
                                            </div>
                                        </article> <!-- card-group-item.// -->
                                        <article class="card-group-item">
                                            <header class="card-header">
                                                <h6 class="title">Categories </h6>
                                            </header>
                                            <div class="filter-content">
                                                <div class="card-body">
                                                    <c:forEach var="category" items="${requestScope.category_list}">
                                                        <div class="custom-control custom-checkbox">
                                                            <c:choose>
                                                                <c:when test="${requestScope.categories == null}">
                                                                     <input type="checkbox" class="custom-control-input" 
                                                                        id="${category.id}-${category.name}" name="categories" value="${category.name}" >
                                                                </c:when>
                                                                <c:when test="${fn:length(requestScope.category_list) == fn:length(requestScope.categories)}">
                                                                     <input type="checkbox" class="custom-control-input" 
                                                                        id="${category.id}-${category.name}" name="categories" value="${category.name}" checked >
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:forEach var="checked_ctg" items="${requestScope.categories}">
                                                                        <c:if test="${checked_ctg eq category.name}">
                                                                            <input type="checkbox" class="custom-control-input" 
                                                                            id="${category.id}-${category.name}" name="categories" value="${category.name}" 
                                                                                checked>
                                                                        </c:if>
                                                                        <c:if test="${checked_ctg != category.name}">
                                                                            <input type="checkbox" class="custom-control-input" 
                                                                            id="${category.id}-${category.name}" name="categories" value="${category.name}" >
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <label class="custom-control-label" for="${category.id}-${category.name}">${category.name}</label>
                                                        </div> <!-- form-check.// -->
                                                    </c:forEach>
                                                </div> <!-- card-body.// -->
                                            </div>

                                        </article> <!-- card-group-item.// -->
                                         <article class="card-group-item">
                                            <header class="card-header">
                                                <h6 class="title">Producers </h6>
                                            </header>
                                            <div class="filter-content">
                                                <div class="card-body">
                                                    <c:forEach var="producer" items="${requestScope.producer_list}">
                                                        <div class="custom-control custom-checkbox">
                                                           <c:choose>
                                                                <c:when test="${requestScope.producers == null}">
                                                                     <input type="checkbox" class="custom-control-input" 
                                                                        id="${producer.id}-${producer.name}" name="producers" value="${producer.name}" >
                                                                </c:when>
                                                                <c:when test="${fn:length(requestScope.producer_list) == fn:length(requestScope.producers)}">
                                                                     <input type="checkbox" class="custom-control-input" 
                                                                        id="${producer.id}-${producer.name}" name="producers" value="${producer.name}" checked >
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:forEach var="checked_ctg" items="${requestScope.producers}">
                                                                        <c:if test="${checked_ctg eq producer.name}">
                                                                            <input type="checkbox" class="custom-control-input" 
                                                                            id="${producer.id}-${producer.name}" name="producers" value="${producer.name}" 
                                                                                checked>
                                                                        </c:if>
                                                                        <c:if test="${checked_ctg != producer.name}">
                                                                            <input type="checkbox" class="custom-control-input" 
                                                                            id="${producer.id}-${producer.name}" name="producers" value="${producer.name}" >
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <label class="custom-control-label" for="${producer.id}-${producer.name}">${producer.name}</label>
                                                        </div> <!-- form-check.// -->
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </article>
                                        <article class="card-group-item">
                                            <div class="filter-content">
                                                <div class="card-body">
                                                     <div class="form-row">
                                                    <div class="form-group col-md-4">
                                                        <input type="submit" class="btn btn-info d-flex justify-content-center" value="Save Filters">
                                                    </div>
                                                    <div class="form-group col-md-8">
                                                          <a href="/shop">Delete filters</a>
                                                    </div>    
                                                    </div>   
                                                </div>         
                                            </div>
                                        </article>
                                    </div> <!-- card.// -->
                            </div>
                        </div>
                     </div>
                </div>                
            </div>
            <div class="row">
               
                    <c:forEach items="${requestScope.product_list}" var="product">  
                        <div class="col-lg-4 col-md-6 item-entry mb-4">
                            <a href="#" class="product-item md-height bg-gray d-block">
                                <img src="style/images/car_1.png" alt="Image" class="img-fluid">
                            </a>
                            <div class="row">
                                <div class="col-8">
                                    <h2 class="item-title"><a href="#">${product.name}</a></h2>
                                    <strong class="item-price">
                                        <del>$18000.00</del>
                                        ${product.price}
                                    </strong>
                                </div>
                                <div class="col-4">
                                    <button type="button" class="btn btn-outline-secondary float-right btn_add_c" onclick="add_to_cart(${product.id})">
                                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                        <fmt:message key = "message.shop.add_to_cart"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
              
            </div>
       
            <c:if test="${requestScope.num_pages > 0}">
                <nav class="d-flex justify-content-center">
                  <ul class="pagination">
                    <c:if test="${requestScope.current_page eq 1}">
                       <li class="page-item disabled">
                           <button type="submit" name="page_num" class="page-link" value="${requestScope.current_page - 1}"><fmt:message key = "message.shop.previous"/></button>
                       </li>
                     </c:if>
                     <c:if test="${requestScope.current_page > 1}">
                       <li class="page-item">
                           <button type="submit" name="page_num" class="page-link" value="${requestScope.current_page - 1}"><fmt:message key = "message.shop.previous"/></button>
                       </li>
                     </c:if>
                    <c:forEach var="i" begin="1" end="${requestScope.num_pages}">
                        <c:choose>
                             <c:when test="${requestScope.current_page == i}">
                                 <li class="page-item active">
                                    <input type="submit" name="page_num" class="page-link" value="${i}">
                                </li>
                             </c:when>
                             <c:otherwise>
                                 <li class="page-item">
                                    <input type="submit" name="page_num" class="page-link" value="${i}">
                                </li>
                             </c:otherwise>
                        </c:choose>
                    </c:forEach>  
                    <c:if test="${requestScope.current_page eq requestScope.num_pages}">
                       <li class="page-item disabled">
                           <button type="submit" name="page_num" class="page-link" value="${requestScope.current_page + 1}"><fmt:message key = "message.shop.next"/></button>
                       </li>
                     </c:if>
                     <c:if test="${requestScope.current_page != requestScope.num_pages}">
                       <li class="page-item">
                           <button type="submit" name="page_num" class="page-link" value="${requestScope.current_page + 1}"><fmt:message key = "message.shop.next"/></button>
                       </li>
                     </c:if>
                  </ul>
                </nav>
            </c:if>
            <c:if test="${requestScope.num_pages == 0}">
                <h1>Sorry, catalog is empty!</h1>
                <h5><a href="/shop">Delete filters</a></h5>
            </c:if>
 
        </div>
    </div>

 <%@ include file="../static/footer.jsp" %>
</form>
</div>

<script src="style/js/plugin/jquery-3.3.1.min.js"></script>
<script src="style/js/plugin/jquery-ui.js"></script>
<script src="style/js/plugin/popper.min.js"></script>
<script src="style/js/plugin/bootstrap.min.js"></script>
<script src="style/js/plugin/owl.carousel.min.js"></script>
<script src="style/js/plugin/jquery.magnific-popup.min.js"></script>
<script src="style/js/plugin/aos.js"></script>

<script src="style/js/main.js"></script>
<!--Validation scripts-->
<script src="style/js/validation/js_validation.js"></script>
<script src="style/js/validation/jquery_validation.js"></script>
<script type="text/javascript">
const node = document.getElementById("lenPage");
node.addEventListener("keyup", function(event) {
    if (event.key === "Enter") {
        // Do work
    }
});
</script>
</body>
</html>
