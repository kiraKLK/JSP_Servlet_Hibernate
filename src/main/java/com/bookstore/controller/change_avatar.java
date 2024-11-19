/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bookstore.controller;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.model.Customer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "change_avatar", urlPatterns = {"/BookStore/change_avatar"})
@MultipartConfig
public class change_avatar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet change_avatar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet change_avatar at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Object obj = request.getSession().getAttribute("customer");
            Customer customer = null;
            if (obj != null) {
                customer = (Customer) obj;
            }

            if (customer != null) {
                try {
//                    String folder = getServletContext().getRealPath("avatar");
//                    System.out.println("this is path:" + folder);
//                    
//                    Part file = request.getPart("duongDanAnh");
//                    
//                    String imageFileName = file.getSubmittedFileName();
//                    System.out.println(imageFileName);
//                    
//                    String uploadPath = folder + File.separator + imageFileName;
//                    System.out.println(uploadPath);
//                    
//                    
//                    FileOutputStream fos = new FileOutputStream(new File(uploadPath));
//                    InputStream is = file.getInputStream();
//                    
//                    byte[] date = new byte[is.available()];
//                    is.read(date);
//                    fos.write(date);
//                    fos.close();

                   // String folder1 = "C:\\Users\\admin\\OneDrive\\Documents\\NetBeansProjects\\JSP_Servlet_Hibernate\\src\\main\\webapp\\avatar";
                    //C:\Users\admin\OneDrive\Documents\NetBeansProjects\JSP_Servlet_Hibernate\src\main\webapp\avatar
                   // "C:\Users\admin\OneDrive\Documents\NetBeansProjects\JSP_Servlet_Hibernate\src\main\webapp\avatar"
                    String folder1 = "C:\\Users\\admin\\OneDrive\\Documents\\NetBeansProjects\\JSP_Servlet_Hibernate\\target\\JSP_Servlet_Hibernate-1.0-SNAPSHOT\\avatar";
                    System.out.println(folder1);
                    File folder = new File(folder1);
                    if (!folder.exists()) {
                        folder.mkdirs(); // Tạo thư mục nếu chưa tồn tại (bao gồm cả thư mục cha nếu cần)
                    }
                     if (folder.exists()) {
                        if (folder.canRead()) {
                            System.out.println("You have read access to the folder.");
                        } else {
                            System.out.println("You do not have read access to the folder.");
                        }

                        if (folder.canWrite()) {
                            System.out.println("You have write access to the folder.");
                        } else {
                            System.out.println("You do not have write access to the folder.");
                        }

                        if (folder.canExecute()) {
                            System.out.println("You have execute access to the folder.");
                        } else {
                            System.out.println("You do not have execute access to the folder.");
                        }
                    } else {
                        System.out.println("The folder does not exist.");
                    }
                    File file;
                    int maxFileSize = 5000 * 1024;
                    int maxMemSize = 5000 * 1024;
                    String contentType = request.getContentType();
                    System.out.println(contentType);
                    System.out.println(contentType.indexOf(contentType));

                    String fileName = null;
                    if (contentType.indexOf(contentType) >= 0) {
                        DiskFileItemFactory factory = new DiskFileItemFactory();

                        // quy dinh dung luong toi da cho 1 file
                        factory.setSizeThreshold(maxMemSize);

                        // tao file upload
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        upload.setSizeMax(maxFileSize);
                        List<FileItem> files = upload.parseRequest(request);
                        for (FileItem fileItem : files) {
                            if (!fileItem.isFormField()) {
                                //fileName = System.currentTimeMillis() + fileItem.getName();
                                fileName =  fileItem.getName();
                                System.out.println(fileName);
                                String path = folder1 + File.separator + fileName;
                                System.out.println(path);
                                file = new File( path);
                                fileItem.write(file);
                                customer.setPathAvatarImages(fileName);
                                CustomerDAO cusDAO = new CustomerDAO();
                                cusDAO.update(customer);
                                customer = cusDAO.selectById(customer);

                            } else {
                                String name = fileItem.getFieldName();
                                String value = fileItem.getString();
                                System.out.println(name + ":" + value);
                            }

                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(change_avatar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileUploadException ex) {
                    Logger.getLogger(change_avatar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(change_avatar.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            String url = "/BookStore/change_avatar.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(change_avatar.class.getName()).log(Level.SEVERE, null, ex);
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
