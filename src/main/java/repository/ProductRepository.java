package repository;

import dto.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final SessionFactory factory = SessionManager.getSessionFactory();


    public Product createProduct(Product product){
        Transaction tx = null;

        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> findAllProducts(){
        Transaction tx = null;
        List<Product> products = new ArrayList<>();

        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            products = session.createQuery("from Product", Product.class).getResultList();
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return products;
    }

    public Product findProductById(Long id){
        Transaction tx = null;
        Product product = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            product = session.find(Product.class, id);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        return product;
    }

    public Product updateProduct(Product product){
        Transaction tx = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        return product;
    }

    public String deleteProduct(Product product){
        Transaction tx = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.remove(product);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        return "Product deleted!";
    }
}
