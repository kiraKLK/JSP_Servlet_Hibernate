<%-- 
    Document   : header
    Created on : Apr 22, 2023, 1:46:58 PM
    Author     : admin
--%>

<%@page import="com.bookstore.model.Author"%>
<%@page import="com.bookstore.dao.AuthorDAO"%>
<%@page import="com.bookstore.model.TypeOfBook"%>
<%@page import="java.util.List"%>
<%@page import="com.bookstore.dao.TypeOfBookDAO"%>
<%@page import="com.bookstore.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
    $("#searchInput").on("input", function() {
    var keyword = $(this).val();
            if (keyword.length >= 0) {
    $.ajax({
            type: "POST",
            url: "customer_controller?action=search_suggestions",
            data: { keyword: keyword },
            dataType: "json",
            success: function(response) {
                    var books = JSON.parse(response);
                    var suggestionsContainer = document.getElementById("suggetions-container");
                    suggestionsContainer.innerHTML = "";
                    for (var i = 0; i < books.length; i++) {
                        var book = books[i];
                        var bookInfo = "<p>" + book.bookName + "</p>"; // Thay title bằng thuộc tính bạn muốn hiển thị
                        suggestionsContainer.innerHTML += bookInfo;
                     }
            }
    });
    } else {
    $("#suggetions-container").empty();
    }
    }
    );
</script>
<!--Navbar-->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="home.jsp">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="home.jsp">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Combo giảm giá</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Thể loai
                    </a>
                    <%
                        TypeOfBookDAO typeOfBookDao = new TypeOfBookDAO();
                        List<TypeOfBook> listTypeOfBook = typeOfBookDao.selectALL();

                    %>


                    <ul class="dropdown-menu">
                        <%                            for (TypeOfBook result : listTypeOfBook) {
                        %>

                        <li><a class="dropdown-item" href="customer_controller?action=filter_by_type&typeId=<%= result.getTypeOfBookId()%>"><%= result.getTypeName()%></a> </li>
                        <li><hr class="dropdown-divider"></li>
                            <%  } %>
                    </ul>

                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Tác giả
                    </a>
                    <%
                        AuthorDAO authorDao = new AuthorDAO();
                        List<Author> listAuthor = authorDao.selectALL();

                    %>


                    <ul class="dropdown-menu">
                        <%                            for (Author result : listAuthor) {
                        %>

                        <li><a class="dropdown-item" role="button" href="customer_controller?action=filter_by_author&authorId=<%= result.getAuthorId()%>"><%= result.getFullName()%></a> </li>
                        <li><hr class="dropdown-divider"></li>
                            <%  } %>
                    </ul>

                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">Hết hàng</a>
                </li>
            </ul>
            <form class="d-flex" role="search" action="customer_controller">
                <input type="hidden" name="action" value="search"/>
                <input class="form-control me-2" id="searchInput" name="keyword" type="search" placeholder="Nội dung tìm kiếm" aria-label="Search">
                <div id="suggestions-container">
                    
                </div>
                <button class="btn btn-outline-success" type="submit">Tìm</button>
                <%
                    Object obj_header = session.getAttribute("customer");
                    Customer customer_header = null;
                    if (obj_header != null) {
                        customer_header = (Customer) obj_header;
                    }
                    if (customer_header == null) {
                %>
                <a class="btn btn-primary" style="white-space: nowrap;" href="login.jsp"> Đăng nhập  </a>
                <%
                } else {
                %>
                <!-- Default dropstart button -->
                <div class="btn-group dropstart">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        Tài khoản
                    </button>
                    <ul class="dropdown-menu">
                        <!-- Dropdown menu links -->
                        <li><a class="dropdown-item" href="cart.jsp">Đơn hàng của tôi</a></li>
                        <li><a class="dropdown-item" href="change_avatar.jsp">Thay đổi avatar</a></li>
                        <li><a class="dropdown-item" href="change_information.jsp">Thay đổi thông tin</a></li>
                        <li><a class="dropdown-item" href="changepassword.jsp">Đổi mật khẩu</a></li>
                        <li><a class="dropdown-item" href="customer_controller?action=logout">Đăng xuất</a></li> 
                    </ul>
                </div>
                <%
                    }
                %>
            </form>
        </div>
    </div>
</nav>
<!--NavbarEnd-->
