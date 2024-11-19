package com.bookstore.dao;

import com.bookstore.model.Customer;
import com.bookstore.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CustomerDAO implements DAOInterface<Customer> {

    @Override
    public List selectALL() {
        List<Customer> listCustomer = new ArrayList();
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from Customer";
                Query query = session.createQuery(hql);
                listCustomer = query.getResultList();
                tr.commit();
                session.close();
            }
        } catch (HibernateException e) {
        }
        return listCustomer;
    }

    @Override
    public Customer selectById(Customer t) {
        Customer book = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                // cach 1
                String hql = "from Customer  where customerID=:id";
                Query query = session.createQuery(hql);
                query.setParameter("id", t.getCustomerID());
                book = (Customer) query.getSingleResult();

                // cach 2 khong nen dung cho table co quan he
//                 book = session.get(Customer.class, 1);
                session.close();
            }
        } catch (HibernateException e) {
        }
        return book;
    }

    public boolean selectUserName(Customer t) {
        Customer book = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

                String hql = "from Customer  where userName=:userName";
                Query query = session.createQuery(hql);
                query.setParameter("userName", t.getUserName());
                book = (Customer) query.getSingleResult();

                session.close();
                return true;
            }
        } catch (HibernateException | NoResultException e) {
         
        }
        return false;
    }

    public Customer selectAccount(Customer t) {
        Customer book = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();

                String hql = " from Customer c  where c.userName=:value1 and c.password=:value2";
                Query query = session.createQuery(hql);
                query.setParameter("value1", t.getUserName());
                query.setParameter("value2", t.getPassword());
                book = (Customer) query.getSingleResult();

                session.close();
               
            }
        } catch (HibernateException | NoResultException e) {

        }
        return book;
    }

    public boolean updateVerifyInformation(Customer t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

//                String hql = "UPDATE EntityName SET field1 = :value1, field2 = :value2 WHERE id = :id";
//                Query query = session.createQuery(hql);
//                query.setParameter("value1", newValue1);
//                query.setParameter("value2", newValue2);
//                query.setParameter("id", entityId);
//                int result = query.executeUpdate();
                String hql = "update Customer set verifyCode=:value1, timeVerify=:value2,verifyState=:value3 where customerID=:id";
                Query query = session.createQuery(hql);
                query.setParameter("value1", t.getVerifyCode());
                query.setParameter("value2", t.getTimeVerify());
                query.setParameter("value3", t.isVerifyState());
                query.setParameter("id", t.getCustomerID());
                query.executeUpdate();

                tr.commit();
                session.close();
                return true;
            }
        } catch (HibernateException e) {
        }
        return false;
    }
    
    public boolean updateAvatar(Customer t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

//                String hql = "UPDATE EntityName SET field1 = :value1, field2 = :value2 WHERE id = :id";
//                Query query = session.createQuery(hql);
//                query.setParameter("value1", newValue1);
//                query.setParameter("value2", newValue2);
//                query.setParameter("id", entityId);
//                int result = query.executeUpdate();
                String hql = "update Customer set pathAvatarImages:=value where customer";
                Query query = session.createQuery(hql);
                query.setParameter("value1", t.getVerifyCode());
                query.setParameter("value2", t.getTimeVerify());
                query.setParameter("value3", t.isVerifyState());
                query.setParameter("id", t.getCustomerID());
                query.executeUpdate();

                tr.commit();
                session.close();
                return true;
            }
        } catch (HibernateException e) {
        }
        return false;
    }

    public boolean changePasssword(Customer t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

                String hql = "update Customer set password=:value1 where customerID=:id";
                Query query = session.createQuery(hql);
                query.setParameter("value1", t.getVerifyCode());
                query.setParameter("id", t.getCustomerID());
                query.executeUpdate();

                tr.commit();
                session.close();
                return true;
            }
        } catch (HibernateException e) {
        }
        return false;
    }

    public boolean updateInformation(Customer t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

                String hql = "update Customer set fullName=:value1, gender=:value2,address=:value3,email=:value4,registerGetAdv=:value5 where customerID=:id";
                Query query = session.createQuery(hql);
                query.setParameter("value1", t.getFullName());
                query.setParameter("value2", t.getGender());
                query.setParameter("value3", t.getAddress());
                query.setParameter("value4", t.getEmail());
                query.setParameter("value5", t.isRegisterGetAdv());
                query.setParameter("id", t.getCustomerID());
                query.executeUpdate();

                tr.commit();
                session.close();
                return true;
            }
        } catch (HibernateException e) {
        }
        return false;
    }

    public boolean saveOrUpdate(Customer t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getFACTORY();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                //session.save(t); // chỉ lưu khi đối tượng chưa tồn tại
                session.saveOrUpdate(t); // thêm mới khi chưa tồn tại, cập nhật dữ liệu khi đã tồn tại đối tượng.
                tr.commit();
                session.close();
                return true;
            }
        } catch (HibernateException e) {
        }
        return false;
    }

    @Override
    public boolean insert(Customer t) {
        return saveOrUpdate(t);
    }

    @Override
    public boolean update(Customer t) {
       return saveOrUpdate(t);
    }

    @Override
    public boolean delete(Customer t) {
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

}
