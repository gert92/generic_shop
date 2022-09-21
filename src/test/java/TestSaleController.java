import dto.Customer;
import dto.Sale;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.SaleRepository;
import repository.SessionManager;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TestSaleController {

    private static SessionFactory sessionFactory;
    private static SaleRepository saleRepository;
    private static Sale sale;
    private Session session;

    @BeforeAll
    public static void setUp() {
        sessionFactory = SessionManager.getSessionFactory();
        saleRepository = new SaleRepository();
        sale = Sale.builder().customer(new Customer(10L, "Someone", 10F)).product(new ArrayList<>()).build(); // Problem \\

        System.out.println("Session Factory created!");
    }

    @AfterAll
    public static void shutDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("Session Factory closed!");
        }
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created!");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) {
            session.close();
            System.out.println("Session closed!");
        }
    }

    @Test
    @Order(1)
    public void testCreateSale() {
        sale = saleRepository.createSale(sale); // Problem \\
        org.assertj.core.api.Assertions.assertThat(sale.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testShowingAllSales() {
        List<Sale> sales = saleRepository.findAllSales();
        org.assertj.core.api.Assertions.assertThat(sales).isNotEmpty();
    }

    @Test
    @Order(3)
    public void testGetSingleSaleById() {
        Sale dataSale = saleRepository.findSaleById(sale.getId());
        org.assertj.core.api.Assertions.assertThat(dataSale.getCustomer()).isEqualTo(sale.getCustomer());
        org.assertj.core.api.Assertions.assertThat(dataSale.getProduct()).isEqualTo(sale.getProduct());
    }

    @Test
    @Order(3)
    public void testGetSingleSaleByCustomerId() {
        Sale dataSale = saleRepository.findSalesByCustomerId(sale.getId()); // Problem \\
        org.assertj.core.api.Assertions.assertThat(dataSale.getCustomer()).isEqualTo(sale.getCustomer());
        org.assertj.core.api.Assertions.assertThat(dataSale.getProduct()).isEqualTo(sale.getProduct());
    }

    @Test
    @Order(5)
    public void testUpdateASingleSale() {
        sale.setCustomer(Customer.builder().build()); // Problem \\
        sale.setProduct(); // Problem \\
        Sale updateSale = saleRepository.updateSale(sale);
        org.assertj.core.api.Assertions.assertThat(updateSale.getCustomer()).isEqualTo(sale.getCustomer());
        org.assertj.core.api.Assertions.assertThat(updateSale.getProduct()).isEqualTo(sale.getProduct());
    }

    @Test
    @Order(4)
    public void testRemovingACustomer() {
        Sale findSale = saleRepository.findSaleById(sale.getId());
        org.assertj.core.api.Assertions.assertThat(findSale).isNotNull();
        saleRepository.deleteSale(findSale);
        Sale deletedSale = saleRepository.findSaleById(sale.getId());
        Assertions.assertThat(deletedSale).isNull();
    }
}
