<%-- 
    Document   : change_avatar
    Created on : Apr 24, 2023, 3:06:38 PM
    Author     : admin
--%>

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
            Customer customer = null;
            if (obj != null) {
                customer = (Customer) obj;
            }
            if (customer == null) {
        %>
        <h1>Bạn chưa đăng nhập vào hệ thống. Vui lòng quay lại trang chủ!</h1>
        <%
        } else {

        %>
        <div class="container">
            <%            String error = request.getAttribute("error") + "";
                error = (error.equals("null")) ? "" : error;

                String duongDanAnh = customer.getPathAvatarImages();
                System.out.println(duongDanAnh);

            %>
            <div class="container">
                <div class="text-center">
                    <h1>THÔNG TIN TÀI KHOẢN</h1>
                </div>

                <div class="red" id="baoLoi">
                    <%=error%>
                </div>
                <%
                    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                            + request.getContextPath();
                    System.out.println(url1);
                %>
                <form class="form" action="<%=url1%>/BookStore/change_avatar" method="post" enctype="multipart/form-data">
                    <!--<input type="hidden" name="action" value="change_avatar"/>-->
                    <div class="row">
                        <div class="col-sm-6">

                            <h3>Thông tin khách hàng</h3>
                            <img src="<%=url1%>/avatar/<%=duongDanAnh%>" alt="Ảnh Avatar"/>        
                            <div class="mb-3">
                                <label for="duongDanAnh" class="form-label">Đường dẫn ảnh</label>
                                <input type="file" class="form-control" id="duongDanAnh" name="duongDanAnh">
                            </div>
                            <input class="btn btn-primary form-control" type="submit"
                                   value="Lưu thông tin" name="submit" id="submit" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <%}%>
        <%@include file ="footer.jsp"%>
    </body>
</html>
