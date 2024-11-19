<%-- 
    Document   : home
    Created on : Apr 14, 2023, 8:33:50 AM
    Author     : admin
--%>


<%@page import="com.bookstore.dao.BookDAO"%>
<%@page import="com.bookstore.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Kira Store</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <!--Page content-->
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
                    <br>
                    <!--products card-->
                    <div class="row">
                    <%
                        String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                                + request.getContextPath();
                        System.out.println(url1);
                        BookDAO bookDao = new BookDAO();
                        List<Book> listBook = bookDao.selectALL();
                        for (Book result : listBook) {
                    %>
                    <div class="col-lg-3 col-md-6 mb-4 " >
                        <div class="card" style="width: 18rem;">
                            <img src="<%=url1%>/CoverBook/<%= result.getImagePath()%>" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title" style="overflow: hidden;
                                    -webkit-line-clamp: 1;
                                    -webkit-box-orient: vertical;
                                    display: -webkit-box;"><%= result.getFullName()%> </h5>
                                <p class="card-text"><%= result.getSellingPrice()%></p>                               
                                <a href="checkouts.jsp" class="btn btn-primary">Mua</a>
                                <a href="customer_controller?action=add_to_cart&bookId=<%= result.getBookId()%>" class="btn btn-primary ">Thêm vào giỏ hàng</a>
                            </div>
                        </div>
                    </div>
                    <% }%>

                </div>
                <!--end products-->
            </div>
        </div>
        <!--End Page Content-->

        <!-- footer -->
        <%@include file ="footer.jsp"%>

    </body>
</html>
