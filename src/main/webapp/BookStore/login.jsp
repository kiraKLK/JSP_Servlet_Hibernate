<%-- 
    Document   : login
    Created on : Apr 19, 2023, 9:50:54 AM
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
        
        
        <main class="form-signin w-100 m-auto">
            
            <form class="text-center" action="customer_controller" method="post">
                
                <input type="hidden" name="action" value="login"/>
                <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
                <%
                    String error = (request.getAttribute("error")+"").equals("null") ? "" : request.getAttribute("error")+"" ;
                    //error = (error.equals("null")) ? "" : error;
                %>
                <div class="text-center"><span class="red"><%=error%></span></div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="user_name" name="user_name" placeholder="User name" required="required">
                    <label for="user_name">User name</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
                    <label for="password ">Password</label>
                </div>

                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit" value="login">Sign in</button>
                
                <a href="register.jsp">New account</a>
            </form>
                 <%@include file="footer.jsp" %>
        </main>
                
               
    </body>
</html>
