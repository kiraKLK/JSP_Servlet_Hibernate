package com.bookstore.controller;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.TypeOfBookDAO;
import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.model.TypeOfBook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author admin
 */
public class SaveProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            String action = request.getParameter("action");
            switch (action) {
                case "add_product":
                    addProduct2(request, response);
                    break;
                case "delete_book":
                    deleteBook(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(SaveProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Part imagePath = request.getPart("imagePath");
        System.out.println(imagePath);

    }

    protected void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        String priceString = request.getParameter("productPrice");
        String priceEntryString = request.getParameter("productEntryPrice");
        String dateOfPublishingString = request.getParameter("dateOfPublishing");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        String language = request.getParameter("language");
        String authorString = request.getParameter("author");
        String typeOfBookString = request.getParameter("typeOfBook");

        System.out.println(productName);
        System.out.println(priceString);
        System.out.println(priceEntryString);
        System.out.println(dateOfPublishingString);
        String error_productCode = "";

        request.setAttribute("value_productName", productName);
        request.setAttribute("value_productPrice", priceString);
        request.setAttribute("value_productEntryPrice", priceEntryString);
        request.setAttribute("value_dateOfPublishing", dateOfPublishingString);
        request.setAttribute("value_description", description);
        request.setAttribute("value_quantity", quantity);
        request.setAttribute("value_language", language);
        request.setAttribute("value_author", authorString);
        request.setAttribute("value_typeOfBookString", typeOfBookString);

        // error
        request.setAttribute("error_productCode", error_productCode);
        request.setAttribute("error_productName", error_productCode);
        request.setAttribute("error_productPrice", error_productCode);
        request.setAttribute("error_productEntryPrice", error_productCode);
        request.setAttribute("error_productVat", error_productCode);
        request.setAttribute("error_productDescribe", error_productCode);

        if (productCode != null && !productCode.isEmpty()) {

            Long productCodeLong = Long.valueOf(productCode);
            double priceDouble = Double.parseDouble(priceString);
            double entryPriceDouble = Double.parseDouble(priceEntryString);
            Date dateOfPublishingDate = Date.valueOf(dateOfPublishingString);
            int quantityInt = Integer.parseInt(quantity);

            Author authorName = new Author();
            authorName.setFullName(authorString);

            TypeOfBook typeOfBookName = new TypeOfBook();
            typeOfBookName.setTypeName(typeOfBookString);

            Book book = new Book(productCodeLong, productName, dateOfPublishingDate, entryPriceDouble, priceDouble, quantityInt, language, description);

            BookDAO bookDao = new BookDAO();
            bookDao.updateBook(book, authorName, typeOfBookName);

            String url = "/BookStore/AddProduct.jsp";
            RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
            rq.forward(request, response);
        } else {

            double priceDouble = Double.parseDouble(priceString);
            double entryPriceDouble = Double.parseDouble(priceEntryString);
            Date dateOfPublishingDate = Date.valueOf(dateOfPublishingString);
            int quantityInt = Integer.parseInt(quantity);

            Author authorName = new Author();
            authorName.setFullName(authorString);

            TypeOfBook typeOfBookName = new TypeOfBook();
            typeOfBookName.setTypeName(typeOfBookString);

            //Book book = new Book(productName, dateOfPublishingDate, entryPriceDouble, priceDouble, quantityInt, language, description);
            BookDAO bookDao = new BookDAO();
            //bookDao.setBookWithAuthorAndType(book, authorName, typeOfBookName);

            String url = "/BookStore/AddProduct.jsp";
            RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
            rq.forward(request, response);
        }

    }

    protected void addProduct2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException, Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        String priceString = request.getParameter("productPrice");
        String priceEntryString = request.getParameter("productEntryPrice");
        String dateOfPublishingString = request.getParameter("dateOfPublishing");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        String language = request.getParameter("language");
        String authorString = request.getParameter("author");
        String typeOfBookString = request.getParameter("typeOfBook");
        
        System.out.println(productName);
        System.out.println(priceString);
        System.out.println(priceEntryString);
        System.out.println(dateOfPublishingString);

        String error_productCode = "";

        request.setAttribute("value_productName", productName);
        request.setAttribute("value_productPrice", priceString);
        request.setAttribute("value_productEntryPrice", priceEntryString);
        request.setAttribute("value_dateOfPublishing", dateOfPublishingString);
        request.setAttribute("value_description", description);
        request.setAttribute("value_quantity", quantity);
        request.setAttribute("value_language", language);
        request.setAttribute("value_author", authorString);
        request.setAttribute("value_typeOfBookString", typeOfBookString);

        // error
        request.setAttribute("error_productCode", error_productCode);
        request.setAttribute("error_productName", error_productCode);
        request.setAttribute("error_productPrice", error_productCode);
        request.setAttribute("error_productEntryPrice", error_productCode);
        request.setAttribute("error_productVat", error_productCode);
        request.setAttribute("error_productDescribe", error_productCode);

        String uploadPath = getServletContext().getRealPath("CoverBook"); // Đường dẫn thư mục lưu ảnh

        Part part = request.getPart("imagePath"); // Lấy phần tệp tin từ yêu cầu
        String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName(); // Tạo tên tệp tin mới
        System.out.println(fileName);
        String filePath = uploadPath + File.separator + fileName; // Đường dẫn tệp tin mới
        System.out.println(fileName);
        try ( InputStream input = part.getInputStream();  OutputStream output = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }
        String uploadPathVideo = getServletContext().getRealPath("CoverBook"); // Đường dẫn thư mục lưu ảnh

        Part partVideo = request.getPart("imagePath"); // Lấy phần tệp tin từ yêu cầu
        String fileNameVideo = System.currentTimeMillis() + "_" + partVideo.getSubmittedFileName(); // Tạo tên tệp tin mới
        System.out.println(fileNameVideo);
        String filePathVideo = uploadPathVideo + File.separator + fileNameVideo; // Đường dẫn tệp tin mới
        System.out.println(fileNameVideo);
        try ( InputStream input = part.getInputStream();  OutputStream output = new FileOutputStream(filePathVideo)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }

        if (productCode != null && !productCode.isEmpty()) {

            Long productCodeLong = Long.valueOf(productCode);
            double priceDouble = Double.parseDouble(priceString);
            double entryPriceDouble = Double.parseDouble(priceEntryString);
            Date dateOfPublishingDate = Date.valueOf(dateOfPublishingString);
            int quantityInt = Integer.parseInt(quantity);

            Author authorName = new Author();
            authorName.setFullName(authorString);

            TypeOfBook typeOfBookName = new TypeOfBook();
            typeOfBookName.setTypeName(typeOfBookString);

            System.out.println(fileName);
            Book book = new Book(productCodeLong, productName, dateOfPublishingDate, entryPriceDouble, priceDouble, quantityInt, language, description, fileName);

            BookDAO bookDao = new BookDAO(); 
            bookDao.updateBook(book, authorName, typeOfBookName);

            String url = "/BookStore/AddProduct.jsp";
            RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
            rq.forward(request, response);
        } else {

            double priceDouble = Double.parseDouble(priceString);
            double entryPriceDouble = Double.parseDouble(priceEntryString);
            Date dateOfPublishingDate = Date.valueOf(dateOfPublishingString);
            int quantityInt = Integer.parseInt(quantity);

            Author authorName = new Author();
            authorName.setFullName(authorString);

            TypeOfBook typeOfBookName = new TypeOfBook();
            typeOfBookName.setTypeName(typeOfBookString);

            System.out.println(fileName);
            Book book = new Book(productName, dateOfPublishingDate, entryPriceDouble, priceDouble, quantityInt, language, description, fileName);

            BookDAO bookDao = new BookDAO();
            bookDao.setBookWithAuthorAndType(book, authorName, typeOfBookName);

            String url = "/BookStore/AddProduct.jsp";
            RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
            rq.forward(request, response);
        }

    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdString = request.getParameter("id");

        if (bookIdString != null) {
            long id = Long.parseLong(bookIdString);
            BookDAO bookDao = new BookDAO();
            bookDao.deleteBookById(id);
            String url = "/BookStore/AddProduct.jsp";
            RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
            rq.forward(request, response);

        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdString = request.getParameter("bookId");
        if (bookIdString != null && !bookIdString.isEmpty()) {
            long bookId = Long.parseLong(bookIdString);
            // Thực hiện xóa sách từ cơ sở dữ liệu dựa trên bookId
            BookDAO bookDao = new BookDAO();
            bookDao.deleteBookById(bookId);
            // Sau khi xóa thành công, gửi phản hồi 200 OK
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Gửi phản hồi lỗi nếu bookId không hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid bookId");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
