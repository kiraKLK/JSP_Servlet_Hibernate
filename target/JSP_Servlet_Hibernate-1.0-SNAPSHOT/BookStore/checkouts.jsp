<%-- 
    Document   : checkouts
    Created on : Aug 28, 2023, 6:15:27 PM
    Author     : admin
--%>

<%@page import="java.util.Map"%>
<%@page import="com.bookstore.model.DetailOrder"%>
<%@page import="com.bookstore.model.Customer"%>
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
        <jsp:include page="header.jsp"></jsp:include>

        <%
            Object obj = session.getAttribute("customer");
            Customer cus = null;
            if (obj != null) {
                cus = (Customer) obj;
            }
            if (cus == null) {
        %>
        <h1>đã đăng nhập đâu mà đòi đổi mật khẩu bạn ei!</h1>
        <% } else {
            String error = (request.getAttribute("error") + "").equals("null") ? "" : request.getAttribute("error") + "";

        %>
        <%            String fullName = cus.getFullName();
            String gender = cus.getGender();
            String address = cus.getAddress();
            String email = cus.getEmail();
            boolean access_received_email = cus.isRegisterGetAdv();
        %>

        <div class="container">
            <h1 class="text-center">Kira Store</h1>
            <div class="red" id="error">
                <%=error%>
            </div>
            <form class="form" action="customer_controller" method="post">
                <input type="hidden" name="action" value="change_information"/>
                <div class="row">
                    <div class="col">
                        <h3>Thông tin khách hàng</h3>
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Họ và tên</label>
                            <input type="text" class="form-control" id="fullName" name="fullName" value="<%=fullName%>">
                        </div>

                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ giao hàng</label>
                            <input type="text" class="form-control" id="address" name="address" value="<%=address%>">
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="<%=email%>">
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Số điện thoại</label>
                            <input type="tel" class="form-control" id="email" name="email" value="">
                        </div>




                    </div>
                    <div class="col">
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
                                    int sum = 0;
                                    Object obj_cart = session.getAttribute("cart");
                                    Map<String, DetailOrder> map = null;
                                    
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
                                    <td></td>
                                    <td>Tổng tiền:</td>
                                    <td><%= sum%> VND</td>
                                </tr>
                            </tbody>
                        </table>


                    </div>

                </div>
            </form>
        </div>
        <%}%>
        <%@include file ="footer.jsp"%>
    </body>
</html>
