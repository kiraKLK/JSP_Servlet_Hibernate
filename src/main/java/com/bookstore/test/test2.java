package com.bookstore.test;

import com.bookstore.dao.AuthorDAO;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.TypeOfBookDAO;
import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.model.Customer;
import com.bookstore.model.CustomerOrder;
import com.bookstore.model.DetailOrder;
import com.bookstore.model.TypeOfBook;
import com.bookstore.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class test2 {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

                Book book = new Book();
                book.setFullName("Mùa xuân oi oi oi");
                BookDAO bookDao = new BookDAO();

                AuthorDAO authorDao = new AuthorDAO();
                Author author = authorDao.selectByName("Huy cận");
                System.out.println("Tên tác giả là :" + author.getFullName());
                book.setAuthor(author);

                if (bookDao.saveOrUpdate(book)) {
                    System.out.println("insert success");
                } else {
                    System.out.println("insert fail");
                }

                TypeOfBookDAO typeDao = new TypeOfBookDAO();
                TypeOfBook type = typeDao.selectByTypeName("Tiểu thuyết");
                System.out.println("ten loai sach:" + type.getTypeName());
                type.addBook(book);

                if (typeDao.saveOrUpdate(type)) {
                    System.out.println("insert success");
                } else {
                    System.out.println("insert fail");
                }

               tr.commit();
               session.close();
            }
        } catch (HibernateException e) {
        }

    }

}
