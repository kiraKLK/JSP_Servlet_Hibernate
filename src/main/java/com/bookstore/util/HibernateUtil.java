package com.bookstore.util;

import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.model.Customer;
import com.bookstore.model.CustomerOrder;
import com.bookstore.model.DetailOrder;

import com.bookstore.model.TypeOfBook;
import java.io.File;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {

        Configuration conf = new Configuration();

        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mySQL://localhost:3306/jsp_hibernate");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "");
        props.put(Environment.HBM2DDL_AUTO, "update");
        props.put(Environment.SHOW_SQL, "true");

        conf.setProperties(props);
        conf.addAnnotatedClass(Author.class);
        conf.addAnnotatedClass(TypeOfBook.class);
        conf.addAnnotatedClass(Book.class);
        conf.addAnnotatedClass(Customer.class);
        conf.addAnnotatedClass(CustomerOrder.class);
        conf.addAnnotatedClass(DetailOrder.class);
       

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
