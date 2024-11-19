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
            String error = (request.getAttribute("error")+"").equals("null") ? "" : (request.getAttribute("error")+"");
            String username = request.getAttribute("username")+"";
            username = (username.equals("null")) ? "" : username;
            String password = request.getAttribute("password")+"";
            password = (password.equals("null")) ? "" : password;
            String reenter_password = request.getAttribute("reenter_password")+"";
            reenter_password = (reenter_password.equals("null")) ? "" : reenter_password;
            String fullName = request.getAttribute("fullName")+"";
            fullName = (fullName.equals("null")) ? "" : fullName;
            String gender = request.getAttribute("gender")+"";
            gender = (gender.equals("null")) ? "" : gender;
            String address = request.getAttribute("address")+"";
            address = (address.equals("null")) ? "" : address;
            String birthday = request.getAttribute("birthday")+"";
            birthday = (birthday.equals("null")) ? "" : birthday;
            String address_of_purchaser = request.getAttribute("address_of_purchaser")+"";
            address_of_purchaser = (address_of_purchaser.equals("null")) ? "" : address_of_purchaser;
            String phone_number = request.getAttribute("phone_number")+"";
            phone_number = (phone_number.equals("null")) ? "" : phone_number;
            String email = request.getAttribute("email")+"";
            email = (email.equals("null")) ? "" : email;
            String access_received_email  = request.getAttribute("access_received_email")+"";
            access_received_email = (access_received_email.equals("on")) ? access_received_email : "";
            System.out.println(access_received_email);
        %>
        <div class="container">
            <h1 class="text-center">Đăng kí</h1>
            <div class="red" id="error">
                <%=error%>
            </div>
            <form class="form" action="customer_controller" method="post">
                 <input type="hidden" name="action" value="register"/>
                <div class="row">
                    <div class="col">
                        <h3>Thông tin tài khoản</h3>
                        <div class="mb-3">
                            <label for="user_name" class="form-label">Tên tài khoản</label><span class="red">*</span>
                            <input type="text" class="form-control" id="user_name" name="user_name" required="required" value="<%=username%>">
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Mật khẩu</label><span class="red">*</span>
                            <input type="password" class="form-control" id="password" name="password" required="required">
                        </div>

                        <div class="mb-3">
                            <label for="reenter_password" class="form-label">Nhập lại mật khẩu</label><span id="msg" class="red">*</span> 
                            <input type="password" class="form-control" id="reenter_password" name="reenter_password" required="required" onkeyup="checkPassword()">
                        </div>

                        <hr>
                        <h3>Thông tin khách hàng</h3>
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Họ và tên</label>
                            <input type="text" class="form-control" id="fullName" name="fullName" value="<%=fullName%>">
                        </div>

                        <div class="mb-3">
                            <label for="gender" class="form-label">Giới tính</label>
                            <select class="form-control" id="gender" name="gender">
                                <option></option>
                                <option value="male"<%=(gender.equals("male")) ? "selected = 'selected'" : ""%>>Nam</option>
                                <option value="female" <%=(gender.equals("female")) ? "selected = 'selected'" : ""%> >Nữ</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="birthday" class="form-label">Ngày sinh</label>
                            <input type="date" class="form-control" id="birthday" name="birthday" value="<%=birthday%>" required="required" >
                        </div>
                    </div>
                    <div class="col">
                        <h3>Địa chỉ</h3>
                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" id="address" name="address" value="<%=address%>">
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="<%=email%>">
                        </div>

                        <hr/>
                        <div class="mb-3">
                            <label for="agree_terms" class="form-label">Đồng ý với điều khoản sử dụng. <a>terms<a/></label>
                            <input type="checkbox" class="form-check-input" id="agree_terms" name="agree_terms" required="required"   onchange="choesAccess()" />
                        </div>

                        <div class="mb-3">
                            <label for="access_received_email" class="form-label">Đồng ý nhận email.</label>
                            <input type="checkbox" class="form-check-input" id="access_received_email" name="access_received_email" <% if (access_received_email.equals("on")) { %>checked<% } %>>
                        </div>
                        <input class="btn btn-primary form-control" type="submit" value="register" name="submit" id="submit" style="visibility: hidden;" />
                    </div>

                </div>
            </form>
        </div>
                            <%@include file="footer.jsp" %>
    </body>
    
    <script>
        function checkPassword() {
            password = document.getElementById("password").value;
            reenter_password = document.getElementById("reenter_password").value;
            if(password !== reenter_password) {
                document.getElementById("msg").innerHTML = "*Reenter password not equals password";
            }else {
                document.getElementById("msg").innerHTML = "*";
            }
        }
        
        function choesAccess() {
            access = document.getElementById("agree_terms");
            if(access.checked === true) {
                document.getElementById("submit").style.visibility="visible";
            }else{
                 document.getElementById("submit").style.visibility="hidden";
            }
          
        }
    </script>
    
</html>
