package com.bookstore.dao;

import com.bookstore.model.Author;
import com.bookstore.model.Author;
import com.bookstore.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AuthorDAO implements DAOInterface<Author> {
    
     SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    @Override
    public List<Author> selectALL() {
        List<Author> listAuthor = new ArrayList();
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from Author";
                Query query = session.createQuery(hql);
                listAuthor = query.getResultList();
                tr.commit();
                session.close();
            }
        } catch (HibernateException e) {
        }
        return listAuthor;
    }

    public Author selectById(long t) {
        Author book = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                // cach 1
                String hql = "from Author  where authorId=:id";
                Query query = session.createQuery(hql);
                query.setParameter("id", t);
                 book = (Author) query.getSingleResult();
               

                // cach 2 khong nen dung cho table co quan he
//                 book = session.get(Author.class, 1);
                tr.commit();
                session.close();
            }
        } catch (HibernateException|NoResultException e) {
        }
        return book;
    }
    
      public Author selectByName(String t) {
        Session session = sessionFactory.openSession();
        Author book = null;
        try {
            session.beginTransaction();
            String hql = "from Author  where fullName=:name";
            Query query = session.createQuery(hql);
            query.setParameter("name", t);
            book = (Author) query.getSingleResult();
        } catch (HibernateException |NoResultException e) {
            session.getTransaction().rollback();
        } finally {
            //session.flush();
            //session.close();
        }
        return book;
    }

   public boolean saveOrUpdate(Author book) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(book);
            session.getTransaction().commit();
            System.out.println("insert success!");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
           // session.flush();
            session.close();
        }
        return true;
    }

    @Override
    public boolean insert(Author t) {
        return saveOrUpdate(t);
    }

    @Override
    public boolean update(Author t) {
         return saveOrUpdate(t);
    }

    @Override
    public boolean delete(Author t) {
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
    public Author selectById(Author t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
