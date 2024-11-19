<%-- 
    Document   : register
    Created on : Apr 18, 2023, 6:01:07 PM
    Author     : admin
--%>


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
        </style>
    </head>
    <body>
        <%@include file="header.jsp"%>

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
        <%           
            String fullName = cus.getFullName();
            String gender = cus.getGender();
            String address = cus.getAddress();
            String email = cus.getEmail();
            boolean access_received_email = cus.isRegisterGetAdv();
        %>

        <div class="container">
            <h1 class="text-center">Thay đổi thông tin</h1>
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
                            <label for="gender" class="form-label">Giới tính</label>
                            <select class="form-control" id="gender" name="gender">
                                <option></option>
                                <option value="Nam" <%= (gender.equals("Nam")) ? "selected='selected'":"" %>>Nam</option>
                                <option value="Nữ"  <%= (gender.equals("Nữ")) ? "selected='selected'":"" %>>Nữ</option>
                            </select>
                        </div>


                    </div>
                    <div class="col">
                        <h3>Địa chỉ</h3>
                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ giao hàng</label>
                            <input type="text" class="form-control" id="address" name="address" value="<%=address%>">
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="<%=email%>">
                        </div>

                        <hr/>


                        <div class="mb-3">
                            <label for="access_received_email" class="form-label">Đồng ý nhận email</label>
                            <input type="checkbox" class="form-check-input" id="access_received_email" name="access_received_email"  <% if (access_received_email) { %>checked<% } %> >
                        </div>
                        <input class="btn btn-primary form-control" type="submit" value="Thay đổi" name="submit" id="submit" />
                        <a class="btn btn-primary container mt-4" href="home.jsp" role="button">Quay về trang chủ</a>

                    </div>

                </div>
            </form>
        </div>
        <%}%>

        <%@include file="footer.jsp" %>
    </body>

    <script>
        function checkPassword() {
            password = document.getElementById("password").value;
            reenter_password = document.getElementById("reenter_password").value;
            if (password !== reenter_password) {
                document.getElementById("msg").innerHTML = "*Reenter password not equals password";
            } else {
                document.getElementById("msg").innerHTML = "*";
            }
        }

        function choesAccess() {
            access = document.getElementById("agree_terms");
            if (access.checked === true) {
                document.getElementById("submit").style.visibility = "visible";
            } else {
                document.getElementById("submit").style.visibility = "hidden";
            }

        }
    </script>

</html>
