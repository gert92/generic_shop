package repository;

import dto.Customer;
import dto.Product;
import dto.Sale;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
    private static final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Customer.class)
            .addAnnotatedClass(Product.class)
            .addAnnotatedClass(Sale.class)
            .buildSessionFactory();

    public static SessionFactory getSessionFactory(){
        return factory;
    }
}
