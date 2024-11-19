/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.test;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.TypeOfBookDAO;
import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.model.TypeOfBook;
import com.bookstore.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
public class test3 {

    public static void main(String[] args) {
//        TypeOfBookDAO typeDao = new TypeOfBookDAO();
//        BookDAO bookDao = new BookDAO();
//        AuthorDAO authorDao = new AuthorDAO();
//
//        Book book = new Book();
//        book.setFullName("Con rong phun lua");
//
//        Author author = new Author();
//        author.setFullName("Huy cậ");
//        
////         authorDao.selectByName("huy");
////        
////        
////       typeDao.selectByTypeName("aaa");
//        
//
//        TypeOfBook type = new TypeOfBook();
//        type.setTypeName("T");
//
//        if(bookDao.setBookWithAuthorAndType(book,author,type)){
//            System.out.println("insert succsess");
//        }else{
//            System.out.println("insert fail");
//        }

        //--------------------------
        SessionFactory sessionFactory = HibernateUtil.getFACTORY();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String keyword = "động";
        try {
            
            String hql = "SELECT b FROM Book b WHERE b.fullName LIKE :keyword";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", "%" + keyword + "%");
           List<Book> listBook = (List<Book>) query.getResultList();
           if(!listBook.isEmpty()){
            for (Book book : listBook) {
                
                System.out.println(book.toString());
                //System.out.println(book.getFullName());
            }
           }else{
               System.out.println("No books found.");
           }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
     
        
    }

}
