<%-- 
    Document   : AddProduct
    Created on : Apr 12, 2023, 9:17:14 PM
    Author     : admin
--%>

<%@page import="com.bookstore.dao.BookDAO"%>
<%@page import="com.bookstore.model.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form add product</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
        <style>

            .rq {
                color: red;
            }
        </style>
        <script>
            function my_submit() {
                error = "";
                productCode = document.getElementById("productCode").value;
                productName = document.getElementById("productName").value;
                productPrice = document.getElementById("productPrice").value;
                productEntryPrice = document.getElementById("productEntryPrice").value;
                productVat = document.getElementById("vat").value;
                productDescribe = document.getElementById("describe").value;
//                    if(productCode.length===0 || productName.length===0 || productVat.length===0){
//                        error = "you must fill this";
//                    }
//                    if(productPrice <= 0 || productEntryPrice <= 0){
//                        error_price = document.getElementById("error_price");
//                        error_price.innerHTML = "<span class='rq'>You must fill product price</span>";
//                        
//                        error_entry_price = document.getElementById("error_entry_price");
//                        error_entry_price.innerHTML = "<span class='rq'>You must fill product entry price</span>";
//                    }
//                    if(error.length > 0 || error_price > 0 || error_entry_price > 0 ){
//                        //alert(error);
//                        return;
//                    }else{
//                    my_form = document.getElementById("my_form");
//                    my_form.submit();
//                    }

                if (productCode.length === 0 || productName.length === 0 || productVat.length === 0 || productPrice <= 0 || productEntryPrice <= 0) {
                    return;
                } else {
                    my_form = document.getElementById("my_form");
                    my_form.submit();
                }
            }

            function editRow(button) {
                var row = button.parentNode.parentNode;
                var cells = row.getElementsByTagName("td");
                var cellID = row.getElementsByTagName("th");
                // Get the values of each cell and populate the input fields
                var bookId = cellID[0].innerText;
                var bookTitle = cells[0].innerText;
                var date = cells[1].innerText;
                var inputPrice = cells[2].innerText;
                var sellPrice = cells[3].innerText;
                var quantity = cells[4].innerText;
                var language = cells[5].innerText;
                var description = cells[6].innerText;
                var author = cells[7].innerText;
                var type = cells[8].innerText;
                document.getElementById("productCode").value = bookId;
                document.getElementById("language").value = language;
                document.getElementById("productName").value = bookTitle;
                document.getElementById("productEntryPrice").value = inputPrice;
                document.getElementById("productPrice").value = sellPrice;
                document.getElementById("dateOfPublishing").value = date;
                document.getElementById("quantity").value = quantity;
                document.getElementById("author").value = author;
                document.getElementById("typeOfBook").value = type;
                document.getElementById("description").value = description;
            }
            function emptyInputFields() {
                document.getElementById("productCode").value = "";
                document.getElementById("language").value = "";
                document.getElementById("productName").value = "";
                document.getElementById("productEntryPrice").value = "";
                document.getElementById("productPrice").value = "";
                document.getElementById("dateOfPublishing").value = "";
                document.getElementById("quantity").value = "";
                document.getElementById("author").value = "";
                document.getElementById("typeOfBook").value = "";
                document.getElementById("description").value = "";
            }



            //var row = button.parentNode.parentNode;
//            function deleteBook(bookId,button) {
//                if (confirm("Bạn có chắc chắn muốn xóa sách này?")) {
//                    var xhr = new XMLHttpRequest();
//                    xhr.open("POST", "save-product?action=delete_book"); // Thay đổi URL của servlet xử lý xóa dựa trên tên thật
//                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                    xhr.onreadystatechange = function () {
//                        if (xhr.readyState === XMLHttpRequest.DONE) {
//                            if (xhr.status === 200) {
//                                // Xóa dòng dữ liệu khỏi bảng sau khi xóa thành công
//                                var row = button.parentNode.parentNode;
//                                row.parentNode.removeChild(row);
//                            } else {
//                                alert("Xảy ra lỗi khi xóa sách.");
//                            }
//                        }
//                    };
//                    xhr.send("bookId=" + encodeURIComponent(bookId);
//                }
//            }




        </script>
    </head>
    <body>
        <%
            // error
            String error_productCode = request.getAttribute("error_productCode") + "";
            error_productCode = (error_productCode.equals("null")) ? "" : error_productCode;

            // forward value
            String value_productCode = request.getAttribute("value_productCode") + "";
            String value_productName = request.getAttribute("value_productName") + "";
            String value_productPrice = request.getAttribute("value_productPrice") + "";
            String value_productEntryPrice = request.getAttribute("value_productEntryPrice") + "";
            String value_dateOfPublishing = request.getAttribute("value_dateOfPublishing") + "";
            String value_description = request.getAttribute("value_description") + "";
            String value_quantity = request.getAttribute("value_quantity") + "";
            String value_language = request.getAttribute("value_language") + "";
            String value_author = request.getAttribute("value_author") + "";
            String value_typeOfBookString = request.getAttribute("value_typeOfBookString") + "";

            // condition value
            value_productCode = (value_productCode.equals("null")) ? "" : value_productCode;
            value_productName = (value_productName.equals("null")) ? "" : value_productName;
            value_productPrice = (value_productPrice.equals("null")) ? "" : value_productPrice;
            value_productEntryPrice = (value_productEntryPrice.equals("null")) ? "" : value_productEntryPrice;
            value_dateOfPublishing = (value_dateOfPublishing.equals("null")) ? "" : value_dateOfPublishing;
            value_description = (value_description.equals("null")) ? "" : value_description;
            value_quantity = (value_quantity.equals("null")) ? "" : value_quantity;
            value_language = (value_language.equals("null")) ? "" : value_language;
            value_author = (value_author.equals("null")) ? "" : value_author;
            value_typeOfBookString = (value_typeOfBookString.equals("null")) ? "" : value_typeOfBookString;

            BookDAO bookDao = new BookDAO();
            List<Object[]> listBook = bookDao.selectBookInformation();


        %>
        <div class="container mt-4"  >
            <form class="row g-3 needs-validation" id="my_form" action="save-product" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="add_product"/>
                <div class="row">
                    <div class="col-6">
                        <label for="productCode" class="form-label">Mã sản phẩm<span class="rq">:</span></label>
                        <!--<div id="productCode"></div>-->
                        <input type="text" class="form-control" id="productCode" name="productCode" value="<%=value_productCode%>" readonly>
                        <div class="rq"><%= error_productCode%></div>
                    </div>

                    <div class="col-6">
                        <label for="productName" class="form-label">Tên sản phẩm<span class="rq">*</span></label>
                        <input type="text" class="form-control" id="productName" name="productName"  value="<%=value_productName%>" placeholder="Enter The Product Name" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <label for="productEntryPrice" class="form-label">Giá nhập<span class="rq">*</span></label>
                        <input type="number" step="0.01"  class="form-control" id="productEntryPrice" name="productEntryPrice"  value="<%=value_productEntryPrice%>" required>
                        <div id="error_entry_price"></div>
                    </div>
                    <div class="col-6">
                        <label for="productPrice" class="form-label">Giá bán<span class="rq">*</span></label>
                        <input type="number" step="0.01"  class="form-control" id="productPrice" name="productPrice"  value="<%=value_productPrice%>" required>
                        <div id="error_price"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <label for="dateOfPublishing" class="form-label">Ngày phát hành</label>
                        <input type="date"  class="form-control" id="dateOfPublishing" name="dateOfPublishing"  value="<%=value_dateOfPublishing%>" >
                    </div>
                    <div class="col-6">
                        <label for="quantity" class="form-label">Số lượng</label>
                        <input type="number"  class="form-control" id="quantity" name="quantity"  value="<%=value_quantity%>" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <label for="author" class="form-label">Tác giả</label>
                        <input type="text"  class="form-control" id="author" name="author"  value="<%=value_author%>" required >
                    </div>
                    <div class="col-6">
                        <label for="typeOfBook" class="form-label">Thể loại</label>
                        <input type="text"  class="form-control" id="typeOfBook" name="typeOfBook"  value="<%=value_typeOfBookString%>" required>
                    </div>
                </div>


                <div class="row">
                    <label for="description" class="form-label">Mô tả</label>
                    <textarea row="10" cols="20" id="description" class="form-control" name="description"><%=value_description%></textarea>                   
                </div>


                <div class="row ">
                    <div class="col-6">
                        <label for="language" class="form-label">Ngôn ngữ</label>
                        <input type="text" class="form-control" id="language" name="language"  value="<%=value_language%>" required>            
                    </div>

                    <div class="col-6">
                        <label for="imagePath" class="form-label">Chọn ảnh bìa</label>
                        <input type="file" class="form-control" id="imagePath" name="imagePath">
                    </div>

                    <!--<button class="btn btn-primary col-6" type="submit" onclick="my_submit()" >Submit form</button>-->
                </div>

                <div class="row ">
                    <div class="col-6 m-auto">
                        <input class="btn btn-primary form-control mt-4" type="submit" value="Lưu thông tin" >
                    </div>

                    <div class="col-6 m-auto">
                        <input class="btn btn-primary form-control mt-4" onclick="emptyInputFields()"  value="Làm trống" >
                    </div>
                </div>



            </form>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Tên sách</th>
                        <th scope="col">Ngày phát hành</th>
                        <th scope="col">Giá nhập</th>
                        <th scope="col">Giá bán</th>
                        <th scope="col">Số lượng</th> 
                        <th scope="col">Ngôn ngữ</th>
                        <th scope="col">Mô tả</th>
                        <th scope="col">Tác giả</th>
                        <th scope="col">Thể loại</th>

                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Object[] result : listBook) {
                            Book book = (Book) result[0];
                            String typeBookName = (String) result[1];
                    %>
                    <tr>
                        <th scope="row"><%= book.getBookId()%></th>
                        <td id="bookTitle_td"><%= book.getFullName()%></td>
                        <td id="dateOfPublishing_td" ><%= book.getDateOfPublishing()%></td>
                        <td id="inputPrice_td"><%= book.getInputPrice()%></td>
                        <td id="sellingPrice_td"><%= book.getSellingPrice()%></td>
                        <td id="quantity_td"><%= book.getQuantity()%></td>
                        <td id="language_td"><%= book.getLanguage()%></td>
                        <td id="description_td"><%= book.getDescription()%></td> 
                        <td id="authorName_td"><%= book.getAuthor().getFullName()%></td> 
                        <td id="typeOfBook_td"><%= typeBookName%></td> 
                        <td>
                            <button class="btn btn-primary form-control" onclick="editRow(this)">Sửa</button>
                        </td>
                        <td>
                            <!--<button class="btn btn-primary form-control" onclick="deleteBook( <%//= book.getBookId()%>,this)">Xóa</button>-->
                            <a class="btn btn-primary form-control" href="save-product?action=delete_book&id=<%= book.getBookId()%>">Xóa</a>                         
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>

        </div>
          
        
    </body>
</html>
