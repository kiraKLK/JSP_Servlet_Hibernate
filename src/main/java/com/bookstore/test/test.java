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

public class test {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                try {
                    Transaction tr = session.beginTransaction();
                    //SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId
//                    String authorId = "Type1";
//                    String hql = "select b from Book b join b.typeOfBook t where t.typeOfBookId =:authorId  ";
//                    Query query = session.createQuery(hql);
//                    query.setParameter("authorId", authorId);
//                    List<Book> listBook = query.getResultList();
//                    // List<Object []> kq =  query.getResultList();
//                    for (Book book : listBook) {
//                        System.out.println(book.getBookId());
//                        System.out.println(book.getFullName());
//                        System.out.println(book.getAuthor().getFullName());
////                        System.out.println(book.getBookId());
//                        System.out.println("--------------------------");
//                    }

//                    String hql1 = "SELECT b, c.typeName FROM Book b JOIN b.typeOfBook c";
//                    List<Object[]> results = session.createQuery(hql1).getResultList();
//
//                    for (Object[] result : results) {
//                        Book book = (Book) result[0];
//                        String categoryName = (String) result[1];
//                        System.out.println(book + " - " + categoryName);
//                    }
//                    Author author = new Author();
//                    author.setFullName("Bo giao duc");
//                    
//                    TypeOfBook type = new TypeOfBook();
//                    type.setTypeName("sach giao khoa");
//
//                    book.setAuthor(author);
//                   // book.addTypeOfBook(type);
//                    
//                    type.addBook(book);
//
//                    session.saveOrUpdate(author);
//                    session.saveOrUpdate(book);
//                    session.saveOrUpdate(type);
//                    Customer cus = new Customer();
//
//                    Book book1 = new Book();
//                    book1.setFullName("Vật lí đại cương");
//                    book1.setSellingPrice(10.000);
//
//                    Book book2 = new Book();
//                    book2.setFullName("Hoa dai cuong");
//                    book2.setSellingPrice(15.000);
//
//                    Book book3 = new Book();
//                    book3.setFullName("Tin hoc dai cuong");
//                    book3.setSellingPrice(20.000);
//
//                    DetailOrder detail = new DetailOrder();
//                    //detail.setOrder(order);
//                    detail.setSumMoney(10.000 + 15.000 + 20.000);
//                   detail.addBook(book3);
//                   detail.addBook(book2);
//                   detail.addBook(book1);
//
//                    CustomerOrder order = new CustomerOrder();
//                    order.setShipAddress("CNTT Thai Nguyen");
//                    order.setOrderStatus("Chua thanh toan");
//                    order.setCustomer(cus);
//                    order.setDetaiOrder(detail);
//
//                    cus.setFullName("Nguyen Van Tung");
//                    cus.setAddress("Thai Nguyen");
//                    cus.getOrder().add(order);
//
//                    session.saveOrUpdate(cus);
//                    session.saveOrUpdate(detail);
                    //session.saveOrUpdate(order);
                    //---------------------------------------------------
//                    String hqlDeleteBooks = "DELETE FROM Book";
//                    String hqlDeleteAuthors = "DELETE FROM Author";
//                    String hqlDeleteTypes = "DELETE FROM TypeOfBook";
//
//                    Query queryBooks = session.createQuery(hqlDeleteBooks);
//                    Query queryAuthors = session.createQuery(hqlDeleteAuthors);
//                    Query queryTypes = session.createQuery(hqlDeleteTypes);
//
//                    queryBooks.executeUpdate();
//                    queryAuthors.executeUpdate();
//                    queryTypes.executeUpdate();
//--------------------------------------------------------------------------
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
                } finally {
                    
                    session.close();
                }
            }
        } catch (HibernateException e) {
        }
    }
}
