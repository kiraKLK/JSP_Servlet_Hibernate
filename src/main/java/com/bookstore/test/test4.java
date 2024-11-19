/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.test;

import com.bookstore.dao.TypeOfBookDAO;
import com.bookstore.model.TypeOfBook;
import java.io.File;
import java.util.List;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author admin
 */
public class test4 {

    public static void main(String[] args) throws Exception {
       String folderPath = "C:\\Users\\admin\\OneDrive\\Documents\\NetBeansProjects\\JSP_Servlet_Hibernate\\target\\JSP_Servlet_Hibernate-1.0-SNAPSHOT\\coverbook"; // Thay bằng đường dẫn thư mục của bạn
        
        File folder = new File(folderPath);
        
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
    }
}
