<%-- 
    Document   : cart
    Created on : Aug 21, 2023, 11:06:35 PM
    Author     : admin
--%>

<%@page import="java.lang.Object"%>
<%@page import="java.util.Map"%>
<%@page import="com.bookstore.model.DetailOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.bookstore.model.Book"%>
<%@page import="com.bookstore.dao.BookDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kira Store</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Sản Phẩm</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Số Lượng</th>
                        <th scope="col">Thành Tiền</th>                    
                    </tr>
                </thead>
                <tbody>
                <%
                    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                            + request.getContextPath();
                    System.out.println(url1);
                    int count = 0;
                    Object obj_cart = session.getAttribute("cart");
                    Map<String, DetailOrder> map = null;
                    int sum = 0;
                    if (obj_cart != null) {
                        map = (Map<String, DetailOrder>) obj_cart;

                        for (Map.Entry<String, DetailOrder> entry : map.entrySet()) {
                            count++;
                            String key = entry.getKey();
                            DetailOrder detailOrder = entry.getValue();
                            sum += detailOrder.getSumMoney();
                %>

                <tr>                
                    <th scope="row"><%= count%></th>
                    <td>
                        <div class="card mb-3" style="max-width: 540px;">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="<%=url1%>/CoverBook/<%= detailOrder.getBook().getImagePath()%>" class="img-fluid rounded-start" alt="...">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title"><%= detailOrder.getBook().getFullName()%></h5>
                                        <p class="card-text">Tác giả: <%= detailOrder.getBook().getAuthor().getFullName()%></p>
                                        <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </td>

                    <td><%= detailOrder.getBook().getSellingPrice()%></td>
                    <td><%= detailOrder.getQuantity()%></td>
                    <td><%= detailOrder.getSumMoney()%></td>

                </tr>

                <% }
                
                    }%>
                    
                    <tr>
                        <td></td>
                        <td></td>
                        <td>Tổng số tiền:</td>
                        <td><%= sum %> VND</td>
                        <td><a href="checkouts.jsp" class="btn btn-primary">Thanh toán</a></td>
                    </tr>
                    
            </tbody>
        </table>


        <%@include file ="footer.jsp"%>
    </body>
</html>
