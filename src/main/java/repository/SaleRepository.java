package repository;

import dto.Customer;
import dto.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SaleRepository {

    private static final SessionFactory factory = SessionManager.getSessionFactory();


    public Sale createSale(Sale sale){
        Transaction tx = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.persist(sale);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return sale;
    }

    public List<Sale> findAllSales(){
        Transaction tx = null;
        List<Sale> sales = null;

        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            sales = session.createQuery("from Sale", Sale.class).getResultList();
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        return sales;
    }

    public Sale findSaleById(Long id){
        Transaction tx = null;
        Sale sale = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            sale = session.find(Sale.class, id);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        return sale;
    }


    public List<Sale> findSalesByCustomerId(Customer customer){
        Transaction tx = null;
        List<Sale> sales = new ArrayList<>();
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Query<Sale> query = session.createQuery("from Sale where customer = :customer", Sale.class);
            query.setParameter("customer", customer);
            sales = query.getResultList();
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        return sales;
    }

    public Sale updateSale(Sale sale){
        Transaction tx = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.merge(sale);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        return sale;
    }

    public String deleteSale(Sale sale){
        Transaction tx = null;
        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.remove(sale);
            tx.commit();
        } catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        return "Sale deleted!";
    }
}
