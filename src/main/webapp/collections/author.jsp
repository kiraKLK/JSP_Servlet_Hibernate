<%-- 
    Document   : author
    Created on : Aug 27, 2023, 4:42:35 PM
    Author     : admin
--%>

<%@page import="com.bookstore.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.bookstore.dao.BookDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="/BookStore/header.jsp"></jsp:include>
            <div class="container">
                <!--Slider and product-->
                <div class="col-lg-12 ">
                    <!--slider-->
                    <div id="carouselExampleCaptions" class="carousel slide">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="https://file.hstatic.net/200000123069/file/ntmn_tai_ban_hrv__1920x1201__95434a67941b471cba82cda84c4b518c.png" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">

                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="https://file.hstatic.net/200000123069/file/haravan_home_de7aa1f6b5994f9dbdef93661b62513d.png" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">

                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="https://file.hstatic.net/200000123069/file/haravan_88b93c800b314f46b716b3120da1846d.jpg" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">

                                </div>
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                    <!--end slider-->
                    
                    <!--product cart-->
                    <div class="row">
                    <%
                        String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                                + request.getContextPath();
                        System.out.println(url1);
                        Object obj_list_book = session.getAttribute("list_book");
                        
                        List<Book> listBook = (List<Book>) obj_list_book;
                        for (Book result : listBook) {
                    %>
                    <div class="col-lg-3 col-md-6 mb-4 " >
                        <div class="card" style="width: 18rem;">
                            <img src="<%=url1 %>/CoverBook/<%= result.getImagePath() %>" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title"><%= result.getFullName()%> </h5>
                                <p class="card-text"><%= result.getSellingPrice()%></p>                               
                                <a href="#" class="btn btn-primary">Mua</a>
                                <a href="customer_controller?action=add_to_cart&bookId=<%= result.getBookId() %>" class="btn btn-primary ">Thêm vào giỏ hàng</a>
                            </div>
                        </div>
                    </div>
                    <% }%>
          
                </div>
                        
                    
                    <!--end product-->
                    
                </div>
                
            <%@include file ="/BookStore/footer.jsp"%>
    </body>
</html>
