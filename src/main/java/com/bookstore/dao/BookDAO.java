package com.bookstore.dao;

import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.model.TypeOfBook;
import com.bookstore.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BookDAO implements DAOInterface<Book> {

    SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    @Override
    public List<Book> selectALL() {
        List<Book> listBook = new ArrayList();
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from Book";
                Query query = session.createQuery(hql);
                listBook = query.getResultList();
                tr.commit();
                session.close();
            }
        } catch (HibernateException e) {
        }
        return listBook;
    }

    public List<Object[]> selectBookInformation() {
        List<Object[]> listBook = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql1 = "SELECT b, c.typeName FROM Book b JOIN b.typeOfBook c";
                listBook = session.createQuery(hql1).getResultList();
                tr.commit();
                session.close();
            }
        } catch (HibernateException e) {
        }
        return listBook;
    }

    public Book selectById(long t) {
        Book book = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from Book  where bookId=:bookId";
                Query query = session.createQuery(hql);
                query.setParameter("bookId", t);
                book = (Book) query.getSingleResult();
                tr.commit();
                session.close();
            }
        } catch (HibernateException e) {
        }
        return book;
    }

    public boolean saveOrUpdate(Book book) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(book);
            session.getTransaction().commit();
            System.out.println("insert success!");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            //session.flush();
            session.close();
        }
        return true;
    }
    
    public List<Book> getBookSuggestions(String keyword){
        Session session = null;
        Transaction transaction = null;
        List<Book> listBook = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from Book b where b.fullName like :keyword ";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", "%" + keyword + "%");
            listBook = (List<Book>) query.getResultList();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }           
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listBook;
    }
    
    
    public List<Book> selectBookWithAuthor(Author author){
        Session session = null;
        Transaction transaction = null;
        List<Book> listBook = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from Book b where b.author.authorId=:id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", author.getAuthorId());
            listBook = (List<Book>) query.getResultList();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listBook;
    }
    
    public List<Book>  selectBookWithTypeOfBook(TypeOfBook typeOfBook){
        Session session = null;
        Transaction transaction = null;
        List<Book> listBook = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select b from Book b join b.typeOfBook t WHERE t.typeOfBookId=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", typeOfBook.getTypeOfBookId());
            listBook = (List<Book>) query.getResultList();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listBook;
    }

    public boolean setBookWithAuthorAndType(Book book, Author author, TypeOfBook typeOfBook) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String hql = "from Author where fullName=:name";
            Query query = session.createQuery(hql);
            query.setParameter("name", author.getFullName());
            Author fetchedAuthor = null;
            try {
                fetchedAuthor = (Author) query.getSingleResult();
            } catch (NoResultException e) {
                // Không tìm thấy tác giả, có thể thực hiện các xử lý khác ở đây nếu cần
            }

            String hql2 = "from TypeOfBook where typeName=:name";
            Query query2 = session.createQuery(hql2);
            query2.setParameter("name", typeOfBook.getTypeName());
            TypeOfBook fetchedTypeOfBook = null;

            try {
                fetchedTypeOfBook = (TypeOfBook) query2.getSingleResult();
            } catch (NoResultException e) {
                // Không tìm thấy loại sách, có thể thực hiện các xử lý khác ở đây nếu cần
            }

            if (fetchedAuthor != null) {
                book.setAuthor(fetchedAuthor);
            } else {
                book.setAuthor(author);
                session.saveOrUpdate(author);
            }

            if (fetchedTypeOfBook != null) {
                book.addTypeOfBook(fetchedTypeOfBook);
            } else {
                book.addTypeOfBook(typeOfBook);
                session.saveOrUpdate(typeOfBook);
            }

            session.saveOrUpdate(book);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean updateBook(Book book, Author author, TypeOfBook typeOfBook) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            Book bookToUpdate = session.get(Book.class, book.getBookId());
            
            bookToUpdate.setDateOfPublishing(book.getDateOfPublishing());
            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setFullName(book.getFullName());
            bookToUpdate.setInputPrice(book.getInputPrice());
            bookToUpdate.setLanguage(book.getLanguage());
            bookToUpdate.setQuantity(book.getQuantity());
            bookToUpdate.setSellingPrice(book.getSellingPrice());
            bookToUpdate.setImagePath(book.getImagePath());
            
            String hql = "from Author where fullName=:name";
            Query query = session.createQuery(hql);
            query.setParameter("name", author.getFullName());
            Author fetchedAuthor = null;
            try {
                fetchedAuthor = (Author) query.getSingleResult();
            } catch (NoResultException e) {
                // Không tìm thấy tác giả, có thể thực hiện các xử lý khác ở đây nếu cần
            }

            String hql2 = "from TypeOfBook where typeName=:name";
            Query query2 = session.createQuery(hql2);
            query2.setParameter("name", typeOfBook.getTypeName());
            TypeOfBook fetchedTypeOfBook = null;

            try {
                fetchedTypeOfBook = (TypeOfBook) query2.getSingleResult();
            } catch (NoResultException e) {
                // Không tìm thấy loại sách, có thể thực hiện các xử lý khác ở đây nếu cần
            }

            if (fetchedAuthor != null) {
                bookToUpdate.setAuthor(fetchedAuthor);
           
            } else {
                bookToUpdate.setAuthor(author);
                session.saveOrUpdate(author);
            }

            if (fetchedTypeOfBook != null) {
                bookToUpdate.getTypeOfBook().clear();
                session.flush();
                bookToUpdate.addTypeOfBook(fetchedTypeOfBook);
            } else {
                bookToUpdate.getTypeOfBook().clear();
                session.flush();
                bookToUpdate.addTypeOfBook(typeOfBook);
                session.saveOrUpdate(typeOfBook);
            }
            
            
        
            session.saveOrUpdate(bookToUpdate);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    
    
    public void deleteBookById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);

            if (book != null) {
                session.delete(book);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public boolean insert(Book t) {
        return saveOrUpdate(t);
    }

    @Override
    public boolean update(Book t) {
        return saveOrUpdate(t);
    }

    @Override
    public boolean delete(Book t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                session.delete(t);
                tr.commit();
                session.close();
                return true;
            }
        } catch (HibernateException e) {
        }
        return false;
    }

    @Override
    public Book selectById(Book t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
