<%-- 
    Document   : changepassword
    Created on : Apr 20, 2023, 9:44:38 PM
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
      <style>
            .red {
                color: red;
            }
            body {
                height: 100%;
            }

            body {
                display: flex;
                align-items: center;
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                max-width: 330px;
                padding: 15px;
            }

            .form-signin .form-floating:focus-within {
                z-index: 2;
            }

            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }

            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
        </style>
    </head>
    <body>
        
        
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
                String error = (request.getAttribute("error")+"").equals("null") ? "" : request.getAttribute("error")+""  ;

        %>
        <main class="form-signin w-100 m-auto">
            <form class="text-center" action="customer_controller" method="post">
                <input type="hidden" name="action" value="change_password"/>
                <h1 class="h3 mb-3 fw-normal">Thay đổi mật khẩu</h1>
                <span class="red"><%=error%></span>
                <div class="form-floating">
                    <input type="password" class="form-control" id="old_password" name="old_password" placeholder="Mật khẩu cũ" required="required">
                    <label for="old_password">Mât khẩu cũ</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="new_password" name="new_password" placeholder="Mật khẩu mới" required="required">
                    <label for="new_password ">Mật khẩu mới</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="reenter_new_password" name="reenter_new_password" placeholder="Nhập lại mật khẩu mới" required="required">
                    <label for="reenter_new_password ">Nhập lại mật khẩu mới</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit" value="login">Thay đổi</button>
                <a class="w-100 btn btn-lg btn-primary  mt-4" href="home.jsp" role="button">Quay về trang chủ</a>
            </form>
                <%@include file="footer.jsp" %>
        </main>
    <%}%>
            
        
        
    </body>
</html>
