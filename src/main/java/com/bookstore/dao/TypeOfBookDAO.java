/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.dao;

import com.bookstore.model.Book;
import com.bookstore.model.TypeOfBook;
import com.bookstore.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author admin
 */
public class TypeOfBookDAO implements DAOInterface<TypeOfBook> {

    SessionFactory sessionFactory = HibernateUtil.getFACTORY();

    @Override
    public List<TypeOfBook> selectALL() {
        List<TypeOfBook> listBook = new ArrayList();
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from TypeOfBook";
                Query query = session.createQuery(hql);
                listBook = query.getResultList();
                tr.commit();
                session.close();
            }
        } catch (HibernateException e) {
        }
        return listBook;
    }

    public TypeOfBook selectById(long t) {
        TypeOfBook book = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from TypeOfBook  where typeOfBookId=:id";
                Query query = session.createQuery(hql);
                query.setParameter("id", t);
                book = (TypeOfBook) query.getSingleResult();
                tr.commit();
                session.close();
            }
        } catch (HibernateException | NoResultException e) {
        }
        return book;
    }

    public TypeOfBook selectByTypeName(String t) {
        Session session = sessionFactory.openSession();
        TypeOfBook book = null;
        try {
            session.beginTransaction();
            String hql = "from TypeOfBook  where typeName=:name";
            Query query = session.createQuery(hql);
            query.setParameter("name", t);
            book = (TypeOfBook) query.getSingleResult();
        } catch (HibernateException | NoResultException e) {
            session.getTransaction().rollback();
        } finally {
            //session.flush();
           // session.close();
        }
        return book;
    }

    public boolean saveOrUpdate(TypeOfBook book) {
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

    @Override
    public boolean insert(TypeOfBook t) {
        return saveOrUpdate(t);
    }

    @Override
    public boolean update(TypeOfBook t) {
        return saveOrUpdate(t);
    }

    @Override
    public boolean delete(TypeOfBook t) {
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
    public TypeOfBook selectById(TypeOfBook t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

}
