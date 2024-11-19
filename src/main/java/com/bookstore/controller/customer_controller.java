/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bookstore.controller;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.TypeOfBookDAO;
import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.model.Customer;
import com.bookstore.model.DetailOrder;
import com.bookstore.model.TypeOfBook;
import com.google.gson.Gson;

import com.kira.util.Email;
import com.kira.util.encode;
import com.kira.util.random_number;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "customer_controller", urlPatterns = {"/BookStore/customer_controller"})
public class customer_controller extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet customer_controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet customer_controller at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "login":
                    login(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "change_information":
                    changeInfor(request, response);
                    break;
                case "change_password":
                    changePassword(request, response);
                    break;
                case "register":
                    register(request, response);
                    break;
                case "verify":
                    verifyAccount(request, response);
                    break;
                case "change_avatar":
                    try {
                    changeAvatar(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(customer_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                case "add_to_cart":
                    addToCart2(request, response);
                    break;
                case "filter_by_author":
                    author(request, response);
                    break;
                case "filter_by_type":
                    typeOfBook(request, response);
                    break;
                case "search_suggestions":
                    searchSuggestions(request, response);
                    break;
                case "search":
                    search(request,response);
                default:
                    break;
            }
        } catch (MessagingException ex) {
            Logger.getLogger(customer_controller.class.getName()).log(Level.SEVERE, null, ex);
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        doGet(request, response);
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

    private void searchSuggestions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        BookDAO bookDao = new BookDAO();
        List<Book> bookSuggestions = bookDao.getBookSuggestions(keyword);
        
        // chuyển đổi danh sách book thành danh sách các đối tượng json
        List<Map<String,Object>> listBook = new ArrayList<>();
        for(Book book : bookSuggestions){
            Map<String,Object> bookMap = new HashMap<>();
            bookMap.put("id", book.getBookId());
            bookMap.put("bookName",book.getFullName());
            
            
            listBook.add(bookMap);
        }
        // chuyển danh sách map thành chuỗi json
        String jsonResponse = new Gson().toJson(listBook);
        
        
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(jsonResponse));
    }
    
    private void search(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        BookDAO bookDao = new BookDAO();
        List<Book> listBook = bookDao.getBookSuggestions(keyword);
        if(!listBook.isEmpty()){
            for (Book book : listBook) {
                
                System.out.println(book.toString());
                //System.out.println(book.getFullName());
            }
           }else{
               System.out.println("No books found.");
           }
        HttpSession session = req.getSession();
        session.setAttribute("list_book", listBook);
        res.sendRedirect(req.getContextPath() + "/BookStore/collections.jsp");
    }

    private void author(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String authorId = req.getParameter("authorId");
        BookDAO bookDao = new BookDAO();
        AuthorDAO authorDao = new AuthorDAO();
        Author author = authorDao.selectById(Long.parseLong(authorId));
        List<Book> listBook = bookDao.selectBookWithAuthor(author);
        HttpSession session = req.getSession();
        session.setAttribute("list_book", listBook);
        res.sendRedirect(req.getContextPath() + "/BookStore/collections.jsp");
    }

    private void typeOfBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String typeId = req.getParameter("typeId");
        BookDAO bookDao = new BookDAO();
        TypeOfBookDAO typeDao = new TypeOfBookDAO();
        TypeOfBook type = typeDao.selectById(Long.parseLong(typeId));
        List<Book> listBook = bookDao.selectBookWithTypeOfBook(type);
        HttpSession session = req.getSession();
        session.setAttribute("list_book", listBook);
        res.sendRedirect(req.getContextPath() + "/BookStore/collections.jsp");
    }

    private void addToCart2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        BookDAO bookDao = new BookDAO();
        Book book = bookDao.selectById(Long.parseLong(bookId));

        HttpSession session = req.getSession();
        Object obj = session.getAttribute("cart"); // luu tam vao session
        if (obj == null) { // tao moi
            // tao mat hang
            DetailOrder detailOrder = new DetailOrder();
            detailOrder.setBook(book);
            detailOrder.setQuantity(1);
            detailOrder.setSumMoney(book.getSellingPrice());

            // gio hang co nhieu mat hang
            Map<String, DetailOrder> map = new HashMap<>();
            map.put(bookId, detailOrder); // them mat hang vao danh sach
            session.setAttribute("cart", map);
        } else {
            Map<String, DetailOrder> map = (Map<String, DetailOrder>) obj;
            DetailOrder detailOrder = map.get(bookId);
            if (detailOrder == null) {
                detailOrder = new DetailOrder();
                detailOrder.setBook(book);
                detailOrder.setQuantity(1);
                detailOrder.setSumMoney(book.getSellingPrice());
                map.put(bookId, detailOrder);
            } else {
                detailOrder.setQuantity(detailOrder.getQuantity() + 1);
                detailOrder.setSumMoney(detailOrder.getSumMoney() + detailOrder.getBook().getSellingPrice());
            }
            session.setAttribute("cart", map); // luu tam vao session
        }
        res.sendRedirect(req.getContextPath() + "/BookStore/cart.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        password = encode.encryptPassword(password);

        Customer cus = new Customer();
        cus.setUserName(user_name);
        cus.setPassword(password);

        CustomerDAO cusDAO = new CustomerDAO();
        Customer customer = cusDAO.selectAccount(cus);

        String url = "";

        if (customer != null && customer.isVerifyState()) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            url = "/BookStore/home.jsp";
        } else {
            request.setAttribute("error", "Username, password invalid or account are not verify!");
            url = "/BookStore/login.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        // Hủy bỏ session
        session.invalidate();
        response.sendRedirect("/JSP_Servlet_Hibernate/BookStore/home.jsp");

    }

    private void changeInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String accessReceivedEmail = request.getParameter("access_received_email");

        request.setAttribute("full_name", fullName);
        request.setAttribute("gender", gender);
        request.setAttribute("address", address);
        request.setAttribute("email", email);
        request.setAttribute("access_received_email", accessReceivedEmail);

        String url = "/BookStore/change_information.jsp";
        String error = "";

        HttpSession session = request.getSession();
        CustomerDAO cusDAO = new CustomerDAO();
        Object obj = session.getAttribute("customer");
        Customer customer = null;

        if (obj != null) {
            customer = (Customer) obj;
        }
        if (customer == null) {
            error = "Bạn chưa đăng nhập vào hệ thống";
        } else {
            Customer newCustomer = new Customer();
            newCustomer.setCustomerID(customer.getCustomerID());
            newCustomer.setFullName(fullName);
            newCustomer.setAddress(address);
            newCustomer.setGender(gender);
            newCustomer.setEmail(email);
            newCustomer.setRegisterGetAdv(accessReceivedEmail != null);
            cusDAO.updateInformation(newCustomer);

            session.setAttribute("customer", newCustomer);
            error = "Thay đổi thông tin thành công!";
            url = "/BookStore/change_information.jsp";
        }
        request.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        String reenter_new_password = request.getParameter("reenter_new_password");

        String encode_old_password = encode.encryptPassword(old_password);
        String error = "";

        String url = "/BookStore/changepassword.jsp";

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("customer");
        Customer customer = null;
        if (obj != null) {
            customer = (Customer) obj;
        }
        if (customer == null) {
            error = "Bạn chưa đăng nhập vào hệ thống";
        } else {
            if (!encode_old_password.equals(customer.getPassword())) {
                error = "Mật khẩu hiện tại không chính xác!";
            } else {
                if (!new_password.equals(reenter_new_password)) {
                    error = "Mật khẩu nhập lại không khớp với mật khẩu mới!";
                } else {
                    String encode_new_password = encode.encryptPassword(new_password);
                    customer.setPassword(encode_new_password);
                    CustomerDAO cusDAO = new CustomerDAO();
                    if (cusDAO.changePasssword(customer)) {
                        error = "Mật khẩu đã thay đổi thành công!";
                        url = "/BookStore/login.jsp";
                    } else {
                        error = "Thay đổi mật khẩu không thành công!";
                    }
                }
            }
        }
        request.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("user_name");
        String password = request.getParameter("password");
        String reenter_password = request.getParameter("reenter_password");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String birthday = request.getParameter("birthday");
        String address_of_purchaser = request.getParameter("address_of_purchaser");
        String phone_number = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String access_received_email = request.getParameter("access_received_email");

        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("reenter_password", reenter_password);
        request.setAttribute("fullName", fullName);
        request.setAttribute("gender", gender);
        request.setAttribute("address", address);
        request.setAttribute("birthday", birthday);
        request.setAttribute("address_of_purchaser", address_of_purchaser);
        request.setAttribute("phone_number", phone_number);
        request.setAttribute("email", email);
        request.setAttribute("access_received_email", access_received_email);

        String url = "";
        String error = "";

        Customer registerUserName = new Customer();
        registerUserName.setUserName(username);

        CustomerDAO customer_DAO = new CustomerDAO();
        customer_DAO.selectUserName(registerUserName);

        if (customer_DAO.selectUserName(registerUserName) == true) {
            error += "Tên người dùng đã tồn tại vui lòng chọn tên người dùng khác !";
        }
        if (!password.equals(reenter_password)) {
            error += " Mật khẩu nhập lại không khớp với mật khẩu! <br/>";
        } else {
            password = encode.encryptPassword(password);
        }
        if (error.length() > 0) {
            url = "/BookStore/register.jsp";
        } else {
            Customer customer = new Customer(fullName, username, password, gender, address, email, access_received_email != null);
            if (customer_DAO.insert(customer) == true) {
                String randomNumber = random_number.getRandomNumber();
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MINUTE, 5);
                Date time_active = new Date(c.getTimeInMillis());

                customer.setVerifyCode(randomNumber);
                customer.setTimeVerify(time_active);
                customer.setVerifyState(false);
                url = "/BookStore/register.jsp";
                if (customer_DAO.updateVerifyInformation(customer) == true) {
                    Email.sendEmail(customer.getEmail(), "Xác nhận tài khoản", getContent(customer));
                }
            }
        }

        request.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void verifyAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customer_id = request.getParameter("customer_id");
        String verify_code = request.getParameter("verify_code");

        String url = "/BookStore/login.jsp";
        String error = "";

        CustomerDAO cusDAO = new CustomerDAO();
        Customer cus = new Customer();
        cus.setCustomerID(Long.parseLong(customer_id));
        Customer customer = cusDAO.selectById(cus);

        if (customer != null) {
            if (customer.getVerifyCode().equals(verify_code)) {
                customer.setVerifyState(true);
                cusDAO.updateVerifyInformation(customer);
                error = "Xác thực thành công";
            } else {
                error = "Xác thực thất bại";
            }
        } else {
            error = "Tài khoản không tồn tại";
        }
        request.setAttribute("error", error);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private void changeAvatar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static String getContent(Customer customer) {
        String link = "http://localhost:9999/JSP_Servlet_Hibernate/BookStore/customer_controller?action=verify&customer_id=" + customer.getCustomerID() + "&verify_code=" + customer.getVerifyCode();
        String content = "<p>Hello <strong>" + customer.getFullName() + ".</strong></p>\n"
                + "\n"
                + "<p>Please fill this verify code:&nbsp;<strong>" + customer.getVerifyCode() + "</strong> to complite register<strong>, </strong>or click on URL :</p>\n"
                + "\n"
                + "<p><a href=\"" + link + "\">http://BookStore.com</a></p>\n"
                + "\n"
                + "<p>This is automatic email send, please don&#39;t resend.</p>\n"
                + "\n"
                + "<p>Thank.</p>";
        return content;
    }

}
